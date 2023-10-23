package com.mise.postcenter.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "posts")
@Data
public class Post {

    @Id
    private Long postId;
    private Long communityId;
    private Boolean isPublic;
    private List<Long> tagList;
    private String title;
    private String content;
    private String photo;
    private List<Long> commentList;
    private Long userId;
    private Date createTime;
    private Date lastUpdateTime;

}