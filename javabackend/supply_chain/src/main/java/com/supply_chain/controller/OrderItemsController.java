//package com.supply_chain.controller;
//
//import com.supply_chain.pojo.OrderItems;
//import com.supply_chain.vo.Result;
//import com.supply_chain.service.OrderItemsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/orderItems")
//@RequiredArgsConstructor
//public class OrderItemsController {
//    private final OrderItemsService orderItemsService;
//
//    @DeleteMapping("/{ids}")
//    Result delByOrderId(@PathVariable Integer[] ids) {
//        orderItemsService.delByOrderId(ids);
//        return Result.success();
//    }
//
//    @PutMapping
//    Result update(@RequestBody OrderItems orderItems) {
//        orderItemsService.update(orderItems);
//        return Result.success();
//    }
//
//    @GetMapping("/{orderId}")
//    Result getByOrderId(@PathVariable Integer orderId) {
//        List<OrderItems> orderItems = orderItemsService.getByOrderId(orderId);
//        return Result.success(orderItems);
//    }
//}
