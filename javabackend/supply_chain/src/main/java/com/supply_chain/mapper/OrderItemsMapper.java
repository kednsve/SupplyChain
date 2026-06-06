package com.supply_chain.mapper;

import com.supply_chain.pojo.OrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderItemsMapper {
    void delByOrderId(Integer[] ids);

    @Update("""
            update order_items
            set quantity = #{quantity},
                unit_price = #{unitPrice}
            where id=#{id}
            """)
    void update(OrderItems orderItems);

    @Select("select id, order_id, product_id, quantity, unit_price, total from order_items where order_id=#{id}")
    List<OrderItems> getByOrderId(Integer id);
}
