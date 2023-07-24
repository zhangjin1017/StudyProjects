package com.zj.msgcenter.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("message_template")
public class MessageTemplate{

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模板标题
     */
    private String name;

    /**
     * 屏蔽类型
     */
    private Integer shieldType;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 推送消息的时间
     * 0：立即发送
     * else：crontab 表达式
     */
    private String expectPushTime;
    /**
     * 发送渠道
     */
    private Integer sendChannel;


    /**
     * 消息内容  {$var} 为占位符
     */
    private String msgContent;

    /**
     * 是否删除
     * 0：未删除
     * 1：已删除
     */
    @TableLogic
    private Integer isDeleted;


}
