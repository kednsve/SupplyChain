package com.supply_chain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemsDTO extends PageQuery{
    Integer productId;
    Integer quantity;
    Integer unitPriceLow;
    Integer unitPriceHigh;
}
