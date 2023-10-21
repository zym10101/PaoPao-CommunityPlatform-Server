package com.mise.postcenter.service;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        post.setCreateTime(new Date());
        post.setLastUpdateTime(new Date());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void updatePost(Post post) {
        post.setLastUpdateTime(new Date());
        postRepository.save(post);
    }
}
