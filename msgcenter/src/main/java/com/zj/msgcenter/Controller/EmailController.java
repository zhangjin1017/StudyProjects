package com.zj.msgcenter.Controller;

import com.zj.msgcenter.Entity.ChannelAccount;
import com.zj.msgcenter.Sender.JoddMailSender;
import com.zj.msgcenter.Service.ChannelAccountService;
import com.zj.msgcenter.Util.EmailUtil;
import com.zj.msgcenter.template.EmailTemplate;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Transport;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private ChannelAccountService channelAccountService;


    @RequestMapping("/send")
    public String send(@RequestParam("channelAccountId") Long channelAccountId,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content,
                       @RequestParam("sendTo") String sendTo
    ) {
        ChannelAccount channelAccount = channelAccountService.getById(channelAccountId);
        if (channelAccount == null) {
            return "error";
        }
        if (channelAccount.getSendChannel()==1) {
            JoddMailSender joddMailSender = new JoddMailSender();
            joddMailSender.sendMail(channelAccount, title, content, sendTo);
        }
        return "success";
    }
    @RequestMapping("/sendAttachMail")
    public String sendAttachMail(@RequestParam("channelAccountId") Long channelAccountId,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content,
                       @RequestParam("sendTo") String sendTo,
                       @RequestParam("file") MultipartFile file
    ) {
        ChannelAccount channelAccount = channelAccountService.getById(channelAccountId);
        if (channelAccount == null) {
            return "error";
        }
        if (channelAccount.getSendChannel()==1) {
            JoddMailSender joddMailSender = new JoddMailSender();
            joddMailSender.sendAttachMail(channelAccount, title, content, sendTo,file);
        }
        return "success";
    }

    //测试邮件配置是否正确
    @RequestMapping("/test/{id}")
    public String test(@PathVariable("id") Long id) {
        ChannelAccount channelAccount = channelAccountService.getById(id);
        JoddMailSender joddMailSender = new JoddMailSender();
        EmailTemplate emailTemplate = EmailUtil.templateparser(channelAccount.getAccountConfig());
        SmtpServer smtpServer = joddMailSender.getSmtpServer(emailTemplate);
        SendMailSession sendMailSession = smtpServer.createSession();
        sendMailSession.open();

        Transport service = sendMailSession.getService();
        System.out.println(service.toString());
        sendMailSession.close();


        return service.toString();
    }
}
