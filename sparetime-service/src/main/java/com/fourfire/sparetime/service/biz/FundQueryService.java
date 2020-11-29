package com.fourfire.sparetime.service.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfire.sparetime.common.utils.DateUtils;
import com.fourfire.sparetime.model.com.fourfire.sparetime.model.result.SingleResult;
import com.fourfire.sparetime.model.com.fourfire.sparetime.model.result.fund.FundDailyInfo;
import com.fourfire.sparetime.service.repo.HttpRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyi
 * @date 2020/11/15
 * @desc
 */
@Component
public class FundQueryService {

    @Autowired
    private HttpRepository httpRepository;

    public SingleResult<FundDailyInfo> queryDailyFundInfo(String fundCode, String date) {
        if (!StringUtils.isNumeric(fundCode) || fundCode.length() != 6) {
            return SingleResult.fail("基金code异常", false, null);
        }

        if (DateUtils.isWeekend(date)) {
            return SingleResult.fail("非交易日", false, null);
        }

        String url = "http://api.fund.eastmoney.com/f10/lsjz";
        Map<String, Object> params = new HashMap<>();
        params.put("callback", "jQuery183015571655574078602_1605452677831");
        params.put("fundCode", fundCode);
        params.put("pageIndex", 1);
        params.put("pageSize", 20);
        params.put("startDate", date);
        params.put("endDate", date);
        params.put("_", "1605453402262");

        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", "http://fundf10.eastmoney.com/jjjz_001410.html");

        try {
            String value = httpRepository.get(url, params, headers);
            if (StringUtils.isEmpty(value)) {
                return SingleResult.fail("查询系统异常", true, null);
            }

            String jsonStr = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            if (jsonObject == null || !jsonObject.containsKey("Data")) {
                return SingleResult.fail("系统返回数据异常", true, null);
            }

            JSONArray jsonArray = jsonObject.getJSONObject("Data").getJSONArray("LSJZList");
            if (jsonArray.isEmpty()) {
                return SingleResult.fail("查询结果为空", false, null);
            }
            JSONObject fundInfo = jsonArray.getJSONObject(0);
            FundDailyInfo fundDailyInfo = new FundDailyInfo();
            fundDailyInfo.setCode(fundCode);
            fundDailyInfo.setDate(date);
            fundDailyInfo.setNetValue(fundInfo.getString("DWJZ"));
            return SingleResult.success(fundDailyInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return SingleResult.fail("本地系统异常", false, null);
        }
    }
}
