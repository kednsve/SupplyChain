package com.supply_chain.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.pojo.Logistics;
import com.supply_chain.service.LogisticsService;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//
//import com.supply_chain.pojo.Logistics;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.vo.Result;
//import com.supply_chain.service.LogisticsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping("/api/logistics")
//@RequiredArgsConstructor
//public class LogisticsController {
//    private final LogisticsService logisticsService;
//
//    @GetMapping
//    Result getLogistics(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            Integer daysRealMin,
//            Integer daysRealMax,
//            Integer daysScheduledMin,
//            Integer daysScheduledMax,
//            String deliveryStatus,
//            Short lateRisk,
//            LocalDate shippingDateBegin,
//            LocalDate shippingDateEnd,
//            String shippingMode,
//            Integer orderId
//    ) {
//        PageBean pageBean = logisticsService.getLogistics(
//                page,
//                pageSize,
//                daysRealMin,
//                daysRealMax,
//                daysScheduledMin,
//                daysScheduledMax,
//                deliveryStatus,
//                lateRisk,
//                shippingDateBegin,
//                shippingDateEnd,
//                shippingMode,
//                orderId
//        );
//        return Result.success(pageBean);
//    }
//
//    @GetMapping("/{id}")
//    Result getByOrderId(@PathVariable Integer id) {
//        Logistics logistics = logisticsService.getByOrderId(id);
//        return Result.success(logistics);
//    }
//
//    @DeleteMapping("/{ids}")
//    Result delByOrder(@PathVariable Integer[] ids) {
//        logisticsService.delByOrderId(ids);
//        return Result.success();
//    }
//
//    @PutMapping
//    Result update(@RequestBody Logistics logistics) {
//        logisticsService.update(logistics);
//        return Result.success();
//    }
//}
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logistics")
public class LogisticsController {
    private final LogisticsService logisticsService;

    @DeleteMapping("/{orderIds}")
    Result delByOrderIds(@PathVariable List<Integer> orderIds) {
        logisticsService.delByOrderId(orderIds);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Logistics logistics) {
        logisticsService.update(logistics);
        return Result.success();
    }

    @GetMapping("/{id}")
    Result selByOrderId(@PathVariable Integer id) {
        Logistics logistics = logisticsService.selByOrderId(id);
        return Result.success(logistics);
    }

    @GetMapping
    Result getLogistics(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Integer daysRealMin,
            Integer daysRealMax,
            Integer daysScheduledMin,
            Integer daysScheduledMax,
            String deliveryStatus,
            Short lateRisk,
            LocalDate shippingDateBegin,
            LocalDate shippingDateEnd,
            String shippingMode
    ) {
        Page<Logistics> logisticsPage = logisticsService.getLogistics(
                page,
                pageSize,
                daysRealMin,
                daysRealMax,
                daysScheduledMin,
                daysScheduledMax,
                deliveryStatus,
                lateRisk,
                shippingDateBegin,
                shippingDateEnd,
                shippingMode
        );
        return Result.success(logisticsPage);
    }
}