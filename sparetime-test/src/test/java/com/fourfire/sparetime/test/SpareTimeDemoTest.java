package com.fourfire.sparetime.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author liuyi
 * @date 2020/9/18
 * @desc
 */
@SpringBootTest(classes = com.fourfire.sparetime.test.SpareTimeDemoTest.class)
public class SpareTimeDemoTest {

    @Test
    void demoTest() {
        int testInt = 2;
        Assert.isTrue(testInt>0, "test failed");
    }
}
