package com.jackie.fund.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author xujj
 * @date 2021/4/16
 */
@FeignClient("investor")
public interface InvestorClient {

    @GetMapping("/investor/getAllInvestor")
    String getAllInvestor();
}
