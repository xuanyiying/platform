package com.platform.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.CheckUnique;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.common.pagination.QueryBuilder;
import com.platform.management.service.MenuService;
import com.platform.orm.entity.Menu;
import com.platform.orm.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
@Service
public class MenuServiceImpl extends ServiceImpl <MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    /**
     * @param menu
     * @param update
     */
    @Override
    public void checkUnique(Menu menu, boolean update) throws UniqueException {
        CheckUnique.checkUnique(menu,menuMapper,update,"menuName");
    }

    /**
     * @param of
     * @return
     */
    @Override
    public PageInfo<Menu> findByPage(PageParameter<Menu> of) {
        QueryWrapper<Menu> queryWrapper = QueryBuilder.build(of, null, true);
        Page<Menu> page = new Page <>(of.getPage(), of.getLimit());
        IPage<Menu> result = menuMapper.selectPage(page, queryWrapper);
        return PageInfo.of(result.getTotal(), result.getRecords(), result.getPages());
    }
}
