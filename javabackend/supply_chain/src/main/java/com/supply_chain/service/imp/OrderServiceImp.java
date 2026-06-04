package com.supply_chain.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.OrderMapper;
import com.supply_chain.pojo.Order;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public PageBean getOrders(
            Integer page, Integer pageSize, LocalDate start, LocalDate end, Integer customerId, Float salesMin,
            Float salesMax,
            String status, String region, String country, String city
                             ) {
        PageHelper.startPage(page, pageSize);
        List<Order> list = orderMapper.getOrders(
                start, end, customerId, salesMin, salesMax, status, region, country, city
                                                );
        PageInfo<Order> pageInfo = new PageInfo<>(list);
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public int deleteByCustomerId(Integer[] ids) {
        return orderMapper.deleteByCustomerId(ids);
    }

    @Override
    public void deleteByOrderId(Integer[] id) {
        orderMapper.deleteByOrderId(id);
    }

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }
}
