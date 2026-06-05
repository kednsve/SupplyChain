package com.supply_chain.service.imp;

import com.supply_chain.mapper.OrderItemsMapper;
import com.supply_chain.service.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemsServiceImp implements OrderItemsService {

    private final OrderItemsMapper orderItemsMapper;
    @Override
    public void delByOrderId(Integer[] ids) {
        orderItemsMapper.delByOrderId(ids);
    }
}
