package com.zj.msgcenter.daf;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

public class Test {

    public HashSet isSensitive(String words,Integer type) {
        HashSet<String> set = new HashSet<>();
        //这里我的敏感词存在数据库 大家结合自己的业务需求改动 可以存在txt文件中或者excel

        return SensitiveFilterUtil.checkTxt(words, set);
    }
}
