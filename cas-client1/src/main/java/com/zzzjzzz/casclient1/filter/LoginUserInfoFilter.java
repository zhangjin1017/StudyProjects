package com.zzzjzzz.casclient1.filter;

import com.alibaba.druid.util.StringUtils;
import com.zzzjzzz.casclient1.util.CASUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginUserInfoFilter implements Filter {

    Logger logger =  LoggerFactory.getLogger(LoginUserInfoFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request_ = (HttpServletRequest)request;
        String loginName = CASUtil.getLoginNameFromCas(request_);
        if(!StringUtils.isEmpty(loginName)){
            logger.info("访问者 ：" +loginName);
            request_.getSession().setAttribute("loginName", loginName);
        }

        chain.doFilter(request, response);
    }
}
