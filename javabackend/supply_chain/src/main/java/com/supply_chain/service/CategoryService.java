package com.supply_chain.service;

import com.supply_chain.pojo.Category;
import com.supply_chain.pojo.PageBean;

public interface CategoryService {
    void delById(Integer[] ids);

    void update(Category category);

    Category getById(Integer id);

    PageBean getCategories(Integer page, Integer pageSize, String name);
}
