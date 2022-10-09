package com.apipassenger.controller;


import com.apipassenger.service.UserService;
import com.yix.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {

        // 从http请求中获取 accessToken
        String accessToken = request.getHeader("Authorization");


        // 根据accessToken查询

        return userService.getUserByAccessToken(accessToken);
    }
}
