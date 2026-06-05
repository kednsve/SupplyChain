package com.supply_chain.mapper;

import com.supply_chain.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from `order` where id=#{id}")
    Order getOrderById(Integer id);

    //List<Order> getOrdersAndItems();

    List<Order> getOrders(
            LocalDate start, LocalDate end, Integer customerId, Float salesMin, Float salesMax, String status,
            String region, String country, String city
                         );

    void deleteByCustomerId(Integer[] ids);

    @Update("update `order` set date = #{date},sales=#{sales},status=#{status}," +
            "region=#{region},country=#{country},city=#{city} where id = #{id};")
    void update(Order order);
    void deleteByOrderId(Integer[] id);

    Integer[] getIdByCustomerId(Integer[] ids);
}
