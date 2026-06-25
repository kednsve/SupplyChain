package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.OrderItemsDTO;
import com.supply_chain.pojo.OrderItems;
import com.supply_chain.vo.OrderItemsVO;

import java.util.List;

//
//import com.supply_chain.pojo.OrderItems;
//
//import java.util.List;
//
//public interface OrderItemsService {
//    void delByOrderId(Integer[] ids);
//
//    void update(OrderItems orderItems);
//
//    List<OrderItems> getByOrderId(Integer id);
//}
public interface OrderItemsService extends IService<OrderItems> {

    void update(OrderItems orderItems);

    List<OrderItemsVO> selByOrderId(Integer orderId);

    Page<OrderItemsVO> getOrderItems(OrderItemsDTO orderItemsDTO);

    void delByIds(List<Integer> ids);
}