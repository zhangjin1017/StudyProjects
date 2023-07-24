package com.zj.msgcenter.template;

import lombok.Data;

@Data
public class EmailTemplate {
    private String host;

    private String port;

    private String user;

    private String pass;

    private String from;


}
