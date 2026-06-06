package com.supply_chain.service;

import com.supply_chain.pojo.PageBean;
import com.supply_chain.pojo.Product;

import java.math.BigDecimal;

public interface ProductService {
    void delById(Integer[] ids);

    void update(Product product);

    Product getById(Integer id);

    PageBean getProducts(
            Integer page,
            Integer pageSize,
            String name,
            BigDecimal priceLow,
            BigDecimal priceHigh,
            Integer categoryId,
            Integer departmentId
    );
}
