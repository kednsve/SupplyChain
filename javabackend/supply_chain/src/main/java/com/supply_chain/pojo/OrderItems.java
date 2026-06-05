package com.supply_chain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {
    // id
    private Integer id;
    // 订单id
    private Integer orderId;
    // 商品id
    private Integer productId;
    // 数量
    private Integer quantity;
    // 单价
    private BigDecimal unitPrice;
    // 总价
    private BigDecimal total;
}
