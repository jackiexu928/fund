package com.jackie.fund;

import com.jackie.fund.client.InvestorClient;
import com.jackie.fund.service.FundService;
import com.jackie.stockbean.fund.entity.Fund;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FundApplicationTests {
    @Autowired
    private FundService fundService;
    @Autowired
    private InvestorClient investorClient;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        System.out.println(investorClient.getAllInvestor());
    }

}
