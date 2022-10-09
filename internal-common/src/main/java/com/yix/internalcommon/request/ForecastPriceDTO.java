package com.yix.internalcommon.request;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;
}
