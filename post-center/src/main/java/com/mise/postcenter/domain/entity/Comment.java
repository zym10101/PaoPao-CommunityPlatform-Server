package com.mise.postcenter.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comments")
@Data
public class Comment {

    @Id
    private Long commentId;
    private Long userId;
    private String content;
    private Long postId;
    private Date createTime;
}
