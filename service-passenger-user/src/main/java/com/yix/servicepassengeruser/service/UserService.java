package com.yix.servicepassengeruser.service;

import com.yix.internalcommon.constant.CommonStatusEnum;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.VerificationCodeDTO;
import com.yix.servicepassengeruser.domain.PassengerUser;
import com.yix.servicepassengeruser.mapper.PassengerUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrReg(String passengerPhone) {

        log.info("userService被调用，手机号为:{}",passengerPhone);
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0));

        //判断用户信息是否存在

        if (passengerUsers.size() == 0) {
            //如果不存在，插入用户信息
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender(0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState(0);


            passengerUserMapper.insert(passengerUser);
        }

        //如果用户不存在，插入用户信息

        return ResponseResult.success();
    }

    /**
     * 根据手机号查询信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUserByPhone(String passengerPhone) {
        // 根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(passengerUsers.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getValue());
        }else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResponseResult.success(passengerUser);
        }
    }
}
