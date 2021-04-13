package com.jackie.fund.mapper;

import com.jackie.stockbean.fund.entity.Fund;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * Created with IntelliJ IDEA
 * Description:
 * `code` varchar(6) NOT NULL COMMENT '基金代码',
 *  *   `name` varchar(32) NOT NULL COMMENT '基金名称',
 *  *   `distribute` VARCHAR(256) NOT NULL COMMENT '投资分布',
 *  *   `stock_distribute` text NOT NULL COMMENT '基金持股',
 *
 * @author xujj
 * @date 2021/4/6
 */
@Mapper
public interface FundMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into fund(code, name, distribute, stock_distribute) " +
            "values(#{code}, #{name}, #{distribute}, #{stockDistribute})")
    void insert(Fund fund);
}
