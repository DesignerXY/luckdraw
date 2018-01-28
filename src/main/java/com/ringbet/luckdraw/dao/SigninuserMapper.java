package com.ringbet.luckdraw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ringbet.luckdraw.core.Mapper;
import com.ringbet.luckdraw.entity.SigninuserVO;
import com.ringbet.luckdraw.model.Signinuser;

public interface SigninuserMapper extends Mapper<Signinuser> {
	
	Signinuser findBy(@Param("signinid")Integer signinid, @Param("userid")Integer userid);
	
	public List<SigninuserVO> findLastSigninuserVOs(@Param("signinid")Integer signinid);
}