package com.supply_chain.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.CategoryMapper;
import com.supply_chain.pojo.Category;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delById(Integer[] ids) {
        categoryMapper.delById(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public PageBean getCategories(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page, pageSize);
        List<Category> categories = categoryMapper.getCategories(name);
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }
}
