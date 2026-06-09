package com.supply_chain.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
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
    // 类别名称
    private String categoryName;
    // 部门id
    private Integer departmentId;
    // 部门名称
    private String departmentName;
    // 逻辑删除
    @TableLogic
    private Short isDeleted;
}