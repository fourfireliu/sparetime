package com.fourfire.sparetime.model.com.fourfire.sparetime.model.result.fund;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author liuyi
 * @date 2020/11/15
 * @desc
 */
@Data
public class FundDailyInfo implements Serializable {

    /**
     * 基金名称
     */
    private String name;

    /**
     * 基金编码
     */
    private String code;

    /**
     * 净值
     */
    private String netValue;

    /**
     * 估计净值
     */
    private String estimatedNetValue;

    /**
     * 净值时间(yyyy-mm-dd)
     */
    private String date;
}
