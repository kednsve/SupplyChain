package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.dto.OrderDTO;
import com.supply_chain.mapper.OrderMapper;
import com.supply_chain.pojo.Order;
import com.supply_chain.service.OrderService;
import com.supply_chain.utils.ChkNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.OrderMapper;
//import com.supply_chain.pojo.Order;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.service.LogisticsService;
//import com.supply_chain.service.OrderItemsService;
//import com.supply_chain.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImp implements OrderService {
//    private final OrderMapper orderMapper;
//    private final LogisticsService logisticsService;
//    private final OrderItemsService orderItemsService;
//
//    @Override
//    public Order getOrderById(Integer id) {
//        return orderMapper.getOrderById(id);
//    }
//
//    @Override
//    public PageBean getOrders(
//            Integer page,
//            Integer pageSize,
//            LocalDate start,
//            LocalDate end,
//            Integer customerId,
//            Float salesMin,
//            Float salesMax,
//            String status,
//            String region,
//            String country,
//            String city
//    ) {
//        PageHelper.startPage(page, pageSize);
//        List<Order> list = orderMapper.getOrders(
//                start,
//                end,
//                customerId,
//                salesMin,
//                salesMax,
//                status,
//                region,
//                country,
//                city
//        );
//        PageInfo<Order> pageInfo = new PageInfo<>(list);
//        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void deleteByCustomerId(Integer[] ids) {
//        Integer[] orderId = getIdByCustomerId(ids);
//        orderItemsService.delByOrderId(orderId);
//        logisticsService.delByOrderId(orderId);
//        orderMapper.deleteByCustomerId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void deleteByOrderId(Integer[] ids) {
//        orderItemsService.delByOrderId(ids);
//        logisticsService.delByOrderId(ids);
//        orderMapper.deleteByOrderId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Order order) {
//        orderMapper.update(order);
//    }
//
//    @Override
//    public Integer[] getIdByCustomerId(Integer[] ids) {
//        return orderMapper.getIdByCustomerId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void updateSales(Integer id, BigDecimal sales) {
//        orderMapper.updateSales(id, sales);
//    }
//}
@Service
@RequiredArgsConstructor
public class OrderServiceImp extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public Order selById(Integer id) {
        return orderMapper.selectById(id);
    }

    @Override
    public Page<Order> getOrders(OrderDTO orderDTO) {
        Integer page = orderDTO.getPage();
        Integer pageSize = orderDTO.getPageSize();
        LocalDate start = orderDTO.getStart();
        LocalDate end = orderDTO.getEnd();
        Integer customerId = orderDTO.getCustomerId();
        Float salesMin = orderDTO.getSalesMin();
        Float salesMax = orderDTO.getSalesMax();
        String status = orderDTO.getStatus();
        String region = orderDTO.getRegion();
        String country = orderDTO.getCountry();
        String city = orderDTO.getCity();
        Page<Order> orderPage = new Page<>(page, pageSize);
        return orderMapper.selectPage(
                orderPage,
                new LambdaQueryWrapper<Order>()
                        .ge(ChkNotNull.check(start), Order::getDate, start)
                        .le(ChkNotNull.check(end), Order::getDate, end)
                        .eq(ChkNotNull.check(customerId), Order::getCustomerId, customerId)
                        .ge(ChkNotNull.check(salesMin), Order::getSales, salesMin)
                        .le(ChkNotNull.check(salesMax), Order::getSales, salesMax)
                        .eq(ChkNotNull.check(status), Order::getStatus, status)
                        .like(ChkNotNull.check(region), Order::getRegion, region)
                        .like(ChkNotNull.check(country), Order::getCountry, country)
                        .like(ChkNotNull.check(city), Order::getCity, city)
        );
    }

    @Override
    public void delByCustomerId(List<Integer> ids) {
        orderMapper.delete(new LambdaQueryWrapper<Order>().in(Order::getCustomerId, ids));
    }

    @Override
    public void delById(List<Integer> ids) {
        orderMapper.deleteByIds(ids);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateById(order);
    }

    @Override
    public void updateSales(Integer id, BigDecimal sales) {
        orderMapper.update(new LambdaUpdateWrapper<Order>().eq(Order::getId, id).set(Order::getSales, sales));
    }
}