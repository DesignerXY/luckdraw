package com.ringbet.luckdraw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ringbet.luckdraw.core.AbstractService;
import com.ringbet.luckdraw.dao.LuckuserMapper;
import com.ringbet.luckdraw.dao.SigninMapper;
import com.ringbet.luckdraw.entity.LuckuserVO;
import com.ringbet.luckdraw.model.Luckuser;
import com.ringbet.luckdraw.model.Signin;
import com.ringbet.luckdraw.service.LuckuserService;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
@Service
@Transactional
public class LuckuserServiceImpl extends AbstractService<Luckuser> implements LuckuserService {
    @Resource
    private LuckuserMapper luckuserMapper;
    @Resource
    private SigninMapper signinMapper;

    @Override
	public List<LuckuserVO> findLastLuckuserVOs() {
    	Signin signin = signinMapper.findLast();
    	if (null == signin || null == signin.getId() || signin.getId().equals(0))
    		return null;
		return luckuserMapper.findLastLuckuserVOs(signin.getId());
	}

	@Override
	public LuckuserVO findLuckuserVO(Integer signinuserid) {
		return luckuserMapper.findLuckuserVO(signinuserid);
	}
    
    
}
