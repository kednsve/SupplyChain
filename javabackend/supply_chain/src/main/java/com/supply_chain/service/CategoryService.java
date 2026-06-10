package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.CategoryDTO;
import com.supply_chain.pojo.Category;

import java.util.List;

//
//import com.supply_chain.pojo.Category;
//import com.supply_chain.vo.PageBean;
//
//public interface CategoryService {
//    void delById(Integer[] ids);
//
//    void update(Category category);
//
//    Category getById(Integer id);
//
//    PageBean getCategories(Integer page, Integer pageSize, String name);
//}
public interface CategoryService extends IService<Category> {
    void delByIds(List<Integer> ids);
    void update(Category category);
    Category getById(Integer id);
    Page<Category> getCategories(CategoryDTO categoryDTO);
}