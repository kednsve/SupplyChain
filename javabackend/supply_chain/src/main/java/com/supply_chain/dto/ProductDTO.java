package com.supply_chain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends PageQuery{
    String name;
    BigDecimal priceLow;
    BigDecimal priceHigh;
    Integer categoryId;
    Integer departmentId;
}
