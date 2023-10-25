package com.mise.usercenter.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.domain.vo.UserVO;
import com.mise.usercenter.mapper.UserMapper;
import com.mise.usercenter.service.UserService;
import com.mise.usercenter.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final RedisCache redisCache;

    @Override
    public boolean login(String userName, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return false;
        }
        user.setLastLoginTime(new Date());
        userMapper.updateById(user);
        return true;
    }

    @Override
    public String register(UserVO userVO) {
        String code = redisCache.getCacheObject(userVO.getPhone());
        if (!userVO.getVerifyCode().equals(code)) {
            return "验证码错误";
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userVO.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return "用户名已存在";
        }
        user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(BCrypt.hashpw(userVO.getPassword()));
        user.setPhone(userVO.getPhone());
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return "注册成功";
    }
}
