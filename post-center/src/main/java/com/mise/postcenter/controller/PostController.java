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

    @PostMapping("/add")
    public R addPost(@RequestBody PostVO postVO) {
        Post post = postService.createPost(postVO);
        if (post != null) {
            return R.success(post);
        }
        return R.error("帖子发布失败！");
    }

    @GetMapping("/id")
    public R getPost(@RequestParam String postId) {
        Post post = postService.getPostById(Long.valueOf(postId));
        if (post != null) {
            return R.success(post);
        }
        return R.error("帖子查询失败！");
    }

}
