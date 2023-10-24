package com.mise.postcenter.service;

import com.mise.postcenter.domain.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    public List<Post> getAllPosts();

    Post getPostById(Long id);

    List<Post> getPostByTitleContaining(String keyword);

    List<Post> getPostByTitleAndUserId(String title, Long userId);

    Post createPost(Post post);

    void deletePost(Long id);

    void deleteAll();

    void updatePost(Post post);

    List<Post> getSimilarPost(Post targetPost, List<Post> postList, int topK);

}
