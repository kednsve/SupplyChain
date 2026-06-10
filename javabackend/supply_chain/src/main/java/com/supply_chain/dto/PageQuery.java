package com.supply_chain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageQuery {
    Integer page = 1;
    Integer pageSize = 10;
}
