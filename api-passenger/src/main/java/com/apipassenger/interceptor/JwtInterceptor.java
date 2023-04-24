package com.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yix.internalcommon.constant.TokenConstant;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.dto.TokenResult;
import com.yix.internalcommon.util.JwtUtils;
import com.yix.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");

        //解析token
        TokenResult tokenResult = JwtUtils.checkToken(token);


        if(tokenResult == null) {
            resultString = "token invalid";
            result = false;
            log.info("token为空");
        }else {
            //拼接token
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstant.ACCESS_TOKEN_TYPE);

            //从redis中取出token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if(StringUtils.isBlank(tokenRedis) || !token.trim().equals(tokenRedis.trim())) {
                resultString = "token invalid";
                result = false;
                log.info("token与redis中的token不一致");
            }
        }



        //比较我们传入的token和redis中token是否相等

        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }

        return result;
    }
}