package com.fourfire.sparetime.common.utils;

import com.fourfire.sparetime.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author liuyi
 * @date 2020/11/29
 * @desc
 */

public class HttpUtil {

    public static String buildParamString(Map<String, Object> params) {
        if (CollectionUtils.isEmpty(params)) {
            return Constants.BLANK_STR;
        }

        Set<String> paramKvs = new HashSet<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            StringBuilder paramBuilder = new StringBuilder();
            paramBuilder.append(entry.getKey())
                    .append(Constants.GET_KV_CONN_STR)
                    .append(entry.getValue());
            paramKvs.add(paramBuilder.toString());
        }
        return StringUtils.join(paramKvs, Constants.GET_KV_SEP_STR);
    }
}
