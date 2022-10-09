package com.yix.servicepassengeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yix.servicepassengeruser.domain.PassengerUser;
import com.yix.servicepassengeruser.service.PassengerUserService;
import com.yix.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 86183
* @description 针对表【passenger_user】的数据库操作Service实现
* @createDate 2022-10-03 19:38:37
*/
@Service
public class PassengerUserServiceImpl extends ServiceImpl<PassengerUserMapper, PassengerUser>
    implements PassengerUserService{

}




