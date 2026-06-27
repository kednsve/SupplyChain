package com.supply_chain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.dto.ProductDTO;
import com.supply_chain.pojo.Product;
import com.supply_chain.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

//
//import com.supply_chain.pojo.Product;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Mapper
//public interface ProductMapper {
//    void delById(Integer[] ids);
//
//    @Update("update product set name=#{name},image=#{image},price=#{price} where id=#{id}")
//    void update(Product product);
//
//    @Select("select id, name, image, price, category_id, department_id from product where id=#{id}")
//    Product getById(Integer id);
//
//    List<Product> getProducts(
//            String name,
//            BigDecimal priceLow,
//            BigDecimal priceHigh,
//            Integer categoryId,
//            Integer departmentId
//    );
//}
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    Page<ProductVO> getProducts(Page<ProductVO> page, ProductDTO dto);

    ProductVO selById(Integer id);
}