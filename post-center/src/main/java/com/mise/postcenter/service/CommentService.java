package com.mise.postcenter.service;

import com.mise.postcenter.domain.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Comment comment);

    List<Long> getCommentIdsByPostId(Long postId);
}
