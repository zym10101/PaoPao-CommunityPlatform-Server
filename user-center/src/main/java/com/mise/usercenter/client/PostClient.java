package com.mise.usercenter.client;

import com.mise.usercenter.domain.entity.Comment;
import com.mise.usercenter.domain.entity.Post;
import com.mise.usercenter.domain.vo.CommentVO;
import com.mise.usercenter.domain.vo.PostVO;
import com.mise.usercenter.domain.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author whm
 * @date 2023/11/26 18:22
 */
@FeignClient("post-service")
@RequestMapping("/post")
public interface PostClient {
    @PostMapping("/addPost")
    R<Post> addPost(@RequestBody PostVO postVO);

    @PostMapping("/addComment")
    R<Comment> addComment(@RequestBody CommentVO commentVO);

    @PostMapping("/up")
    R<String> up(@RequestParam String userId, @RequestParam String postId);

    @PostMapping("/down")
    R<String> down(@RequestParam String postId);
}
