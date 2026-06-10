package com.supply_chain.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.pojo.OrderItems;
import com.supply_chain.service.OrderItemsService;
import com.supply_chain.vo.OrderItemsVO;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderItems")
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    @DeleteMapping("/{orderIds}")
    Result delByOrderId(@PathVariable List<Integer> orderIds) {
        orderItemsService.delByOrderId(orderIds);
        return Result.success();
    }

    @PutMapping
    Result updateByOrderId(@RequestBody OrderItems orderItems) {
        orderItemsService.update(orderItems);
        return Result.success();
    }

    @GetMapping("/{orderId}")
    Result getByOrderId(@PathVariable Integer orderId) {
        List<OrderItemsVO> voList = orderItemsService.selByOrderId(orderId);
        return Result.success(voList);
    }

    @GetMapping
    Result getOrderItems(
            Integer page,
            Integer pageSize,
            Integer productId,
            Integer quantity,
            Integer unitPriceLow,
            Integer unitPriceHigh
    ) {
        Page<OrderItemsVO> voPage = orderItemsService.getOrderItems(
                page,
                pageSize,
                productId,
                quantity,
                unitPriceLow,
                unitPriceHigh
        );
        return Result.success(voPage);
    }
}