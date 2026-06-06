package com.supply_chain.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.DepartmentMapper;
import com.supply_chain.pojo.Department;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delById(Integer[] ids) {
        departmentMapper.delById(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public Department getById(Integer id) {
        return departmentMapper.getById(id);
    }

    @Override
    public PageBean getProducts(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Department> departments = departmentMapper.getDepartments(name);
        PageInfo<Department> pageInfo = new PageInfo<>(departments);
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }
}
