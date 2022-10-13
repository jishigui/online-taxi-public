package com.yix.internalcommon.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @TableName price_rule
 */
@Data
@ToString
public class PriceRule implements Serializable {
    /**
     * 
     */
    private String cityCode;

    /**
     * 
     */
    private String vehicleType;

    /**
     * 
     */
    private Double startFare;

    /**
     * 
     */
    private Integer startMile;

    /**
     * 
     */
    private Double unitPricePerMile;

    /**
     * 
     */
    private Double unitPricePerMinute;

    private static final long serialVersionUID = 1L;
}