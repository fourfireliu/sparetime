package com.fourfire.sparetime.model.com.fourfire.sparetime.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author liuyi
 * @date 2020/11/15
 * @desc
 */
@Data
public class BaseResult implements Serializable {

    private boolean success = Boolean.FALSE;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 是否可重试
     */
    private boolean canRetry = Boolean.FALSE;

    /**
     * 扩展信息
     */
    private Map<String, String> extInfos;

}
