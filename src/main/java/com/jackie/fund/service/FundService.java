package com.jackie.fund.service;

import com.alibaba.fastjson.JSON;
import com.jackie.fund.mapper.FundMapper;
import com.jackie.stockbean.fund.dto.request.FundAddReqDTO;
import com.jackie.stockbean.fund.entity.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author xujj
 * @date 2021/4/7
 */
@Service
public class FundService {
    @Autowired
    private FundMapper fundMapper;

    public String add(FundAddReqDTO reqDTO){
        Fund fund = new Fund();
        fund.setCode(reqDTO.getCode());
        fund.setName(reqDTO.getName());
        fund.setDistribute(JSON.toJSONString(reqDTO.getDistributeList()));
        fund.setStockDistribute(JSON.toJSONString(reqDTO.getStockDistributeList()));
        fundMapper.insert(fund);
        return "success";
    }


}
