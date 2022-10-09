package com.apipassenger.controller;

import com.apipassenger.service.TokenService;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.responese.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TokenController {


    @Autowired
    private TokenService tokenService;

    @PostMapping("token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {

        String refreshToken = tokenResponse.getRefreshToken();
        log.info("原来的 refreshToken{}",refreshToken);
        return tokenService.refreshToken(refreshToken);
    }
}
