package com.jackie.fund.controller;

import com.jackie.fund.service.FundService;
import com.jackie.stockbean.fund.dto.request.FundAddReqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author xujj
 * @date 2021/4/7
 */
@Controller
@RequestMapping("/fund")
@Api(value = "Fund")
public class FundController {
    @Autowired
    private FundService fundService;

    @PostMapping("add")
    @ApiOperation("添加基金")
    public String addFund(@RequestBody @ApiParam(name="基金参数",value="传入json格式",required=true) FundAddReqDTO reqDTO){
        return fundService.add(reqDTO);
    }
}
