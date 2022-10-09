package com.apipassenger.service;

import com.apipassenger.remote.ServicePassengerUserClient;
import com.yix.internalcommon.dto.PassengerUserDTO;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.dto.TokenResult;
import com.yix.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired

    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken{}",accessToken);
        // 解析accessToken，拿到手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号:{}",phone);

        // 根据手机号查询用户信息
        ResponseResult<PassengerUserDTO> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone);
    }
}
