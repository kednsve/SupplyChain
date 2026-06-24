package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.dto.OrderItemsDTO;
import com.supply_chain.mapper.OrderItemsMapper;
import com.supply_chain.pojo.OrderItems;
import com.supply_chain.service.OrderItemsService;
import com.supply_chain.service.OrderService;
import com.supply_chain.vo.OrderItemsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

//
//import com.supply_chain.mapper.OrderItemsMapper;
//import com.supply_chain.pojo.OrderItems;
//import com.supply_chain.service.OrderItemsService;
//import com.supply_chain.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class OrderItemsServiceImp implements OrderItemsService {
//    private final OrderService orderService;
//    private final OrderItemsMapper orderItemsMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delByOrderId(Integer[] ids) {
//        orderItemsMapper.delByOrderId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(OrderItems orderItems) {
//        orderItemsMapper.update(orderItems);
//        Integer orderId = orderItems.getOrderId();
//        List<OrderItems> list = orderItemsMapper.getByOrderId(orderId);
//        BigDecimal sales = BigDecimal.ZERO;
//        for (OrderItems orderItem : list) {
//            sales = sales.add(orderItem.getTotal());
//        }
//        orderService.updateSales(orderId, sales);
//    }
//
//    @Override
//    public List<OrderItems> getByOrderId(Integer id) {
//        return orderItemsMapper.getByOrderId(id);
//    }
//}
@Service
@RequiredArgsConstructor
public class OrderItemsServiceImp extends ServiceImpl<OrderItemsMapper, OrderItems> implements OrderItemsService {
    private final OrderItemsMapper orderItemsMapper;
    private final OrderService orderService;

    @Override
    public void delByOrderId(List<Integer> orderIds) {
        orderItemsMapper.delete(new LambdaQueryWrapper<OrderItems>().in(OrderItems::getOrderId, orderIds));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(OrderItems orderItems) {
        orderItemsMapper.updateById(orderItems);
        Integer orderId = orderItems.getOrderId();
        List<OrderItemsVO> voList = this.selByOrderId(orderId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemsVO vo : voList) {
            if (vo.getTotal() != null) {
                totalPrice = totalPrice.add(vo.getTotal());
            }
        }
        orderService.updateSales(orderId, totalPrice);
    }

    @Override
    public List<OrderItemsVO> selByOrderId(Integer orderId) {
        return orderItemsMapper.selByOrderId(orderId);
    }

    @Override
    public Page<OrderItemsVO> getOrderItems(OrderItemsDTO orderItemsDTO) {
        Integer page = orderItemsDTO.getPage();
        Integer pageSize = orderItemsDTO.getPageSize();
        Page<OrderItemsVO> pageTemp = new Page<>(page, pageSize);
        return orderItemsMapper.getOrderItems(pageTemp, orderItemsDTO);
    }
}