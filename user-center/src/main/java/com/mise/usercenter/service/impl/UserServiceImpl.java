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
    public Long login(String userName, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return null;
        }
        user.setLastLoginTime(new Date());
        userMapper.updateById(user);
        return user.getUserId();
    }

    @Override
    public String register(UserVO userVO) {
        String captcha = redisCache.getCacheObject(userVO.getPhone());
        if (!userVO.getVerifyCode().equals(captcha)) {
            return "验证码错误";
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userVO.getUserName());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return "用户名已存在";
        }

        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, userVO.getPhone());
        user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return "该手机号已绑定用户";
        }

        user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(BCrypt.hashpw(userVO.getPassword()));
        user.setPhone(userVO.getPhone());
        user.setCreateTime(new Date());
        user.setPhoto("https://kiyotakawang.oss-cn-hangzhou.aliyuncs.com/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F.jpg");
        userMapper.insert(user);
        return "注册成功";
    }

    @Override
    public String update(UserVO userVO) {
        String captcha = redisCache.getCacheObject(userVO.getPhone());
        if (!userVO.getVerifyCode().equals(captcha)) {
            return "验证码错误";
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, userVO.getPhone());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return "该手机号未绑定用户";
        }
        user.setPassword(BCrypt.hashpw(userVO.getPassword()));
        userMapper.updateById(user);
        return "修改密码成功";
    }

    @Override
    public Long getUserId(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) return null;
        return user.getUserId();
    }

    @Override
    public String editUserPhoto(String userName, String url) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return "用户不存在";
        }
        user.setPhoto(url);
        userMapper.updateById(user);
        return "修改用户头像成功";
    }
}
