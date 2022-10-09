package com.apipassenger.service;


import com.apipassenger.remote.ServicePassengerUserClient;
import com.apipassenger.remote.ServiceVefificationcodeClient;
import com.yix.internalcommon.constant.CommonStatusEnum;
import com.yix.internalcommon.constant.IdentityConstant;
import com.yix.internalcommon.constant.TokenConstant;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.VerificationCodeDTO;
import com.yix.internalcommon.responese.NumberCodeResponse;
import com.yix.internalcommon.responese.TokenResponse;
import com.yix.internalcommon.util.JwtUtils;
import com.yix.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {

    @Autowired
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    //乘客验证码的前缀
    private String verificationCodePrefix = "passenger-verification-code-";

    //token存储的前缀
    private String tokenPrefix = "token-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public ResponseResult generatorCode(String passengerPhone) {
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeReponse = serviceVefificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeReponse.getData().getNumberCode();

        System.out.println("remote number code:"+numberCode);

        //存入redis
        System.out.println("存入redis");
        //key,value,过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务器，将对应的验证码发送到手机上，阿里短信服务，腾讯短信通，华信，容联云

        return ResponseResult.success("");
    }


    /**
     *
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {
        //根据手机号，去redis读取验证码
        log.info("根据手机号，去redis读取验证码");

        //生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);

        // 根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中value:" + codeRedis);

        // 校验验证码
        log.info("校验验证码");
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if ( !verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        // 判断原来是否有用户，并进行对应的处理
        log.info("判断原来是否有用户，并进行对应的处理");
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrReg(verificationCodeDTO);

        // 颁发令牌
        log.info("颁发令牌");
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        //将token存到redis中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstant.PASSENGER_IDENTITY, TokenConstant.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, accessToken, 31, TimeUnit.DAYS);

        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
