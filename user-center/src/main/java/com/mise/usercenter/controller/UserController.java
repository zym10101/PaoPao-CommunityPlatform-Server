package com.mise.usercenter.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mise.usercenter.domain.vo.CommentVO;
import com.mise.usercenter.domain.vo.PostVO;
import com.mise.usercenter.domain.vo.Response;
import com.mise.usercenter.domain.vo.UserVO;
import com.mise.usercenter.service.OssService;
import com.mise.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author whm
 * @date 2023/10/24 12:33
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final OssService ossService;

    @RequestMapping("/login")
    public Response login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        Long id = userService.login(userName, password);
        if (id != null) {
            StpUtil.login(id);
            SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
            return Response.success(200, "登录成功！", saTokenInfo);
        } else {
            return Response.failed(999, "用户名或密码错误！");
        }
    }

    @RequestMapping("/logout")
    public Response logout() {
        StpUtil.logout();
        return Response.success(200, "退出成功！");
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserVO userVO) {
        String res = userService.register(userVO);
        if (res.equals("注册成功")) {
            return Response.success(200, "注册成功！");
        } else {
            return Response.failed(999, res);
        }
    }

    /**
     * 修改密码
     * @param userVO
     * @return
     */
    @PostMapping("/update")
    public Response update(@RequestBody UserVO userVO) {
        String res = userService.update(userVO);
        if (res.equals("修改密码成功")) {
            return Response.success(200, "修改密码成功！");
        } else {
            return Response.failed(999, res);
        }
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/edit")
    public Response edit(@RequestParam("userName") String userName, @RequestParam("newPhoto") MultipartFile file) {
        String url = ossService.uploadFile(file);
        String res = userService.editUserPhoto(userName, url);
        if (res.equals("用户不存在")) {
            return Response.failed(999, res);
        } else {
            return Response.success(200, res, url);
        }
    }

    /**
     * 用户发布帖子
     */
    @PostMapping("/publish")
    public Response publish(@RequestBody PostVO postVO) {
        if (StpUtil.isLogin()) {
            String res = userService.publish(postVO);
            if (res.equals("发布成功")) {
                return Response.success(200, res);
            } else {
                return Response.failed(999, res);
            }
        }
        return Response.failed(999, "用户未登录，无法发布帖子！");
    }

    /**
     * 用户发布评论
     */
    @PostMapping("/comment")
    public Response comment(@RequestBody CommentVO commentVO) {
        if (StpUtil.isLogin()) {
            boolean res = userService.comment(commentVO);
            return res ? Response.success(200, "评论成功！") : Response.failed(999, "评论失败！");
        }
        return Response.failed(999, "用户未登录，无法评论！");
    }

    /**
     * 用户点赞帖子
     */
    @PostMapping("/like")
    public Response like(@RequestParam("postId") String postId) {
        if (StpUtil.isLogin()) {
            String userId = StpUtil.getLoginIdAsString();
            boolean res = userService.up(userId, postId);
            return res ? Response.success(200, "点赞成功！") : Response.failed(999, "点赞失败！");
        }
        return Response.failed(999, "用户未登录，无法点赞！");
    }

    /**
     * 用户点踩帖子
     */
    @PostMapping("/dislike")
    public Response dislike(@RequestParam("postId") String postId) {
        if (StpUtil.isLogin()) {
            boolean res = userService.down(postId);
            return res ? Response.success(200, "点踩成功！") : Response.failed(999, "点踩失败！");
        }
        return Response.failed(999, "用户未登录，无法点踩！");
    }
}
