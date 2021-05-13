package com.jackie.fund.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author xujj
 * @date 2021/4/15
 */
@FeignClient(value = "shares")
public interface SharesClient {

    @GetMapping("/supervise/getStockPercentage")
    String getSelectRecords(List<String> codeList);
}
