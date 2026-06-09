package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.pojo.Department;

import java.util.List;

//
//import com.supply_chain.pojo.Department;
//import com.supply_chain.vo.PageBean;
//
//public interface DepartmentService {
//    void delById(Integer[] ids);
//
//    void update(Department department);
//
//    Department getById(Integer id);
//
//    PageBean getProducts(Integer page, Integer pageSize, String name);
//}
public interface DepartmentService extends IService<Department> {
    void delById(List<Integer> ids);
    void update(Department department);
    Department getById(Integer id);
    Page<Department> getDepartments(Integer page,Integer pageSize,String name);
}