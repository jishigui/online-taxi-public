package com.apipassenger.service;


import com.yix.internalcommon.constant.CommonStatusEnum;
import com.yix.internalcommon.constant.IdentityConstant;
import com.yix.internalcommon.constant.TokenConstant;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.dto.TokenResult;
import com.yix.internalcommon.responese.TokenResponse;
import com.yix.internalcommon.util.JwtUtils;
import com.yix.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc) {
        //解析refreshToken 是否合法
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        String phone = tokenResult.getPhone();
        String idetity = tokenResult.getIdentity();

        //读取redis中的refreshToken
        String refreshTokenkey = RedisPrefixUtils.generatorTokenKey(phone,idetity, TokenConstant.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenkey);

        //校验refreshToken
        if (StringUtils.isBlank(refreshTokenRedis) || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))) {
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());

        }

        //生成双token
        String refreshToken = JwtUtils.generatorToken(phone,idetity,TokenConstant.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone,idetity,TokenConstant.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone,idetity,TokenConstant.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        stringRedisTemplate.opsForValue().set(refreshTokenkey, accessToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);


        return ResponseResult.success(tokenResponse);
    }
}
