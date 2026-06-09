package com.supply_chain.controller;

import com.supply_chain.pojo.Product;
import com.supply_chain.service.ProductService;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.pojo.Product;
//import com.supply_chain.vo.Result;
//import com.supply_chain.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/product")
//public class ProductController {
//    private final ProductService productService;
//
//    @DeleteMapping("/{ids}")
//    public Result delByIds(@PathVariable Integer[] ids) {
//        productService.delById(ids);
//        return Result.success();
//    }
//
//    @PutMapping
//    public Result update(@RequestBody Product product) {
//        productService.update(product);
//        return Result.success();
//    }
//
//    @GetMapping("/{id}")
//    public Result getById(@PathVariable Integer id) {
//        Product product = productService.getById(id);
//        return Result.success(product);
//    }
//
//    @GetMapping
//    public Result getProducts(
//            @RequestParam(defaultValue = "1") Integer page,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            String name,
//            BigDecimal priceLow,
//            BigDecimal priceHigh,
//            Integer categoryId,
//            Integer departmentId
//    ) {
//        PageBean pageBean = productService.getProducts(
//                page,
//                pageSize,
//                name,
//                priceLow,
//                priceHigh,
//                categoryId,
//                departmentId
//        );
//        return Result.success(pageBean);
//    }
//}
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @DeleteMapping("/{ids}")
    Result delById(@PathVariable List<Integer> ids) {
        productService.delById(ids);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.success();
    }

    @GetMapping("/{id}")
    Result getById(@PathVariable Integer id) {
        return Result.success(productService.selById(id));
    }

    @GetMapping
    Result getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            BigDecimal priceLow,
            BigDecimal priceHigh,
            Integer categoryId,
            Integer departmentId
    ) {
        return Result.success(productService.getProducts(
                page,
                pageSize,
                name,
                priceLow,
                priceHigh,
                categoryId,
                departmentId
        ));
    }
}