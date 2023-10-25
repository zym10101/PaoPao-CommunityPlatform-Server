package com.mise.usercenter.controller;

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
public class LoginController {
    private final UserService userService;

    @RequestMapping("/login")
    public Response login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        boolean login = userService.login(userName, password);
        if (login) {
            StpUtil.login(userName);
            return Response.success(200, "登录成功！");
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
}
