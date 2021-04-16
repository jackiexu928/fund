package com.jackie.fund.schedule;

import com.jackie.fund.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author xujj
 * @date 2021/4/16
 */
@Configuration
@EnableScheduling
public class FundTask {
    @Autowired
    private FundService fundService;

    @Scheduled(cron = "0 30 14 ? * MON-FRI")
    private void checkAll(){
        fundService.checkFundAll();
    }

    @Scheduled(cron = "0 45 14 ? * MON-FRI")
    private void checkHoldFund(){
        fundService.checkHoldFund();
    }

}
