package com.fourfire.sparetime.test;

import com.fourfire.sparetime.SparetimeWorkApplication;
import com.fourfire.sparetime.service.biz.FundQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

/**
 * @author liuyi
 * @date 2020/9/18
 * @desc
 */
@SpringBootTest(classes = SparetimeWorkApplication.class)
public class SpareTimeDemoTest {

    @Autowired
    private FundQueryService fundQueryService;

    @Test
    void demoTest() {
        int testInt = 2;
        Assert.isTrue(testInt>0, "test failed");
    }

    @Test
    void testQuery() {
        String fundCode = "270002";
        String date = "2020-11-27";
        System.out.println("===========");
        System.out.println(fundQueryService.queryDailyFundInfo(fundCode, date));
        System.out.println("===========");
    }
}
