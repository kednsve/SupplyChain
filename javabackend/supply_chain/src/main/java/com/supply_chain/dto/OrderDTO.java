package com.supply_chain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OrderDTO extends PageQuery {
    LocalDate start;
    LocalDate end;
    Integer customerId;
    Float salesMin;
    Float salesMax;
    String status;
    String region;
    String country;
    String city;
}
