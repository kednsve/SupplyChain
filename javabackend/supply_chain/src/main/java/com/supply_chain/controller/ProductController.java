package com.supply_chain.controller;

import com.supply_chain.pojo.PageBean;
import com.supply_chain.pojo.Product;
import com.supply_chain.pojo.Result;
import com.supply_chain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @DeleteMapping("/{ids}")
    public Result delByIds(@PathVariable Integer[] ids) {
        productService.delById(ids);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }

    @GetMapping
    public Result getProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            BigDecimal priceLow,
            BigDecimal priceHigh,
            Integer categoryId,
            Integer departmentId
    ) {
        PageBean pageBean = productService.getProducts(
                page,
                pageSize,
                name,
                priceLow,
                priceHigh,
                categoryId,
                departmentId
        );
        return Result.success(pageBean);
    }
}
