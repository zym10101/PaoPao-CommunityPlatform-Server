package com.mise.postcenter.service;

import com.mise.postcenter.domain.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment createComment(Comment comment);

    List<Long> getCommentIdsByPostId(Long postId);

    Comment getCommentById(Long id);

    void deleteAll();
}
