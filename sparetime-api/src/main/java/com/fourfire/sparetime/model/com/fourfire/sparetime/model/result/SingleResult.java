package com.fourfire.sparetime.model.com.fourfire.sparetime.model.result;

import lombok.Data;

import java.util.Map;

/**
 * 单个结果
 *
 * @author liuyi
 * @date 2020/11/15
 * @desc
 */
@Data
public class SingleResult<T> extends BaseResult {

    /**
     * 单个返回结果
     */
    private T value;

    public static <T> SingleResult<T> success(T t) {
        SingleResult<T> result = new SingleResult<T>();
        result.setValue(t);
        result.setSuccess(true);
        return result;
    }

    public static SingleResult fail(String errorMsg, boolean canRetry,
                                    Map<String, String> extInfo) {
        SingleResult result = new SingleResult();
        result.setSuccess(false);
        result.setCanRetry(canRetry);
        result.setErrMsg(errorMsg);
        result.setExtInfos(extInfo);

        return result;
    }
}
