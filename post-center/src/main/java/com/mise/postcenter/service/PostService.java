package com.mise.postcenter.service;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.domain.vo.PostVO;

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

    List<Post> getPostsByCommunityId(Long communityId);

    List<Post> getPostsByUserId(String userId);

    List<Post> getSimilarPost(Post targetPost, List<Post> postList, int topK);

    boolean up(Long userId, Long postId);

    boolean down(Long postId);

    List<Post> getLikesByUserId(Long userId);
    List<Post> getHistoriesByUserId(Long userId);
}
