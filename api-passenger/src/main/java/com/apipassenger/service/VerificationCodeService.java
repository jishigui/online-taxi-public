package com.apipassenger.service;


import com.apipassenger.remote.ServiceVefificationcodeClient;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.responese.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVefificationcodeClient serviceVefificationcodeClient;

    public String generatorCode(String passengerPhone) {
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeReponse = serviceVefificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeReponse.getData().getNumberCode();

        System.out.println("remote number code:"+numberCode);


        //存入redis
        System.out.println("存入redis");

        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
