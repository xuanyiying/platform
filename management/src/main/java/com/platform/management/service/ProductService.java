package com.platform.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Product;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
public interface ProductService extends IService<Product> {
	/**
	 *  分页查询
	 * @param pageParam
	 * @return
	 */
	PageInfo<Product> findByPage(PageParameter<Product> pageParam);

	/**
	 * 数据库数据唯一性检测
	 *
	 * @param product
	 * @param update
	 * @throws UniqueException
	 */
	void checkUnique(Product product, boolean update) throws UniqueException;

	/**
	 * 将商品状态设置为DELETE
	 * @param id
	 */
	void deleteById(String id);
}
