package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.CommentService;
import com.mise.postcenter.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PostServiceTests {

    @Autowired
    private PostService postService;

    @Test
    void testCreatePost() {
        Post post = new Post();
        post.setPostId(200000L);
        post.setCommunityId(400000L);
        post.setIsPublic(true);
        post.setTagList(List.of("数据库", "安全"));
        post.setTitle("题目");
        post.setContent("内容");
        post.setPhoto("图片地址");
        post.setCommentList(List.of(300000L, 300001L, 300002L));
        post.setUserId(100000L);
        post.setCreateTime(new Date());
        post.setLastUpdateTime(new Date());
        postService.createPost(post);
    }

    @Test
    void testDeletePost() {
        postService.deletePost(200000L);
    }

    @Test
    void testDeleteAll() {
        postService.deleteAll();
    }

    @Test
    void testUpdatePost() {
        Post post = postService.getPostById(200000L);
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
