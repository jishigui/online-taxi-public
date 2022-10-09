package com.yix.servicemap.service;

import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.responese.DirectionResponse;
import com.yix.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 根据起点经纬度和终点经纬度获取距离（米）和时长（分钟）
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        mapDirectionClient.direction(depLongitude,depLatitude,destLongitude,destLatitude);

        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);

        return ResponseResult.success(directionResponse);
    }
}
