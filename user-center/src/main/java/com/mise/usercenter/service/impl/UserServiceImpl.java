package com.mise.usercenter.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.mapper.UserMapper;
import com.mise.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return false;
        }
        return true;
    }
}
