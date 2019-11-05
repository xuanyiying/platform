package com.platform.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.management.service.ProductService;
import com.platform.orm.entity.Product;

/**
 * @author wangying Created on 2019/10/25.
 */
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/manage/product/{id}")
	@Authentication
	public Product getProductById(@PathVariable("id") String id) {
		return productService.getById(id);
	}

	@PostMapping("/manage/product")
	@Authentication
	public RequestResult addProduct(@RequestBody Product product) throws UniqueException {
		productService.checkUnique(product, false);
		productService.save(product);
		return RequestResult.success();
	}

	@PutMapping("/manage/product")
	@Authentication
	public RequestResult updateProduct(@RequestBody Product product) throws UniqueException {
		productService.checkUnique(product, true);
		productService.updateById(product);
		return RequestResult.success();
	}

	@DeleteMapping("/manage/product/{id}")
	@Authentication
	public RequestResult deleteProduct(@PathVariable String id) {
		productService.deleteById(id);
		return RequestResult.success();
	}

	@GetMapping(path = "/manage/products")
	@Authentication
	public PageInfo<Product> getProductList(@RequestParam(value = "page") int page,
			@RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
			@RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
		PageParameter<Product> pageParam = PageParameter.<Product>builder().page(page, limit)
				.search(JSON.parseObject(search, Product.class)).sort(sort, direction).build();
		return productService.findByPage(pageParam);
	}

}
