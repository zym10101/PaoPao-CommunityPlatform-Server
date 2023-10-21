package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostCenterApplicationTests {

    @Autowired
    private PostService postService;

    @Test
    void testCreatePost() {
        Post post = new Post();
        post.setPostId(2L);
        postService.createPost(post);
        System.out.println(post);
    }

    @Test
    void testDeletePost() {
        postService.deletePost(2L);
    }

    @Test
    void testUpdatePost() {
        Post post = postService.getPostById(1L);
        postService.updatePost(post);
        System.out.println(post);
    }

    @Test
    void testGetAllPosts() {
        List<Post> allPosts = postService.getAllPosts();
        System.out.println(allPosts.size());
        for (Post post : allPosts) {
            System.out.println(post);
        }
    }

}
