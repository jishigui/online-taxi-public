package com.apipassenger.remote;


import com.yix.internalcommon.dto.PassengerUserDTO;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult loginOrReg(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    public ResponseResult getUserByPhone(@PathVariable("phone") String passengerPhone);
}
