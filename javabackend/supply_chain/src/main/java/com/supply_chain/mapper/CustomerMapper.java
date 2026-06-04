package com.supply_chain.mapper;

import com.supply_chain.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @Select("select * from Customer where id=#{id}")
    Customer selectById(Integer id);

    List<Customer> list(String name, String segment);

    void deleteByCustomerId(Integer[] ids);

    @Update("update Customer set name=#{name},segment=#{segment}")
    Integer update(Customer customer);
}
