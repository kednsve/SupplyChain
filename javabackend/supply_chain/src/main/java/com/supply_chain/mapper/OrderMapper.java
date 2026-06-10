package com.supply_chain.mapper;
//
//import com.supply_chain.pojo.Order;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.List;
//
//@Mapper
//public interface OrderMapper {
//    @Select("select id, date, customer_id, sales, status, region, country, city from `order` where id=#{id}")
//    Order getOrderById(Integer id);
//
//    //List<Order> getOrdersAndItems();
//
//    List<Order> getOrders(
//            LocalDate start,
//            LocalDate end,
//            Integer customerId,
//            Float salesMin,
//            Float salesMax,
//            String status,
//            String region,
//            String country,
//            String city
//    );
//
//    void deleteByCustomerId(Integer[] ids);
//
//    @Update("update `order` set date = #{date},sales=#{sales},status=#{status}," +
//            "region=#{region},country=#{country},city=#{city} where id = #{id}")
//    void update(Order order);
//
//    void deleteByOrderId(Integer[] id);
//
//    Integer[] getIdByCustomerId(Integer[] ids);
//
//    @Update("update `order` set sales = #{sales} where id=#{id}")
//    void updateSales(Integer id, BigDecimal sales);
//}

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.supply_chain.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}