package com.supply_chain.controller;
//
//import com.supply_chain.pojo.Order;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.vo.Result;
//import com.supply_chain.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping("/api/order")
//@RequiredArgsConstructor

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.dto.OrderDTO;
import com.supply_chain.pojo.Order;
import com.supply_chain.service.OrderService;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//public class OrderController {
//    private final OrderService orderService;
//
//    @GetMapping("/{id}")
//    public Result getOrderById(@PathVariable Integer id) {
//        Order order = orderService.getOrderById(id);
//        return Result.success(order);
//    }
//
//    @GetMapping
//    public Result getOrders(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer pageSize,
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
//        PageBean pageBean = orderService.getOrders(
//                page,
//                pageSize,
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
//        return Result.success(pageBean);
//    }
//
//    @DeleteMapping("/{ids}")
//    public Result deleteById(@PathVariable Integer[] ids) {
//        orderService.deleteByOrderId(ids);
//        return Result.success();
//    }
//
//    @PutMapping
//    public Result updateOrder(@RequestBody Order order) {
//        orderService.update(order);
//        return Result.success();
//    }
//}
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    Result selById(@PathVariable Integer id) {
        Order order = orderService.selById(id);
        return Result.success(order);
    }

    @PostMapping
    Result getOrders(@RequestBody OrderDTO orderDTO) {
        Page<Order> orderPage = orderService.getOrders(orderDTO);
        return Result.success(orderPage);
    }

    @DeleteMapping("/{ids}")
    Result delById(@PathVariable List<Integer> ids) {
        orderService.delById(ids);
        return Result.success();
    }

    @DeleteMapping("/CustomerId")
    Result delByCustomerId(@RequestParam List<Integer> customerId) {
        orderService.delByCustomerId(customerId);
        return Result.success();
    }
}