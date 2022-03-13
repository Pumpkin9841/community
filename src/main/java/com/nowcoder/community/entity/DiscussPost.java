package com.nowcoder.community.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName discuss_post
 */
@Data
public class DiscussPost implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String userId;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String content;

    /**
     * 0-普通; 1-置顶;
     */
    private Integer type = 0;

    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    private Integer status = 0;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer commentCount;

    /**
     * 
     */
    private Double score;

    private static final long serialVersionUID = 1L;
}