package com.supply_chain.mapper;
//
//import com.supply_chain.pojo.OrderItems;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import java.util.List;
//
//@Mapper
//public interface OrderItemsMapper {
//    void delByOrderId(Integer[] ids);
//
//    @Update("""
//            update order_items
//            set quantity = #{quantity},
//                unit_price = #{unitPrice}
//            where id=#{id}
//            """)
//    void update(OrderItems orderItems);
//
//    @Select("select id, order_id, product_id, quantity, unit_price, total from order_items where order_id=#{id}")
//    List<OrderItems> getByOrderId(Integer id);
//}

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.dto.OrderItemsDTO;
import com.supply_chain.pojo.OrderItems;
import com.supply_chain.vo.OrderItemsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemsMapper extends BaseMapper<OrderItems> {
    List<OrderItemsVO> selByOrderId(Integer orderId);

    Page<OrderItemsVO> getOrderItems(Page<OrderItemsVO> page, OrderItemsDTO dto);
}