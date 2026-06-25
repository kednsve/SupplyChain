package com.supply_chain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemsDTO extends PageQuery{
    Integer productId;
    String productName;
    Integer quantityMin;
    Integer quantityMax;
    BigDecimal unitPriceMin;
    BigDecimal unitPriceMAX;
    BigDecimal totalMin;
    BigDecimal totalMax;
}
