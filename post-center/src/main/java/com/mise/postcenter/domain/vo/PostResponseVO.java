package com.mise.postcenter.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PostResponseVO implements Serializable {

    private String postId;
    private String communityId;
    private Boolean isPublic;
    private List<String> tagList;
    private String title;
    private String content;
    private String photo;
    private String commentNum;
    private String likeNum;
    private String dislikeNum;
    private String userName;
    private Date createTime;
    private Date lastUpdateTime;
}
