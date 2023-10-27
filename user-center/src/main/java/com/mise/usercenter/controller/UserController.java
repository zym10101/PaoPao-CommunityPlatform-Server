package com.mise.usercenter.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mise.usercenter.domain.vo.Response;
import com.mise.usercenter.domain.vo.UserVO;
import com.mise.usercenter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author whm
 * @date 2023/10/24 12:33
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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
}
