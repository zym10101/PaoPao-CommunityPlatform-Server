package com.mise.postcenter;

import com.mise.postcenter.domain.entity.Post;
import com.mise.postcenter.service.PostService;
import com.mise.postcenter.domain.vo.PostVO;
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
        PostVO postVO = new PostVO();
        postVO.setCommunityId("400000");
        postVO.setIsPublic(true);
        postVO.setTagList(List.of("数据库", "安全"));
        postVO.setTitle("题目");
        postVO.setContent("内容");
        postVO.setPhoto("图片地址");
        postVO.setUserId("100001");
        postService.createPost(postVO);
    }

    @Test
    void testGetLastPostId() {
        System.out.println(postService.getLastPostId());
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
