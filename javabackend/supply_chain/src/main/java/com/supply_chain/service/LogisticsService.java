package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.LogisticsDTO;
import com.supply_chain.pojo.Logistics;

import java.util.List;

//
//import com.supply_chain.pojo.Logistics;
//import com.supply_chain.vo.PageBean;
//
//import java.time.LocalDate;
//
//public interface LogisticsService {
//    PageBean getLogistics(
//            Integer page,
//            Integer pageSize,
//            Integer daysRealMin,
//            Integer daysRealMax,
//            Integer daysScheduledMin,
//            Integer daysScheduledMax,
//            String deliveryStatus,
//            Short lateRisk,
//            LocalDate shippingDateBegin,
//            LocalDate shippingDateEnd,
//            String shippingMode,
//            Integer orderId
//    );
//
//    Logistics getByOrderId(Integer orderId);
//
//    void delByOrderId(Integer[] ids);
//
//    void update(Logistics logistics);
//}
public interface LogisticsService extends IService<Logistics> {
    void delByOrderId(List<Integer> orderIds);

    void update(Logistics logistics);

    Logistics selByOrderId(Integer orderId);

    Page<Logistics> getLogistics(LogisticsDTO logisticsDTO);
}