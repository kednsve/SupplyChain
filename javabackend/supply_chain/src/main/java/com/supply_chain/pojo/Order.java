package com.supply_chain.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    // 订单主键id
    private Integer id;
    // 下单时间
    private LocalDateTime date;
    // 客户id
    private Integer customerId;
    // 销售额
    private BigDecimal sales;
    // 订单状态
    private String status;
    // 地区
    private String region;
    // 国家
    private String country;
    // 城市
    private String city;
    // 逻辑删除
    @TableLogic
    private Short isDeleted;
}