package com.apipassenger.service;

import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {

    /**
     * 根据出发地和目的地的经纬度计算预估价格
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        log.info("出发地经度{}",depLongitude);
        log.info("出发地维度{}",depLatitude);
        log.info("目的地经度{}",destLongitude);
        log.info("目的地维度{}",destLatitude);

        log.info("调用计价服务，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(13.14);
        return ResponseResult.success(forecastPriceResponse);
    }
}
