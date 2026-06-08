package com.supply_chain.controller;

import com.supply_chain.pojo.Logistics;
import com.supply_chain.vo.PageBean;
import com.supply_chain.vo.Result;
import com.supply_chain.service.LogisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/logistics")
@RequiredArgsConstructor
public class LogisticsController {
    private final LogisticsService logisticsService;

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
            String shippingMode,
            Integer orderId
    ) {
        PageBean pageBean = logisticsService.getLogistics(
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
                shippingMode,
                orderId
        );
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    Result getByOrderId(@PathVariable Integer id) {
        Logistics logistics = logisticsService.getByOrderId(id);
        return Result.success(logistics);
    }

    @DeleteMapping("/{ids}")
    Result delByOrder(@PathVariable Integer[] ids) {
        logisticsService.delByOrderId(ids);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Logistics logistics) {
        logisticsService.update(logistics);
        return Result.success();
    }
}
