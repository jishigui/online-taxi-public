package com.yix.servicepassengeruser.controller;

import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.VerificationCodeDTO;
import com.yix.servicepassengeruser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        log.info("手机号:{}",passengerPhone);
        return userService.loginOrReg(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String passengerPhone) {
        log.info("passengerPhone{}",passengerPhone);
        return userService.getUserByPhone(passengerPhone);
    }
}
