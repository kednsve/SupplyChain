package com.supply_chain.service;

import com.supply_chain.pojo.Department;
import com.supply_chain.pojo.PageBean;

public interface DepartmentService {
    void delById(Integer[] ids);

    void update(Department department);

    Department getById(Integer id);

    PageBean getProducts(Integer page, Integer pageSize, String name);
}
