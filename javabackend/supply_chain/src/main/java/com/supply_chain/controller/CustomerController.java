package com.supply_chain.controller;
//
//import com.supply_chain.pojo.Customer;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.vo.Result;
//import com.supply_chain.service.CustomerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/customer")
//@RequiredArgsConstructor
//public class CustomerController {
//    private final CustomerService customerService;
//
//    @GetMapping
//    Result page(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            String name,
//            String segment
//    ) {
//        PageBean pageBean = customerService.page(page, pageSize, name, segment);
//        return Result.success(pageBean);
//    }
//
//    @GetMapping("/{id}")
//    Result selectById(@PathVariable Integer id) {
//        Customer customer = customerService.selectById(id);
//        return Result.success(customer);
//    }
//
//    @DeleteMapping("/{ids}")
//    Result deleteByIds(@PathVariable Integer[] ids) {
//        customerService.deleteByCustomerId(ids);
//        return Result.success();
//    }
//
//    @PutMapping
//    Result update(@RequestBody Customer customer) {
//        customerService.update(customer);
//        return Result.success();
//    }
//}

import com.supply_chain.dto.CustomerDTO;
import com.supply_chain.pojo.Customer;
import com.supply_chain.service.CustomerService;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    Result selById(@PathVariable Integer id) {
        return Result.success(customerService.selById(id));
    }

    @PostMapping
    Result getCustomers(@RequestBody CustomerDTO customerDTO) {
        return Result.success(customerService.getCustomers(customerDTO));
    }

    @PutMapping
    Result update(@RequestBody Customer customer) {
        customerService.update(customer);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    Result delById(@PathVariable List<Integer> ids) {
        customerService.delById(ids);
        return Result.success();
    }
}