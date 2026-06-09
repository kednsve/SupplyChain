package com.supply_chain.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.supply_chain.mapper.ProductMapper;
import com.supply_chain.pojo.Product;
import com.supply_chain.service.ProductService;
import com.supply_chain.utils.ChkNotNull;
import com.supply_chain.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.supply_chain.mapper.ProductMapper;
//import com.supply_chain.vo.PageBean;
//import com.supply_chain.pojo.Product;
//import com.supply_chain.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ProductServiceImp implements ProductService {
//    private final ProductMapper productMapper;
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void delById(Integer[] ids) {
//        productMapper.delById(ids);
//    }
//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void update(Product product) {
//        productMapper.update(product);
//    }
//
//    @Override
//    public Product getById(Integer id) {
//        return productMapper.getById(id);
//    }
//
//    @Override
//    public PageBean getProducts(
//            Integer page,
//            Integer pageSize,
//            String name,
//            BigDecimal priceLow,
//            BigDecimal priceHigh,
//            Integer categoryId,
//            Integer departmentId
//    ) {
//        PageHelper.startPage(page, pageSize);
//        List<Product> list = productMapper.getProducts(name, priceLow, priceHigh, categoryId, departmentId);
//        PageInfo<Product> pageInfo = new PageInfo<>(list);
//        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
//    }
//}
@Service
@RequiredArgsConstructor
public class ProductServiceImp extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public void delById(List<Integer> ids) {
        productMapper.deleteByIds(ids);
    }

    @Override
    public void update(Product product) {
        productMapper.updateById(product);
    }

    @Override
    public ProductVO selById(Integer id) {
        return productMapper.selById(
                new QueryWrapper<Product>().eq("p.id",id)
        );
    }

    @Override
    public Page<ProductVO> getProducts(
            Integer page,
            Integer pageSize,
            String name,
            BigDecimal priceLow,
            BigDecimal priceHigh,
            Integer categoryId,
            Integer departmentId
    ) {
        Page<ProductVO> productPage = new Page<>(page, pageSize);
        return productMapper.getProducts(
                productPage,
                new LambdaQueryWrapper<Product>()
                        .like(ChkNotNull.check(name), Product::getName, name)
                        .ge(ChkNotNull.check(priceLow), Product::getPrice, priceLow)
                        .le(ChkNotNull.check(priceHigh), Product::getPrice, priceHigh)
                        .eq(ChkNotNull.check(categoryId), Product::getCategoryId, categoryId)
                        .eq(ChkNotNull.check(departmentId), Product::getDepartmentId, departmentId)
        );
    }
}