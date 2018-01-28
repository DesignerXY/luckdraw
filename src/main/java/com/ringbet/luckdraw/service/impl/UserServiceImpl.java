package com.ringbet.luckdraw.service.impl;

import com.ringbet.luckdraw.dao.UserMapper;
import com.ringbet.luckdraw.model.User;
import com.ringbet.luckdraw.service.UserService;
import com.ringbet.luckdraw.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
