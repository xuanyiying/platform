package com.platform.message.service.impl;

import com.platform.message.service.UserRelationshipService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.orm.entity.UserRelationship;
import com.platform.orm.mapper.UserRelationshipMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangying
 * @since 2019-10-22
 */
@Service
public class UserRelationshipServiceImpl extends ServiceImpl<UserRelationshipMapper, UserRelationship>
		implements UserRelationshipService {

}
