package com.yix.servicemap.remote;


import com.yix.internalcommon.constant.AmapConfigConstants;
import com.yix.internalcommon.responese.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        // 组装请求调用url

        StringBuilder urlBuilder = new StringBuilder();



        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin="+depLongitude+","+depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination="+destLongitude+","+destLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key="+amapKey);
        log.info(urlBuilder.toString());


        // 调用高德接口

        // 解析接口

        return null;
    }
}
