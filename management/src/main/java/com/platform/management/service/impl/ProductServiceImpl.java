package com.platform.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.platform.management.service.ProductService;
import com.platform.orm.entity.Product;
import com.platform.orm.mapper.ProductMapper;

/**
 *
 * @author wangying
 * @since 2019-10-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public PageInfo<Product> findByPage(PageParameter<Product> of) {
		QueryWrapper<Product> queryWrapper = QueryBuilder.build(of, null, true);
		Page<Product> page = new Page<>(of.getPage(), of.getLimit());
		IPage<Product> result = productMapper.selectPage(page, queryWrapper);
		return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
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
