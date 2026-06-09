package com.supply_chain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.supply_chain.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

//
//import com.supply_chain.pojo.Customer;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import java.util.List;
//
//@Mapper
//public interface CustomerMapper {
//    @Select("select id,name,segment from Customer where id=#{id}")
//    Customer selectById(Integer id);
//
//    List<Customer> list(String name, String segment);
//
//    void deleteByCustomerId(Integer[] ids);
//
//    @Update("update Customer set name=#{name},segment=#{segment}")
//    void update(Customer customer);
//}
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
}