package com.zj.msgcenter.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("send_record")
public class SendRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 渠道账号Id
     */
    private Integer channelAccountId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 发送的内容
     */
    private String msgContent;

    /**
     * 发送状态
     */
    private Integer status;

    /**
     * 回执信息
     */
    private String reportContent;
}
