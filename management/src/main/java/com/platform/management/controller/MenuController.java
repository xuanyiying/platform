package com.platform.management.controller;

import com.alibaba.fastjson.JSON;
import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.management.service.MenuService;
import com.platform.orm.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangying
 * @since 2019-10-25
 */
@RestController
public class MenuController {
	@Autowired
	private MenuService menuService;

	@GetMapping("/manage/menu/{id}")
	@Authentication
	public Menu getMenuById(@PathVariable("id") String id) {
		return menuService.getById(id);
	}

	@PostMapping("/manage/menu")
	@Authentication
	public RequestResult addMenu(@RequestBody Menu menu) throws UniqueException {
		menuService.checkUnique(menu, false);
		menuService.save(menu);
		return RequestResult.success();
	}

	@PutMapping("/manage/menu")
	@Authentication
	public RequestResult updateMenu(@RequestBody Menu menu) throws UniqueException {
		menuService.checkUnique(menu, true);
		menuService.updateById(menu);
		return RequestResult.success();
	}

	@DeleteMapping("/manage/menu/{id}")
	@Authentication
	public RequestResult deleteMenu(@PathVariable String id) {
		menuService.removeById(id);
		return RequestResult.success();
	}

	@GetMapping(path = "/manage/menus")
	@Authentication
	public PageInfo<Menu> getMenuList(@RequestParam(value = "page") int page,
			@RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
			@RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
		PageParameter<Menu> pageParam = PageParameter.<Menu>builder().page(page, limit)
				.search(JSON.parseObject(search, Menu.class)).sort(sort, direction).build();
		return menuService.findByPage(pageParam);
	}

}
