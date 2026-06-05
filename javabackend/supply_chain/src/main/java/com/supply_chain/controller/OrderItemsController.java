package com.supply_chain.controller;

import com.supply_chain.pojo.Result;
import com.supply_chain.service.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orderItems")
@RequiredArgsConstructor
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    @DeleteMapping("/{ids}")
    Result delByOrderId(@PathVariable Integer[] ids) {
        orderItemsService.delByOrderId(ids);
        return Result.success();
    }
}
