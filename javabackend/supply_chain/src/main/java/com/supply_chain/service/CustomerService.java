package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.CustomerDTO;
import com.supply_chain.pojo.Customer;

import java.util.List;

//
//
//import com.supply_chain.pojo.Customer;
//import com.supply_chain.vo.PageBean;
//
//
//public interface CustomerService {
//    Customer selectById(Integer id);
//
//    PageBean page(Integer start, Integer pageSize, String name, String segment);
//
//    void update(Customer customer);
//
//    void deleteByCustomerId(Integer[] id);
//}
public interface CustomerService extends IService<Customer> {
    Customer selById(Integer id);

    Page<Customer> getCustomers(CustomerDTO customerDTO);

    void update(Customer customer);

    void delById(List<Integer> ids);
}