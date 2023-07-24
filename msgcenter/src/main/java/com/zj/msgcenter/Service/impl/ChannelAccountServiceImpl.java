package com.zj.msgcenter.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zj.msgcenter.Entity.ChannelAccount;
import com.zj.msgcenter.Service.ChannelAccountService;
import com.zj.msgcenter.mapper.ChannelAccountMapper;
import org.springframework.stereotype.Service;

@Service
public class ChannelAccountServiceImpl  extends ServiceImpl<ChannelAccountMapper, ChannelAccount> implements ChannelAccountService{
}
