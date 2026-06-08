package com.supply_chain.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    // id
    private Integer id;
    // 产品名称
    private String name;
    // 产品图片
    private String image;
    // 产品单价
    private BigDecimal price;
    // 产品类别id
    private Integer categoryId;
    // 部门id
    private Integer departmentId;
    // 逻辑删除
    @TableLogic
    private Short isDeleted;
}
