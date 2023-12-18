package com.mise.usercenter.service;

import com.mise.usercenter.domain.entity.Post;
import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.domain.vo.*;
import com.mise.usercenter.domain.vo.community.CommunityVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
public interface UserService {
    Long login(String userName, String password);

    String register(UserVO userVO);

    String update(UserVO userVO);

    Long getUserId(String userName);

    String getAvatar(Long userId);

    String editUserPhoto(String userName, String url);

    String publish(PostVO postVO);

    boolean comment(CommentVO commentVO);

    boolean up(String userId, String postId);

    boolean up_back(String userId, String postId);

    boolean down(String postId);

    boolean down_back(String postId);

    List<PostResponseVO> likes(String userId);

    List<PostResponseVO> posts(String userId);

    List<Post> history(String userId);

    List<PostResponseVO> getRecentPosts();

    List<CommentResponseVO> getComment(String postId);

    Map<CommunityVO, List<User>> getApplicationByAdminId(long adminID);

}
