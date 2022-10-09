package com.yix.serviceverificationcode.controller;

import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.responese.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {

        System.out.println("size:" + size);
        //生成验证码
        double mathRandow = (Math.random()*9 + 1) * (Math.pow(10,size - 1));
        int resultInt = (int) mathRandow;
        System.out.println("com.yix.servicepassengeruser.generator src code:"+resultInt);
//        JSONObject result = new JSONObject();
//        result.put("code", 1);
//        result.put("message", "success");
//        JSONObject data = new JSONObject();
//        data.put("numberCode", (int)mathRandow);
//        result.put("data",data );

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        return ResponseResult.success(response);
    }
}
