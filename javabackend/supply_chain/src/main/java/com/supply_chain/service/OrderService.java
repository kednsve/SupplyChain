package com.supply_chain.service;

import com.supply_chain.pojo.Order;
import com.supply_chain.pojo.PageBean;

import java.time.LocalDate;

public interface OrderService {
    Order getOrderById(Integer id);
    PageBean getOrders(
            Integer page,Integer pageSize,LocalDate start, LocalDate end, Integer customerId, Float salesMin, Float salesMax, String status,
            String region, String country, String city
                      );
    int deleteByCustomerId(Integer[] ids);
    void deleteByOrderId(Integer[] ids);
    int update(Order order);
}
