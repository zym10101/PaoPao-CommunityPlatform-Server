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
        return R.success(post);
    }

    @GetMapping("/{id}")
    public R getPost(@PathVariable String id) {
        Post post = postService.getPostById(Long.valueOf(id));
        return R.success(post);
    }
}
