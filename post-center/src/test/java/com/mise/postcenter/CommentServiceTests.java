package com.mise.postcenter;

import com.mise.postcenter.entity.Comment;
import com.mise.postcenter.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CommentServiceTests {


    @Autowired
    private CommentService commentService;


    @Test
    void testCreateComment() {

    }

    @Test
    void testGetCommentsByPostId() {
        List<Long> commentIds = commentService.getCommentIdsByPostId(200000L);
        for (Long id : commentIds) {
            System.out.println(commentService.getCommentById(id).getContent());
        }
    }

    @Test
    void testDeleteAllComments() {
        commentService.deleteAll();
    }
}
