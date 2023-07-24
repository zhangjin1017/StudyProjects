package com.zj.msgcenter.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zj.msgcenter.template.EmailTemplate;
import org.springframework.context.annotation.Configuration;
@Configuration
public class EmailUtil {

    public static EmailTemplate templateparser(String template) {
        EmailTemplate emailTemplate = new EmailTemplate();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            emailTemplate = objectMapper.readValue(template, EmailTemplate.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailTemplate;

    }
}
