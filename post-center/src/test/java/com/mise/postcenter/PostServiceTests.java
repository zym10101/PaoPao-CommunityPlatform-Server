package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    void testCreatePost() {
        Post post = new Post();
        post.setPostId(1L);
        post.setUserId(101L);
        post.setTitle("哈哈哈");
        postService.createPost(post);
        post = new Post();
        post.setPostId(2L);
        post.setUserId(101L);
        post.setTitle("嘿嘿嘿");
        postService.createPost(post);
        post = new Post();
        post.setPostId(3L);
        post.setUserId(102L);
        post.setTitle("嘿嘿嘿");
        postService.createPost(post);
        post = new Post();
        post.setPostId(4L);
        post.setUserId(103L);
        post.setTitle("嘿嘿嘿哈哈哈");
        postService.createPost(post);
        System.out.println(post);
    }

    @Test
    void testDeletePost() {
        postService.deletePost(2L);
    }

    @Test
    void testDeleteAll() {
        postService.deleteAll();
    }

    @Test
    void testUpdatePost() {
        Post post = postService.getPostById(1L);
        postService.updatePost(post);
        System.out.println(post);
    }


    @Test
    void testGetPostByTitle() {
        System.out.println(postService.getPostByTitleAndUserId("嘿嘿嘿", 101L));
    }

    @Test
    void testGetPostByTitleContaining() {
        List<Post> posts = postService.getPostByTitleContaining("嘿");
        for (Post post : posts) {
            System.out.println(post);
        }
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
