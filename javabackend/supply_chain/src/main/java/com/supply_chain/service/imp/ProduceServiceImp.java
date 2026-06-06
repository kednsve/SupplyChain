package com.supply_chain.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supply_chain.mapper.ProductMapper;
import com.supply_chain.pojo.PageBean;
import com.supply_chain.pojo.Product;
import com.supply_chain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduceServiceImp implements ProductService {
    private final ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delById(Integer[] ids) {
        productMapper.delById(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public PageBean getProducts(
            Integer page,
            Integer pageSize,
            String name,
            BigDecimal priceLow,
            BigDecimal priceHigh,
            Integer categoryId,
            Integer departmentId
    ) {
        PageHelper.startPage(page, pageSize);
        List<Product> list = productMapper.getProducts(name, priceLow, priceHigh, categoryId, departmentId);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return new PageBean(pageInfo.getTotal(), pageInfo.getList());
    }
}
