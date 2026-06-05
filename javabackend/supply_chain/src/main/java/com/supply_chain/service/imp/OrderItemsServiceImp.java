package com.supply_chain.service.imp;

import com.supply_chain.mapper.OrderItemsMapper;
import com.supply_chain.pojo.OrderItems;
import com.supply_chain.service.OrderItemsService;
import com.supply_chain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsServiceImp implements OrderItemsService {
    private final OrderService orderService;
    private final OrderItemsMapper orderItemsMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delByOrderId(Integer[] ids) {
        orderItemsMapper.delByOrderId(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(OrderItems orderItems) {
        orderItemsMapper.update(orderItems);
        Integer orderId = orderItems.getOrderId();
        List<OrderItems> list = orderItemsMapper.getByOrderId(orderId);
        BigDecimal sales = BigDecimal.ZERO;
        for (OrderItems orderItem : list) {
            sales = sales.add(orderItem.getTotal());
        }
        orderService.updateSales(orderId, sales);
    }

    @Override
    public List<OrderItems> getByOrderId(Integer id) {
        return orderItemsMapper.getByOrderId(id);
    }
}
