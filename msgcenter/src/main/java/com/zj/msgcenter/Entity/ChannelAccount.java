package com.zj.msgcenter.Entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
@TableName("channel_account")
public class ChannelAccount {


    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账号名称
     */
    private String name;

    /**
     * 发送渠道类型
     */
    private int sendChannel;

    /**
     * 账号配置
     */
    private String accountConfig;

    /**
     * 0：未删除
     * 1：已删除
     */
    @TableLogic
    private Integer isDeleted;

    /** * 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /*** 修改时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
