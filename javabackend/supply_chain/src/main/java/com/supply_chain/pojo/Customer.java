package com.supply_chain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    // id
    private Integer id;
    // 姓名
    private String name;
    // 类别
    private String segment;
}
