package com.zj.msgcenter.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//@Component
public class MailSender {



    @Autowired
    private JavaMailSender mailSender;





    /**
     * 发送简单文本的邮件方法
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
//        message.setFrom(from);
        message.setSubject(subject);
        message.setText(content);
 
        mailSender.send(message);
    }
 
    /**
     * 发送HTML邮件的方法
     * @param to
     * @param subjecr
     * @param content
     */
    public void sendHtmlMail(String to ,String subjecr,String content){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
//            helper.setFrom(from);
            helper.setSubject(subjecr);
            helper.setText(content,true);
            mailSender.send(message);

        } catch (MessagingException e) {

        }
 
    }
}