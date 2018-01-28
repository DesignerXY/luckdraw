package com.ringbet.luckdraw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringbet.luckdraw.core.AbstractService;
import com.ringbet.luckdraw.dao.SigninMapper;
import com.ringbet.luckdraw.model.Signin;
import com.ringbet.luckdraw.service.SigninService;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
@Service
@Transactional
public class SigninServiceImpl extends AbstractService<Signin> implements SigninService {
    @Resource
    private SigninMapper signinMapper;

    public Signin findLast() {
    	return signinMapper.findLast();
    }
    
    public int updateEndtime(Signin entity) {
    	return signinMapper.updateEndtime(entity);
    }
}
