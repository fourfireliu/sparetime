package com.fourfire.sparetime.model.com.fourfire.sparetime.model.result;

import lombok.Data;

import java.util.List;

/**
 * 批量结果
 *
 * @author liuyi
 * @date 2020/11/15
 * @desc
 */
@Data
public class BatchResult<T> extends BaseResult {

    /**
     * 分页结果
     */
    private List<T> values;
}
