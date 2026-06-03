package com.supply_chain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    // id
    private Integer id;
    // 类别名称
    private String name;
}
