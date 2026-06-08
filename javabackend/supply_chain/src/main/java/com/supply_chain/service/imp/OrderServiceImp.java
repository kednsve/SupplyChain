package com.supply_chain.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.OrderMapper;
import com.supply_chain.pojo.Order;
import com.supply_chain.vo.PageBean;
import com.supply_chain.service.LogisticsService;
import com.supply_chain.service.OrderItemsService;
import com.supply_chain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderMapper orderMapper;
    private final LogisticsService logisticsService;
    private final OrderItemsService orderItemsService;

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public PageBean getOrders(
            Integer page,
            Integer pageSize,
            LocalDate start,
            LocalDate end,
            Integer customerId,
            Float salesMin,
            Float salesMax,
            String status,
            String region,
            String country,
            String city
    ) {
        PageHelper.startPage(page, pageSize);
        List<Order> list = orderMapper.getOrders(
                start,
                end,
                customerId,
                salesMin,
                salesMax,
                status,
                region,
                country,
                city
        );
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByCustomerId(Integer[] ids) {
        Integer[] orderId = getIdByCustomerId(ids);
        orderItemsService.delByOrderId(orderId);
        logisticsService.delByOrderId(orderId);
        orderMapper.deleteByCustomerId(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByOrderId(Integer[] ids) {
        orderItemsService.delByOrderId(ids);
        logisticsService.delByOrderId(ids);
        orderMapper.deleteByOrderId(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public Integer[] getIdByCustomerId(Integer[] ids) {
        return orderMapper.getIdByCustomerId(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSales(Integer id, BigDecimal sales) {
        orderMapper.updateSales(id, sales);
    }
}
