package com.supply_chain.controller;

import com.supply_chain.pojo.Customer;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.pojo.Result;
import com.supply_chain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            String segment
    ) {
        PageBean pageBean = customerService.page(page, pageSize, name, segment);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    Result selectById(@PathVariable Integer id) {
        Customer customer = customerService.selectById(id);
        return Result.success(customer);
    }

    @DeleteMapping("/{ids}")
    Result deleteByIds(@PathVariable Integer[] ids) {
        customerService.deleteByCustomerId(ids);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Customer customer) {
        customerService.update(customer);
        return Result.success();
    }
}
