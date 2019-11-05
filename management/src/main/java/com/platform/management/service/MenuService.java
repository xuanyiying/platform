package com.platform.management.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.common.exception.UniqueException;
import com.platform.common.pagination.PageInfo;
import com.platform.common.pagination.PageParameter;
import com.platform.orm.entity.Menu;

/**
 * @author wangying
 * Created on 2019/10/30.
 */
public interface MenuService extends IService<Menu> {
    /**
     *
     * @param menu
     * @param update
     */
    void checkUnique(Menu menu, boolean update) throws UniqueException;

    /**
     * 
     * @param pageParam
     * @return
     */
    PageInfo<Menu> findByPage(PageParameter<Menu> pageParam);
}
