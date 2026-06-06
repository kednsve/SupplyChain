package com.supply_chain.service;

import com.supply_chain.pojo.OrderItems;

import java.util.List;

public interface OrderItemsService {
    void delByOrderId(Integer[] ids);

    void update(OrderItems orderItems);

    List<OrderItems> getByOrderId(Integer id);
}
