package com.platform.management.controller;

import com.alibaba.fastjson.JSON;
import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.management.service.RoleService;
import com.platform.orm.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/manage/role/{id}")
    @Authentication
    public Role getRoleById(@PathVariable("id") String id) {
        return roleService.getById(id);
    }

    @PostMapping("/manage/role")
    @Authentication
    public RequestResult addRole(@RequestBody Role role) throws UniqueException {
        roleService.checkUnique(role, false);
        roleService.save(role);
        return RequestResult.success();
    }

    @PutMapping("/manage/role")
    @Authentication
    public RequestResult updateRole(@RequestBody Role role) throws UniqueException {
        roleService.checkUnique(role, true);
        roleService.updateById(role);
        return RequestResult.success();
    }

    @DeleteMapping("/manage/role/{id}")
    @Authentication
    public RequestResult deleteRole(@PathVariable String id) {
        roleService.removeById(id);
        return RequestResult.success();
    }

    @GetMapping(path = "/manage/roles")
    @Authentication
    public PageInfo<Role> getRoleList(@RequestParam(value = "page") int page,
                                                  @RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
                                                  @RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
        PageParameter<Role> pageParam = PageParameter.<Role>builder().page(page, limit)
                .search(JSON.parseObject(search, Role.class)).sort(sort, direction).build();
        return roleService.findByPage(pageParam);
    }

}
