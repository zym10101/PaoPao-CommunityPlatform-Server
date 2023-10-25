package com.mise.postcenter.repository;

import com.mise.postcenter.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findCommentsByPostId(Long postId);
}