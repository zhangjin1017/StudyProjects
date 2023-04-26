package com.zzzjzzz.casclient2.util;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;

public class CASUtil {

    public static String getLoginNameFromCas(HttpServletRequest request) {
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if(assertion!= null) {
            AttributePrincipal principal = assertion.getPrincipal();
            return principal.getName();
        } else {
            return null;
        }
    }
}

