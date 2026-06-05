package com.supply_chain.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.LogisticsMapper;
import com.supply_chain.pojo.Logistics;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.service.LogisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogisticsServiceImp implements LogisticsService {
    private final LogisticsMapper logisticsMapper;

    @Override
    public PageBean getLogistics(
            Integer page, Integer pageSize, Integer daysRealMin, Integer daysRealMax, Integer daysScheduledMin,
            Integer daysScheduledMax, String deliveryStatus, Short lateRisk, LocalDate shippingDateBegin,
            LocalDate shippingDateEnd, String shippingMode, Integer orderId
    ) {
        PageHelper.startPage(page, pageSize);
        List<Logistics> list = logisticsMapper.getLogistics(
                daysRealMin, daysRealMax, daysScheduledMin, daysScheduledMax, deliveryStatus, lateRisk,
                shippingDateBegin, shippingDateEnd, shippingMode, orderId
        );
        PageInfo<Logistics> p = new PageInfo<>(list);
        return new PageBean(p.getTotal(), p.getList());
    }

    @Override
    public Logistics getByOrderId(Integer orderId) {
        return logisticsMapper.getByOrderId(orderId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delByOrderId(Integer[] ids) {
        logisticsMapper.delByOrderId(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Logistics logistics) {
        logisticsMapper.update(logistics);
    }
}
