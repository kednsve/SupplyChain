package com.supply_chain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logistics {
    // id
    private Integer id;
    // 实际运输时长(天)
    private Integer shippingDaysReal;
    // 计划运输时长(天)
    private Integer shippingDaysScheduled;
    // 物流状态
    private String deliveryStatus;
    // 延误风险 0/1
    private Integer lateRisk;
    // 发货日期
    private LocalDate shippingDate;
    // 物流方式
    private String shippingMode;
    // 订单id
    private Integer orderId;
}
