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
import com.platform.management.service.SystemUserService;
import com.platform.orm.entity.SystemUser;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangying
 * @since 2019-10-25
 */
@RestController
public class SystemUserController {
	@Autowired
	private SystemUserService systemUserService;

	@GetMapping("/manage/systemUser/{id}")
	@Authentication
	public SystemUser getSystemUserById(@PathVariable("id") String id) {
		return systemUserService.getById(id);
	}

	@PostMapping("/manage/systemUser")
	@Authentication
	public RequestResult addSystemUser(@RequestBody SystemUser systemUser) throws UniqueException {
		systemUserService.checkUnique(systemUser, false);
		systemUserService.save(systemUser);
		return RequestResult.success();
	}

	@PutMapping("/manage/systemUser")
	@Authentication
	public RequestResult updateSystemUser(@RequestBody SystemUser systemUser) throws UniqueException {
		systemUserService.checkUnique(systemUser, true);
		systemUserService.updateById(systemUser);
		return RequestResult.success();
	}

	@DeleteMapping("/manage/systemUser/{id}")
	@Authentication
	public RequestResult deleteSystemUser(@PathVariable String id) {
		systemUserService.removeById(id);
		return RequestResult.success();
	}

	@GetMapping(path = "/manage/systemUsers")
	@Authentication
	public PageInfo<SystemUser> getSystemUserList(@RequestParam(value = "page") int page,
			@RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
			@RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
		PageParameter<SystemUser> pageParam = PageParameter.<SystemUser>builder().page(page, limit)
				.search(JSON.parseObject(search, SystemUser.class)).sort(sort, direction).build();
		return systemUserService.findByPage(pageParam);
	}

}
