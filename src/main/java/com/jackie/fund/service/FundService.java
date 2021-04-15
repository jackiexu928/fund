package com.jackie.fund.service;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.AtomicDouble;
import com.jackie.fund.client.MessageFeignClient;
import com.jackie.fund.client.SharesClient;
import com.jackie.fund.mapper.FundMapper;
import com.jackie.stockbean.fund.dto.request.FundAddReqDTO;
import com.jackie.stockbean.fund.entity.Fund;
import com.jackie.stockbean.fund.vo.DistributeVo;
import com.jackie.stockbean.message.dto.request.MailReqDTO;
import com.jackie.stockbean.shares.dto.response.StockPercentageResponseDTO;
import com.jackie.stockbean.utils.DecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.ListUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private SharesClient sharesClient;
    @Autowired
    private MessageFeignClient messageFeignClient;

    public String add(FundAddReqDTO reqDTO){
        Fund fund = new Fund();
        fund.setCode(reqDTO.getCode());
        fund.setName(reqDTO.getName());
        fund.setDistribute(JSON.toJSONString(reqDTO.getDistributeList()));
        fund.setStockDistribute(JSON.toJSONString(reqDTO.getStockDistributeList()));
        fundMapper.insert(fund);
        return "success";
    }

    /**
     * 检查所有基金
     * 邮件提醒上涨的基金
     */
    public void checkFundAll(){
        List<Fund> fundList = fundMapper.findAll();
        if (ListUtil.isNotEmpty(fundList)){
            fundList.forEach(fund -> {
                //获取所有持仓股票
                List<DistributeVo> stockList = JSON.parseArray(fund.getStockDistribute(), DistributeVo.class);
                //查询当日涨幅
                List<String> codeList = stockList.stream().map(DistributeVo::getCode).collect(Collectors.toList());
                String json = sharesClient.getSelectRecords(codeList);
                List<StockPercentageResponseDTO> responseDTOList = JSON.parseArray(json, StockPercentageResponseDTO.class);
                Map<String, Double> respMap = responseDTOList.stream().collect(
                        Collectors.toMap(StockPercentageResponseDTO::getCode, StockPercentageResponseDTO::getPercentage));
                AtomicDouble gain = new AtomicDouble(0.00);
                stockList.forEach(stock -> gain.addAndGet(DecimalUtil.getTwoDecimalPlaces(stock.getProportion() * respMap.get(stock.getCode()) / 100)));
                if (gain.get() > 0){
                    //基金股票持仓部分收涨
                    MailReqDTO reqDTO = new MailReqDTO();
                    reqDTO.setTo("137469680@qq.com");
                    reqDTO.setSubject("基金");
                    reqDTO.setContent(fund.getCode() + fund.getName() + "基金，今日持仓股票总体上涨，涨幅约为：" + gain.get() + "%");
                    messageFeignClient.sendMail(reqDTO);
                }
            });
        }
    }

    /**
     * 检查所有用户持有的基金
     * 将本日的涨跌情况发给对应的用户邮箱
     */
    public void checkHoldFund(){

    }

}
