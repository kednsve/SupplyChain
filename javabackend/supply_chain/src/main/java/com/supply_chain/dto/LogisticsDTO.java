package com.supply_chain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LogisticsDTO extends PageQuery {
    Integer daysRealMin;
    Integer daysRealMax;
    Integer daysScheduledMin;
    Integer daysScheduledMax;
    String deliveryStatus;
    Short lateRisk;
    LocalDate shippingDateBegin;
    LocalDate shippingDateEnd;
    String shippingMode;
}
