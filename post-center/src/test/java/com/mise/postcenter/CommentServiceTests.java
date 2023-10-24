package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Comment;
import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
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
        Comment comment = new Comment();
        comment.setCommentId(300000L);
        comment.setUserId(100006L);
        comment.setContent("评论1");
        comment.setPostId(200000L);
        comment.setCreateTime(new Date());
        commentService.createComment(comment);
        comment = new Comment();
        comment.setCommentId(300001L);
        comment.setUserId(100007L);
        comment.setContent("评论2");
        comment.setPostId(200000L);
        comment.setCreateTime(new Date());
        commentService.createComment(comment);
        comment = new Comment();
        comment.setCommentId(300002L);
        comment.setUserId(100008L);
        comment.setContent("评论3");
        comment.setPostId(200000L);
        comment.setCreateTime(new Date());
        commentService.createComment(comment);
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
