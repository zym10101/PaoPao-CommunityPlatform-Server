package com.mise.postcenter.controller;

import com.mise.postcenter.common.R;
import com.mise.postcenter.entity.Post;
import com.mise.postcenter.service.PostService;
import com.mise.postcenter.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * 发布帖子
     *
     * @param postVO
     * @return
     */
    @PostMapping("/add")
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
    @PostMapping("/delete")
    public R<String> deletePost(@RequestParam String postId) {
        try {
            postService.deletePost(Long.valueOf(postId));
        } catch (Exception e) {
            return R.error("帖子删除失败！");
        }
        return R.success("帖子删除成功！");
    }

    /**
     * 根据postId查找帖子
     *
     * @param postId
     * @return
     */
    @GetMapping("/get")
    public R<Post> getPost(@RequestParam String postId) {
        Post post = postService.getPostById(Long.valueOf(postId));
        if (post == null) {
            return R.error("帖子查询失败！");
        }
        return R.success(post);
    }

}
