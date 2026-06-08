package com.supply_chain.service;


import com.supply_chain.pojo.Customer;
import com.supply_chain.vo.PageBean;


public interface CustomerService {
    Customer selectById(Integer id);

    PageBean page(Integer start, Integer pageSize, String name, String segment);

    void update(Customer customer);

    void deleteByCustomerId(Integer[] id);
}
