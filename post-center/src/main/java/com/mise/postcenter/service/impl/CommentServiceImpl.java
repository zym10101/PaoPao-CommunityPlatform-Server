package com.mise.postcenter.service.impl;

import com.mise.postcenter.domain.entity.Comment;
import com.mise.postcenter.repository.CommentRepository;
import com.mise.postcenter.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Long> getCommentIdsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        List<Long> list = new ArrayList<>();
        for (Comment comment : comments) {
            list.add(comment.getCommentId());
        }
        return list;
    }
}
