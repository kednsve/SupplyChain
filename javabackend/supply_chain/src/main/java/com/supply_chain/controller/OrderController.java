package com.supply_chain.controller;

import com.supply_chain.pojo.Order;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.pojo.Result;
import com.supply_chain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Result getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return Result.success(order);
    }

    @GetMapping
    public Result getOrders(
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
            LocalDate start, LocalDate end, Integer customerId, Float salesMin, Float salesMax, String status,
            String region, String country, String city
    ) {
        PageBean pageBean = orderService.getOrders(
                page, pageSize, start, end, customerId, salesMin, salesMax, status, region, country, city
        );
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable Integer[] ids) {
        orderService.deleteByOrderId(ids);
        return Result.success();
    }

    @PutMapping
    public Result updateOrder(@RequestBody Order order) {
        orderService.update(order);
        return Result.success();
    }
}
