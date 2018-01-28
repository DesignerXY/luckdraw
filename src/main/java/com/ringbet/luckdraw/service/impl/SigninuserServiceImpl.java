package com.ringbet.luckdraw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringbet.luckdraw.core.AbstractService;
import com.ringbet.luckdraw.dao.SigninMapper;
import com.ringbet.luckdraw.dao.SigninuserMapper;
import com.ringbet.luckdraw.entity.SigninuserVO;
import com.ringbet.luckdraw.model.Signin;
import com.ringbet.luckdraw.model.Signinuser;
import com.ringbet.luckdraw.service.SigninuserService;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
@Service
@Transactional
public class SigninuserServiceImpl extends AbstractService<Signinuser> implements SigninuserService {
    @Resource
    private SigninuserMapper signinuserMapper;
    @Resource
    private SigninMapper signinMapper;

    public Signinuser findBy(Integer signinid, Integer userid) {
    	return signinuserMapper.findBy(signinid, userid);
    }

    public List<SigninuserVO> findLastSigninuserVOs() {
    	Signin signin = signinMapper.findLast();
    	if (null == signin || null == signin.getId() || signin.getId().equals(0))
    		return null;
    	return signinuserMapper.findLastSigninuserVOs(signin.getId());
    }
}
