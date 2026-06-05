package com.supply_chain.mapper;

import com.supply_chain.pojo.Logistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface LogisticsMapper {
    List<Logistics> getLogistics(
            Integer daysRealMin, Integer daysRealMax,
            Integer daysScheduledMin, Integer daysScheduledMax,
            String deliveryStatus, Short lateRisk,
            LocalDate shippingDateBegin, LocalDate shippingDateEnd,
            String shippingMode, Integer orderId
    );

    @Select("select * from logistics where order_id = #{orderId}")
    Logistics getByOrderId(Integer orderId);

    void delByOrderId(Integer[] ids);

    @Update("""
            update logistics
            set shipping_days_real     = #{shippingDaysReal},
                shipping_days_scheduled=#{shippingDaysScheduled},
                shipping_date=#{shippingDate},
                late_risk=#{lateRisk},
                delivery_status=#{deliveryStatus},
                shipping_mode=#{shippingMode}
            where id=#{id}""")
    void update(Logistics logistics);
}
