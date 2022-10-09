package com.apipassenger.controller;


import com.apipassenger.remote.ServiceVefificationcodeClient;
import com.apipassenger.service.VerificationCodeService;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    ServiceVefificationcodeClient serviceVefificationcodeClient;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号"+passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public  ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        log.info("手机号{passengerPhone}:,验证码:{verificationCode}",passengerPhone,verificationCode);

        return verificationCodeService.checkCode(verificationCodeDTO.getPassengerPhone(),verificationCodeDTO.getVerificationCode());
    }




}
