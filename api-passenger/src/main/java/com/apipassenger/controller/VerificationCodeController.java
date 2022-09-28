package com.apipassenger.controller;


import com.apipassenger.remote.ServiceVefificationcodeClient;
import com.apipassenger.request.VerificationCodeDTO;
import com.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    ServiceVefificationcodeClient serviceVefificationcodeClient;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {

        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机号"+passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
