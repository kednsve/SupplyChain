package com.supply_chain.mapper;

import com.supply_chain.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void delById(Integer[] ids);

    @Update("update category set name = #{name} where id=#{id};")
    void update(Category category);

    @Select("select id, name from category")
    Category getById(Integer id);

    List<Category> getCategories(String name);
}
