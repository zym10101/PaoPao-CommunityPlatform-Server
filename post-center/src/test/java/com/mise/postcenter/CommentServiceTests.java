package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Comment;
import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentServiceTests {


    @Autowired
    private CommentService commentService;


    @Test
    void testCreateComment() {
        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setPostId(2L);
        comment.setContent("不错！");
        commentService.createComment(comment);
        comment = new Comment();
        comment.setCommentId(2L);
        comment.setPostId(2L);
        comment.setContent("真不错！");
        commentService.createComment(comment);
    }

    @Test
    void testGetCommentIdsByPostId() {
        List<Long> comments = commentService.getCommentIdsByPostId(2L);
        System.out.println(comments);
    }
}
