package com.supply_chain.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
    // 逻辑删除
    @TableLogic
    private Short isDeleted;
}
