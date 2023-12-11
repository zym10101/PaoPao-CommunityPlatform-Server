package com.mise.usercenter.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mise.usercenter.client.PostClient;
import com.mise.usercenter.domain.entity.Comment;
import com.mise.usercenter.domain.entity.Post;
import com.mise.usercenter.domain.entity.User;
import com.mise.usercenter.domain.vo.CommentVO;
import com.mise.usercenter.domain.vo.PostVO;
import com.mise.usercenter.domain.vo.R;
import com.mise.usercenter.domain.vo.UserVO;
import com.mise.usercenter.mapper.UserMapper;
import com.mise.usercenter.service.UserService;
import com.mise.usercenter.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author whm
 * @date 2023/10/24 15:54
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final RedisCache redisCache;

    private final PostClient postClient;

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
    public String getAvatar(Long userId) {
        User user = userMapper.selectById(userId);
        return user.getPhoto();
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

    @Override
    public String publish(PostVO postVO) {
        R<Post> r = postClient.addPost(postVO);
        if (r.getCode() == 1) return "发布成功";
        else return "发布失败";
    }

    @Override
    public boolean comment(CommentVO commentVO) {
        R<Comment> r = postClient.addComment(commentVO);
        return r.getCode() == 1;
    }

    @Override
    public boolean up(String userId, String postId) {
        R<String> r = postClient.up(userId, postId);
        return r.getCode() == 1;
    }

    @Override
    public boolean down(String postId) {
        R<String> r = postClient.down(postId);
        return r.getCode() == 1;
    }

    @Override
    public List<Post> likes(String userId) {
        R<List<Post>> r = postClient.likes(userId);
        if (r.getCode() == 1) {
            return r.getData();
        }
        return null;
    }

    @Override
    public List<Post> posts(String userId) {
        R<List<Post>> r = postClient.getUserPosts(userId);
        if (r.getCode() == 1) {
            return r.getData();
        }
        return null;
    }

    @Override
    public List<Post> history(String userId) {
        R<List<Post>> r = postClient.histories(userId);
        if (r.getCode() == 1) {
            return r.getData();
        }
        return null;
    }

    @Override
    public String getUserNameById(String userId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) return null;
        return user.getUserName();
    }
}
