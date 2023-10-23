package com.mise.postcenter.service.impl;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.repository.PostRepository;
import com.mise.postcenter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostByTitleContaining(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public List<Post> getPostByTitleAndUserId(String title, Long userId) {
        return postRepository.findByTitleAndUserId(title, userId);
    }

    public Post createPost(Post post) {
        post.setCreateTime(new Date());
        post.setLastUpdateTime(new Date());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public void updatePost(Post post) {
        post.setLastUpdateTime(new Date());
        postRepository.save(post);
    }
}
