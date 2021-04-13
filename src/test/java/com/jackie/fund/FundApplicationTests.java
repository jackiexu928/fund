package com.jackie.fund;

import com.jackie.fund.service.FundService;
import com.jackie.stockbean.fund.entity.Fund;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FundApplicationTests {
    @Autowired
    private FundService fundService;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){

    }

}
