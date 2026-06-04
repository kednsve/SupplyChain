package com.supply_chain.service.imp;

import com.supply_chain.mapper.OrderItemsMapper;
import com.supply_chain.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImp implements OrderItemsService {
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Override
    public Integer delByOrderId(Integer[] ids) {
        return orderItemsMapper.delByOrderId(ids);
    }
}
