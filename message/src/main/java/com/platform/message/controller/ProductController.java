package com.platform.message.controller;

import com.platform.message.service.ProductService;
import com.platform.message.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.platform.common.annotation.Authentication;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Product;

/**
 * @author wangying Created on 2019/10/23.
 */
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/product/{id}")
	@Authentication
	public Product getProductById(@PathVariable("id") String id) {
		return productService.getById(id);
	}

	@GetMapping(path = "/products")
	@Authentication
	public PageInfo<ProductVO> getProductList(@RequestParam(value = "page") int page,
											  @RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
											  @RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
		Product product = JSON.parseObject(search, Product.class);
		PageParameter<Product> pageParam = PageParameter.<Product>builder().page(page, limit).search(product)
				.sort(sort, direction).build();
		return productService.findByPage(pageParam);
	}
}
