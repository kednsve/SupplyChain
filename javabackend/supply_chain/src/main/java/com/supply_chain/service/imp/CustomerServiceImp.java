package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.mapper.CustomerMapper;
import com.supply_chain.pojo.Customer;
import com.supply_chain.service.CustomerService;
import com.supply_chain.utils.ChkNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.CustomerMapper;
//import com.supply_chain.pojo.Customer;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.service.CustomerService;
//import com.supply_chain.service.OrderService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CustomerServiceImp implements CustomerService {
//    private final CustomerMapper customerMapper;
//    private final OrderService orderService;
//
//    @Override
//    public Customer selectById(Integer id) {
//        return customerMapper.selectById(id);
//    }
//
//    @Override
//    public PageBean page(Integer page, Integer pageSize, String name, String segment) {
//        PageHelper.startPage(page, pageSize);
//        List<Customer> list = customerMapper.list(name, segment);
//        PageInfo<Customer> p = new PageInfo<>(list);
//        return new PageBean(p.getTotal(), p.getList());
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void deleteByCustomerId(Integer[] ids) {
//        orderService.deleteByCustomerId(ids);
//        customerMapper.deleteByCustomerId(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Customer customer) {
//        customerMapper.update(customer);
//    }
//}
@Service
@RequiredArgsConstructor
public class CustomerServiceImp extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    private final CustomerMapper customerMapper;

    @Override
    public Customer selById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public Page<Customer> getCustomers(Integer page, Integer pageSize, String name, String segment) {
        Page<Customer> pageConfig = new Page<>(page, pageSize);
        return customerMapper.selectPage(
                pageConfig,
                new LambdaQueryWrapper<Customer>()
                        .like(ChkNotNull.check(name), Customer::getName, name)
                        .eq(ChkNotNull.check(segment), Customer::getSegment, segment)
        );
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateById(customer);
    }

    @Override
    public void delById(List<Integer> ids) {
        customerMapper.deleteByIds(ids);
    }
}