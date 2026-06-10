package com.supply_chain.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.supply_chain.dto.ProductDTO;
import com.supply_chain.pojo.Product;
import com.supply_chain.vo.ProductVO;

import java.util.List;

//
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.pojo.Product;
//
//import java.math.BigDecimal;
//
//public interface ProductService {
//    void delById(Integer[] ids);
//
//    void update(Product product);
//
//    Product getById(Integer id);
//
//    PageBean getProducts(
//            Integer page,
//            Integer pageSize,
//            String name,
//            BigDecimal priceLow,
//            BigDecimal priceHigh,
//            Integer categoryId,
//            Integer departmentId
//    );
//}
public interface ProductService extends IService<Product> {
    void delById(List<Integer> ids);

    void update(Product product);

    ProductVO selById(Integer id);

    Page<ProductVO> getProducts(ProductDTO productDTO);
}