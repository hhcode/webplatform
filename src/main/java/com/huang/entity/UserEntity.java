package com.huang.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author huangjihui
 * @Date 2018/9/7 11:08
 */
@Data
public class UserEntity {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
}
