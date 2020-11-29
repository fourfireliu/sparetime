package com.fourfire.sparetime.service.repo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fourfire.sparetime.common.utils.HttpUtil;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fourfire.sparetime.common.Constants;

import java.util.Map;

/**
 * @author liuyi
 * @date 2020/9/21
 * @desc
 */
@Repository
public class HttpRepository {

    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * https://fundf10.eastmoney.com/F10DataApi.aspx?type=lsjz&code=004871&page=1&per=65535&sdate=2020-11-27&edate=2020-11-27
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String get(String url, Map<String, Object> params, Map<String, String> headers) throws Exception {
        String finalUrl = StringUtils.join(url, Constants.GET_URL_PARAM_SEP_STR, HttpUtil.buildParamString(params));

        final Request request = new Request.Builder()
                .url(finalUrl)
                .headers(Headers.of(headers))
                .get()
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public static void main(String args[]) throws Exception {
        String url= "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery183015571655574078602_1605452677831&fundCode=001410&pageIndex=1&pageSize=20&startDate=2020-11-26&endDate=2020-11-27&_=1605453402262";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url).header("Referer", "http://fundf10.eastmoney.com/jjjz_001410.html")
                .get()
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }
}
