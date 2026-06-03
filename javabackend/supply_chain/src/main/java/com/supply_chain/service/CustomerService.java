package com.supply_chain.service;

import com.supply_chain.mapper.CustomerMapper;
import com.supply_chain.pojo.Customer;
import com.supply_chain.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerService {
    Customer selectById(Integer id);

    PageBean page(Integer start, Integer pageSize, String name, String segment);

    Integer update(Customer customer);
}
