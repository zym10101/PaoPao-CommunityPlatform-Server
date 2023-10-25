package com.mise.postcenter.controller;

import com.mise.postcenter.common.R;
import com.mise.postcenter.entity.Comment;
import com.mise.postcenter.entity.Post;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
import com.mise.postcenter.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发布评论
     *
     * @param commentVO
     * @return
     */
    @PostMapping("/add")
    public R<Comment> addComment(@RequestBody CommentVO commentVO) {
        Comment comment = commentService.createComment(commentVO);
        if (comment == null) {
            return R.error("评论发布失败！");
        }
        return R.success(comment);
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    @PostMapping("/delete")
    public R<String> deleteComment(@RequestParam String commentId) {
        try {
            commentService.deleteComment(Long.valueOf(commentId));
        } catch (Exception e) {
            return R.error("评论删除失败！");
        }
        return R.success("评论删除成功！");
    }

    /**
     * 根据postId查询这条帖子中的评论
     *
     * @param postId
     * @return
     */
    @GetMapping("/get")
    public R<List<Comment>> getComments(@RequestParam String postId) {
        List<Comment> comments = commentService.getCommentsByPostId(Long.valueOf(postId));
        if (comments == null) {
            return R.error("评论查询失败！");
        }
        return R.success(comments);
    }
}
