package com.zj.msgcenter.Sender;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.mail.util.MailSSLSocketFactory;
import com.zj.msgcenter.Entity.ChannelAccount;
import com.zj.msgcenter.Util.EmailUtil;
import com.zj.msgcenter.Util.FileUtil;
import com.zj.msgcenter.template.EmailTemplate;
import jodd.mail.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.concurrent.CompletableFuture;

import static jodd.mail.CommonEmail.PRIORITY_HIGHEST;

@Component
public class JoddMailSender {

    public SmtpServer getSmtpServer(EmailTemplate emailTemplate){

        SmtpServer smtpServer = MailServer.create()
                .host(emailTemplate.getHost())
                //默认端口25
                .port(Integer.parseInt(emailTemplate.getPort()))
                .auth(emailTemplate.getUser(), emailTemplate.getPass())
                .timeout(3000)
                .ssl(true)
                .buildSmtpMailServer();

        return smtpServer;
    }


    public void sendMail(ChannelAccount channelAccount, String title,String content,String sendTo) {
        CompletableFuture.runAsync(() -> {
            EmailTemplate emailTemplate = EmailUtil.templateparser(channelAccount.getAccountConfig());

            SmtpServer smtpServer = getSmtpServer(emailTemplate);
            //将sendTo以逗号分隔转换成数组
            String[] recipients = sendTo.split(",");

            if (StringUtils.isNotBlank(sendTo)) {
                Email email = Email.create()
//                        .from(channelAccount.getName(), emailTemplate.getFrom())
                        .from(emailTemplate.getFrom())
                        //可以同时发送给多人
                        .to(recipients)
                        .subject(title)
                        .htmlMessage(content)
                        .priority(PRIORITY_HIGHEST);
               
                SendMailSession sendMailSession = smtpServer.createSession();
                sendMailSession.open();
                //可以同时发送多封邮件
                sendMailSession.sendMail(email);
                sendMailSession.close();
            }
        }).thenAccept(result -> {
            System.out.println("邮件发送完成");
        });
    }

    public void sendAttachMail(ChannelAccount channelAccount, String title, String content, String sendTo, MultipartFile multipartFile) {
        CompletableFuture.runAsync(() -> {
            EmailTemplate emailTemplate = EmailUtil.templateparser(channelAccount.getAccountConfig());

            SmtpServer smtpServer = getSmtpServer(emailTemplate);
            //将sendTo以逗号分隔转换成数组
            String[] recipients = sendTo.split(",");

            if (StringUtils.isNotBlank(sendTo)) {
                File file= null;
                try {
                    System.out.println(multipartFile.getOriginalFilename());
                    file = FileUtil.multipartFileToFile(multipartFile);
                    System.out.println(file.getName());
                    Email email = Email.create()
//                        .from(channelAccount.getName(), emailTemplate.getFrom())
                            .from(emailTemplate.getFrom())
                            //可以同时发送给多人
                            .to(recipients)
                            .subject(title)
                            .htmlMessage(content)
                            .embeddedAttachment(EmailAttachment.with()
                                    .content(file))
                            // 附件
                            .attachment(EmailAttachment.with()
                                    .content(file))
                            .priority(PRIORITY_HIGHEST);

                    SendMailSession sendMailSession = smtpServer.createSession();
                    sendMailSession.open();
                    //可以同时发送多封邮件
                    sendMailSession.sendMail(email);
                    sendMailSession.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    FileUtil.deleteTempFile(file);
                }
            }
        }).thenAccept(result -> {
            System.out.println("邮件发送完成");
        });
    }




}
