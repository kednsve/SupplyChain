package com.supply_chain.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.CustomerMapper;
import com.supply_chain.pojo.Customer;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.service.CustomerService;
import com.supply_chain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderService orderService;

    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, String segment) {
        PageHelper.startPage(page, pageSize);
        List<Customer> list = customerMapper.list(name, segment);
        PageInfo<Customer> p = new PageInfo<>(list);
        return new PageBean(p.getTotal(), p.getList());
    }

    @Override
    public void deleteByCustomerId(Integer[] ids) {
        orderService.deleteByCustomerId(ids);
        customerMapper.deleteByCustomerId(ids);
    }

    @Override
    public Integer update(Customer customer) {
        return customerMapper.update(customer);
    }
}
