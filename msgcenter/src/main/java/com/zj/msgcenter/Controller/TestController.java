package com.zj.msgcenter.Controller;

import com.zj.msgcenter.Entity.ChannelAccount;
import com.zj.msgcenter.Service.ChannelAccountService;
import com.zj.msgcenter.template.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private ChannelAccountService channelAccountService;

    @RequestMapping("/templateparsertest")
    public EmailTemplate templateparsertest(@RequestParam("template") String template) {
        //{"host":"smtp.qq.com","port":465,"user":"ZzzjzzZ-zj@qq.com","pass":"wnkuagjjwtekbacc","from":"ZzzjzzZ-zj@qq.com"}
        EmailTemplate emailTemplate = new EmailTemplate();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            emailTemplate = objectMapper.readValue(template, EmailTemplate.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailTemplate;
//        {
//                "host": "smtp.qq.com",
//                "port": "465",
//                "user": "ZzzjzzZ-zj@qq.com",
//                "pass": "wnkuagjjwtekbacc",
//                "from": "ZzzjzzZ-zj@qq.com"
//        }
    }


    @RequestMapping("/getChannelAccount")
    public List<ChannelAccount> getChannelAccount() {
        System.out.println("getChannelAccount");

        return  channelAccountService.list();
    }

    //add
    @RequestMapping("/addChannelAccount")
    public boolean addChannelAccount(@RequestParam("name") String name,@RequestParam("sendChannel") int sendChannel,@RequestParam("accountConfig") String accountConfig) {
        System.out.println("addChannelAccount");
        ChannelAccount channelAccount =ChannelAccount
                .builder()
                .name(name)
                .sendChannel(sendChannel)
                .accountConfig(accountConfig)
                .build();

        return channelAccountService.save(channelAccount);
    }

    //delete
    @RequestMapping("/deleteChannelAccount/{id}")
    public boolean deleteChannelAccount(@PathVariable("id") int id) {
        System.out.println("deleteChannelAccount");

        return channelAccountService.removeById(id);
    }

    //update
    @RequestMapping("/updateChannelAccount")
    public boolean updateChannelAccount(@RequestParam("id") Long id,@RequestParam("name") String name) {
        System.out.println("updateChannelAccount");
        ChannelAccount channelAccount =ChannelAccount
                .builder()
                .id(id)
                .name(name)
                .build();
        return channelAccountService.updateById(channelAccount);
    }


}
