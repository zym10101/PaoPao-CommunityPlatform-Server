package com.mise.usercenter.service;

import com.mise.usercenter.domain.vo.CommentVO;
import com.mise.usercenter.domain.vo.PostVO;
import com.mise.usercenter.domain.vo.UserVO;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
public interface UserService {
    Long login(String userName, String password);

    String register(UserVO userVO);

    String update(UserVO userVO);

    Long getUserId(String userName);

    String editUserPhoto(String userName, String url);

    String publish(PostVO postVO);

    boolean comment(CommentVO commentVO);

    boolean up(String userId, String postId);

    boolean down(String postId);
}
