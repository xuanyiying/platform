package com.platform.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.common.CheckUnique;
import com.platform.common.enumation.UserPriority;
import com.platform.common.exception.UniqueException;
import com.platform.common.util.BeanUtil;
import com.platform.common.util.ImageBase64Util;
import com.platform.common.util.InvitationCode;
import com.platform.common.util.SecurityUtil;
import com.platform.message.service.UserService;
import com.platform.message.vo.UserVO;
import com.platform.orm.entity.User;
import com.platform.orm.entity.UserRelationship;
import com.platform.orm.mapper.UserMapper;
import com.platform.orm.mapper.UserRelationshipMapper;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangying Created on 2019/9/25.
 */
@Service
public class UserServiceImpl extends ServiceImpl <UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRelationshipMapper userRelationshipMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserVO findById(Integer id) {
        return convert(userMapper.selectById(id));
    }
    private UserVO convert(User user) {
        UserVO vo = new UserVO();
        BeanUtil.copy(user, vo);
        vo.setAvatarData(ImageBase64Util.encode(new File(user.getAvatarUrl())));
        return vo;
    }

    @Override
    public void register(UserVO user) throws UniqueException {
        User child = new User();
        BeanUtil.copy(user, child);
        checkUnique(child, false);
        child.setPriority(UserPriority.ORDINARY.getValue());
        child.setPassword(SecurityUtil.encrypt(user.getPassword()));
        child.setMyInvitationNum(InvitationCode.getInstance().generate(user.getUsername()));
        User parent = userMapper.findByInvitationNum(user.getInvitedNum());
        userMapper.insert(child);
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setChildId(child.getId());
        userRelationship.setParentId(parent.getId());
        userRelationship.setInvitedNum(user.getInvitedNum());
        userRelationshipMapper.insert(userRelationship);

    }

    /**
     * 修改密码
     *
     * @param id
     * @param password
     */
    @Override
    public void resetPassword(Integer id, String password) {
        userMapper.resetPassword(id, password);
    }

    /**
     * 修改用户权限
     *
     * @param user
     */
    @Override
    public void updatePriority(UserVO user) {
        User child = new User();
        BeanUtil.copy(user, child);
        userMapper.updateById(child);
    }

    @Override
    public List<Integer> getUserPriorities(Integer priority) {
        List<Integer> priorities = UserPriority.getValues();
        return priorities.stream().filter(item ->item>priority).collect(Collectors.toList());
    }

    /**
     * @param user
     * @param update
     * @throws UniqueException
     */
    private void checkUnique(User user, boolean update) throws UniqueException {
        CheckUnique.checkUnique(user, userMapper, update, "phone");
        CheckUnique.checkUnique(user, userMapper, update, "username", "phone");
    }

}
