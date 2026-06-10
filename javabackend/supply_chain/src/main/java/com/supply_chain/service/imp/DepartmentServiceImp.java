package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.dto.DepartmentDTO;
import com.supply_chain.mapper.DepartmentMapper;
import com.supply_chain.pojo.Department;
import com.supply_chain.service.DepartmentService;
import com.supply_chain.utils.ChkNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.DepartmentMapper;
//import com.supply_chain.pojo.Department;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.service.DepartmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class DepartmentServiceImp implements DepartmentService {
//    private final DepartmentMapper departmentMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delById(Integer[] ids) {
//        departmentMapper.delById(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Department department) {
//        departmentMapper.update(department);
//    }
//
//    @Override
//    public Department getById(Integer id) {
//        return departmentMapper.getById(id);
//    }
//
//    @Override
//    public PageBean getProducts(Integer page, Integer pageSize, String name) {
//        PageHelper.startPage(page, pageSize);
//        List<Department> departments = departmentMapper.getDepartments(name);
//        PageInfo<Department> pageInfo = new PageInfo<>(departments);
//        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
//    }
//}
@Service
@RequiredArgsConstructor
public class DepartmentServiceImp extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Override
    public void delById(List<Integer> ids) {
        departmentMapper.deleteByIds(ids);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    @Override
    public Department getById(Integer id) {
        return departmentMapper.selectById(id);
    }

    @Override
    public Page<Department> getDepartments(DepartmentDTO departmentDTO) {
        Integer page = departmentDTO.getPage();
        Integer pageSize = departmentDTO.getPageSize();
        String name = departmentDTO.getName();
        Page<Department> departmentPage = new Page<>(page, pageSize);
        return departmentMapper.selectPage(
                departmentPage,
                new LambdaQueryWrapper<Department>()
                        .like(ChkNotNull.check(name), Department::getName, name)
        );
    }
}