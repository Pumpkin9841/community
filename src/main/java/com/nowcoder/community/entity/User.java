package com.nowcoder.community.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String salt;

    /**
     * 
     */
    private String email;

    /**
     * 0-普通用户; 1-版主; 2-管理员;
     */
    private Integer type;

    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;

    /**
     * 
     */
    private String activationCode;

    /**
     * 
     */
    private String headerUrl;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}