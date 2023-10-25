package com.mise.usercenter.service;

import com.mise.usercenter.domain.vo.UserVO;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
public interface UserService {
    boolean login(String userName, String password);

    String register(UserVO userVO);
}
