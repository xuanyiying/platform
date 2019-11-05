package com.platform.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.message.vo.ProductVO;
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
	 *
	 * @param pageParam
	 * @return
	 */
	PageInfo<ProductVO> findByPage(PageParameter<Product> pageParam);

	/**
	 *
	 * @param product
	 * @param update
	 * @throws UniqueException
	 */
	void checkUnique(Product product, boolean update) throws UniqueException;

	/**
	 *
	 * @param id
	 */
	void deleteById(String id);
}
