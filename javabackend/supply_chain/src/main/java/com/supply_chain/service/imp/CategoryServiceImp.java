package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.mapper.CategoryMapper;
import com.supply_chain.pojo.Category;
import com.supply_chain.service.CategoryService;
import com.supply_chain.utils.ChkNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.CategoryMapper;
//import com.supply_chain.pojo.Category;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CategoryServiceImp implements CategoryService {
//    private final CategoryMapper categoryMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delById(Integer[] ids) {
//        categoryMapper.delById(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Category category) {
//        categoryMapper.update(category);
//    }
//
//    @Override
//    public Category getById(Integer id) {
//        return categoryMapper.getById(id);
//    }
//
//    @Override
//    public PageBean getCategories(Integer page, Integer pageSize, String name) {
//        PageHelper.startPage(page, pageSize);
//        List<Category> categories = categoryMapper.getCategories(name);
//        PageInfo<Category> pageInfo = new PageInfo<>(categories);
//        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
//    }
//}
@Service
@RequiredArgsConstructor
public class CategoryServiceImp extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public void delByIds(List<Integer> ids) {
        categoryMapper.deleteByIds(ids);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public Category getById(Integer id) {
        return null;
    }

    @Override
    public Page<Category> getCategories(Integer page, Integer pageSize, String name) {
        Page<Category> categoryPage = new Page<>(page, pageSize);
        return categoryMapper.selectPage(
                categoryPage,
                new LambdaQueryWrapper<Category>()
                        .like(ChkNotNull.check(name), Category::getName, name)
        );
    }

}