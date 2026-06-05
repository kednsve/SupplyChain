package com.supply_chain.service;

import com.supply_chain.pojo.Logistics;
import com.supply_chain.pojo.PageBean;

import java.time.LocalDate;

public interface LogisticsService {
    PageBean getLogistics(
            Integer page, Integer pageSize, Integer daysRealMin, Integer daysRealMax,
            Integer daysScheduledMin, Integer daysScheduledMax,
            String deliveryStatus, Short lateRisk,
            LocalDate shippingDateBegin, LocalDate shippingDateEnd,
            String shippingMode, Integer orderId
    );

    Logistics getByOrderId(Integer orderId);

    void delByOrderId(Integer[] ids);

    void update(Logistics logistics);
}
