package com.platform.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.CheckUnique;
import com.platform.common.enumation.ProductStatus;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.common.pagination.QueryBuilder;
import com.platform.common.util.BeanUtil;
import com.platform.common.util.ImageBase64Util;
import com.platform.message.service.ProductService;
import com.platform.message.vo.ProductVO;
import com.platform.orm.entity.Product;
import com.platform.orm.mapper.ProductMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl <ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo <ProductVO> findByPage(PageParameter <Product> of) {
        QueryWrapper <Product> queryWrapper = QueryBuilder.build(of, null, true);
        Page <Product> page = new Page <>(of.getPage(), of.getLimit());
        IPage <Product> result = productMapper.selectPage(page, queryWrapper);
        List <Product> products = result.getRecords();
        List <ProductVO> records = new ArrayList <>(products.size());
        final ProductVO[] vo = new ProductVO[1];
        products.stream().forEach(item -> records.add(convert(item)));
        return PageInfo.of(result.getTotal(), records, result.getPages());
    }

    private ProductVO convert(Product product) {
        ProductVO vo = new ProductVO();
        BeanUtil.copy(product, vo);
        vo.setImageData(ImageBase64Util.encode(new File(product.getImageUrl())));
        return vo;
    }

    @Override
    public void checkUnique(Product product, boolean update) throws UniqueException {
        CheckUnique.checkUnique(product, productMapper, update, "name");
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(String id) {
        Product product = productMapper.selectById(id);
        product.setStatus(ProductStatus.DELETE.name());
        productMapper.updateById(product);
    }

}
