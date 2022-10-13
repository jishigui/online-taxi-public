package com.yix.serviceprice.controller;


import com.yix.internalcommon.dto.ResponseResult;
import com.yix.internalcommon.request.ForecastPriceDTO;
import com.yix.serviceprice.service.ForeCastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastPriceController {

    @Autowired
    private ForeCastPriceService foreCastPriceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

        return foreCastPriceService.forecastPrice(forecastPriceDTO);
    }
}
