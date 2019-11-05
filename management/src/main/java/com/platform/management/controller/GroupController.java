package com.platform.management.controller;

import com.alibaba.fastjson.JSON;
import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.management.service.GroupService;
import com.platform.orm.entity.Group;
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
public class GroupController {
	@Autowired
	private GroupService groupService;

	@GetMapping("/manage/group/{id}")
	@Authentication
	public Group getGroupById(@PathVariable("id") String id) {
		return groupService.getById(id);
	}

	@PostMapping("/manage/group")
	@Authentication
	public RequestResult addGroup(@RequestBody Group group) throws UniqueException {
		groupService.checkUnique(group, false);
		groupService.save(group);
		return RequestResult.success();
	}

	@PutMapping("/manage/group")
	@Authentication
	public RequestResult updateGroup(@RequestBody Group group) throws UniqueException {
		groupService.checkUnique(group, true);
		groupService.updateById(group);
		return RequestResult.success();
	}

	@DeleteMapping("/manage/group/{id}")
	@Authentication
	public RequestResult deleteGroup(@PathVariable String id) {
		groupService.removeById(id);
		return RequestResult.success();
	}

	@GetMapping(path = "/manage/groups")
	@Authentication
	public PageInfo<Group> getGroupList(@RequestParam(value = "page") int page,
			@RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
			@RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
		PageParameter<Group> pageParam = PageParameter.<Group>builder().page(page, limit)
				.search(JSON.parseObject(search, Group.class)).sort(sort, direction).build();
		return groupService.findByPage(pageParam);
	}

}
