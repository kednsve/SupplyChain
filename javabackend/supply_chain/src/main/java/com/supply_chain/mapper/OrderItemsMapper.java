package com.supply_chain.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemsMapper {
    Integer delByOrderId(Integer[] ids);
}
