package com.supply_chain.mapper;
//
//import com.supply_chain.pojo.Department;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import java.util.List;
//
//@Mapper
//public interface DepartmentMapper {
//    void delById(Integer[] ids);
//
//    @Update("update department set name = #{name} where id=#{id}")
//    void update(Department department);
//
//    @Select("select id,name from department where id=#{id}")
//    Department getById(Integer id);
//
//    List<Department> getDepartments(String name);
//}

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.supply_chain.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}