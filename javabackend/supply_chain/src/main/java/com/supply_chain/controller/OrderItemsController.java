package com.supply_chain.controller;

import com.supply_chain.pojo.Result;
import com.supply_chain.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemsController {
    @Autowired
    private OrderItemsService orderItemsService;

    @DeleteMapping("/{ids}")
    Result delByOrderId(@PathVariable Integer[] ids) {
        orderItemsService.delByOrderId(ids);
        return Result.success();
    }
}
