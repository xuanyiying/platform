package com.platform.management.controller;

import com.alibaba.fastjson.JSON;
import com.platform.common.RequestResult;
import com.platform.common.annotation.Authentication;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.management.service.OperationService;
import com.platform.orm.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@RestController
public class OperationController {
    @Autowired
    private OperationService operationService;

    @GetMapping("/manage/operation/{id}")
    @Authentication
    public Operation getOperationById(@PathVariable("id") String id) {
        return operationService.getById(id);
    }

    @PostMapping("/manage/operation")
    @Authentication
    public RequestResult addOperation(@RequestBody Operation operation) throws UniqueException {
        operationService.checkUnique(operation, false);
        operationService.save(operation);
        return RequestResult.success();
    }

    @PutMapping("/manage/operation")
    @Authentication
    public RequestResult updateOperation(@RequestBody Operation operation) throws UniqueException {
        operationService.checkUnique(operation, true);
        operationService.updateById(operation);
        return RequestResult.success();
    }

    @DeleteMapping("/manage/operation/{id}")
    @Authentication
    public RequestResult deleteOperation(@PathVariable String id) {
        operationService.removeById(id);
        return RequestResult.success();
    }

    @GetMapping(path = "/manage/operations")
    @Authentication
    public PageInfo<Operation> getOperationList(@RequestParam(value = "page") int page,
                                                  @RequestParam(value = "limit") int limit, @RequestParam(value = "sort") String sort,
                                                  @RequestParam(value = "direction") String direction, @RequestParam(value = "search") String search) {
        PageParameter<Operation> pageParam = PageParameter.<Operation>builder().page(page, limit)
                .search(JSON.parseObject(search, Operation.class)).sort(sort, direction).build();
        return operationService.findByPage(pageParam);
    }

}
