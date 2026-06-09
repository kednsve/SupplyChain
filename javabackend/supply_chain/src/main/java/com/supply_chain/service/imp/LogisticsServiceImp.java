package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.mapper.LogisticsMapper;
import com.supply_chain.pojo.Logistics;
import com.supply_chain.service.LogisticsService;
import com.supply_chain.utils.ChkNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.LogisticsMapper;
//import com.supply_chain.pojo.Logistics;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.service.LogisticsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class LogisticsServiceImp implements LogisticsService {
//    private final LogisticsMapper logisticsMapper;
//
//    @Override
//    public PageBean getLogistics(
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
//    ) {
//        PageHelper.startPage(page, pageSize);
//        List<Logistics> list = logisticsMapper.getLogistics(
//                daysRealMin,
//                daysRealMax,
//                daysScheduledMin,
//                daysScheduledMax,
//                deliveryStatus,
//                lateRisk,
//                shippingDateBegin,
//                shippingDateEnd,
//                shippingMode,
//                orderId
//        );
//        PageInfo<Logistics> p = new PageInfo<>(list);
//        return new PageBean(p.getTotal(), p.getList());
//    }
//
//    @Override
//    public Logistics getByOrderId(Integer orderId) {
//        return logisticsMapper.getByOrderId(orderId);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delByOrderId(Integer[] ids) {
//        logisticsMapper.delByOrderId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Logistics logistics) {
//        logisticsMapper.update(logistics);
//    }
//}
@Service
@RequiredArgsConstructor
public class LogisticsServiceImp extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {
    private final LogisticsMapper logisticsMapper;

    @Override
    public void delByOrderId(List<Integer> orderIds) {
        logisticsMapper.delete(new LambdaQueryWrapper<Logistics>().in(Logistics::getOrderId, orderIds));
    }

    @Override
    public void update(Logistics logistics) {
        logisticsMapper.updateById(logistics);
    }

    @Override
    public Logistics selByOrderId(Integer orderId) {
        return logisticsMapper.selectOne(new LambdaQueryWrapper<Logistics>().eq(Logistics::getOrderId, orderId));
    }

    @Override
    public Page<Logistics> getLogistics(
            Integer page,
            Integer pageSize,
            Integer daysRealMin,
            Integer daysRealMax,
            Integer daysScheduledMin,
            Integer daysScheduledMax,
            String deliveryStatus,
            Short lateRisk,
            LocalDate shippingDateBegin,
            LocalDate shippingDateEnd,
            String shippingMode
    ) {
        Page<Logistics> logisticsPage = new Page<>(page, pageSize);
        return logisticsMapper.selectPage(
                logisticsPage,
                new LambdaQueryWrapper<Logistics>()
                        .ge(ChkNotNull.check(daysRealMin), Logistics::getShippingDaysReal, daysRealMin)
                        .le(ChkNotNull.check(daysRealMax), Logistics::getShippingDaysReal, daysRealMax)
                        .ge(ChkNotNull.check(daysScheduledMin), Logistics::getShippingDaysScheduled, daysScheduledMin)
                        .le(ChkNotNull.check(daysScheduledMax), Logistics::getShippingDaysScheduled, daysScheduledMax)
                        .eq(ChkNotNull.check(deliveryStatus), Logistics::getDeliveryStatus, deliveryStatus)
                        .eq(ChkNotNull.check(lateRisk), Logistics::getLateRisk, lateRisk)
                        .ge(ChkNotNull.check(shippingDateBegin), Logistics::getShippingDate, shippingDateBegin)
                        .le(ChkNotNull.check(shippingDateEnd), Logistics::getShippingDate, shippingDateEnd)
                        .eq(ChkNotNull.check(shippingMode), Logistics::getShippingMode, shippingMode)
        );
    }
}