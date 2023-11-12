package com.mise.postcenter.controller;

import com.mise.postcenter.common.R;
import com.mise.postcenter.domain.entity.Comment;
import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.domain.vo.CommentVO;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
import com.mise.postcenter.domain.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    /**
     * 发布帖子
     *
     * @param postVO
     * @return
     */
    @PostMapping("/addPost")
    public R<Post> addPost(@RequestBody PostVO postVO) {
        Post post = postService.createPost(postVO);
        if (post == null) {
            return R.error("帖子发布失败！");
        }
        return R.success(post);
    }


    /**
     * 删除帖子
     *
     * @param postId
     * @return
     */
    @PostMapping("/deletePost")
    public R<String> deletePost(@RequestParam String postId) {
        try {
            postService.deletePost(Long.valueOf(postId));
        } catch (Exception e) {
            return R.error("帖子删除失败！");
        }
        return R.success("帖子删除成功！");
    }

    /**
     * 根据communityId查找其中的所有帖子
     *
     * @param communityId
     * @return
     */
    @GetMapping("/getPosts")
    public R<List<Post>> getPosts(@RequestParam String communityId) {
        List<Post> posts = postService.getPostsByCommunityId(Long.valueOf(communityId));
        if (posts.isEmpty()) {
            return R.error("该社区中没有帖子！");
        }
        return R.success(posts);
    }


    /**
     * 发布评论
     *
     * @param commentVO
     * @return
     */
    @PostMapping("/addComment")
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
    @PostMapping("/deleteComment")
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
    @GetMapping("/getComments")
    public R<List<Comment>> getComments(@RequestParam String postId) {
        List<Comment> comments = commentService.getCommentsByPostId(Long.valueOf(postId));
        if (comments.isEmpty()) {
            return R.error("该帖子没有评论！");
        }
        return R.success(comments);
    }

    @PostMapping("/up")
    public R<String> up(@RequestParam String userId, @RequestParam String postId) {
        boolean up = postService.up(Long.valueOf(userId), Long.valueOf(postId));
        if (up) {
            return R.success("点赞成功");
        }
        return R.error("点赞失败");
    }

    @PostMapping("/down")
    public R<String> down(@RequestParam String postId) {
        boolean down = postService.down(Long.valueOf(postId));
        if (down) {
            return R.success("点踩成功");
        }
        return R.error("点踩失败");
    }
}
