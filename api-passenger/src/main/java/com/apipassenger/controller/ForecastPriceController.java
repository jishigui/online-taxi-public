package com.apipassenger.controller;

import com.apipassenger.service.ForecastPriceService;
import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.ForecastPriceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {

    @Autowired
    ForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();

        log.info("出发地经度{}",depLongitude);
        log.info("出发地维度{}",depLatitude);
        log.info("目的地经度{}",destLongitude);
        log.info("目的地维度{}",destLatitude);

        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);
    }
}
