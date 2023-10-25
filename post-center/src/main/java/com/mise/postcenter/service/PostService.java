package com.mise.postcenter.service;

import com.mise.postcenter.entity.Post;
import com.mise.postcenter.vo.PostVO;

import java.util.List;


public interface PostService {

    public List<Post> getAllPosts();

    Post getPostById(Long id);

    List<Post> getPostByTitleContaining(String keyword);

    List<Post> getPostByTitleAndUserId(String title, Long userId);

    Post createPost(PostVO postVO);

    void deletePost(Long id);

    void deleteAll();

    void updatePost(Post post);

    Long getLastPostId();

    List<Post> getSimilarPost(Post targetPost, List<Post> postList, int topK);

}
