package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.OrderDTO;
import com.supply_chain.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    Order selById(Integer id);

    Page<Order> getOrders(OrderDTO orderDTO);

    void delById(List<Integer> ids);

    void update(Order order);


}
