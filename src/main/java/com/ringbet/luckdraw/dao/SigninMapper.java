package com.ringbet.luckdraw.dao;

import com.ringbet.luckdraw.core.Mapper;
import com.ringbet.luckdraw.model.Signin;

public interface SigninMapper extends Mapper<Signin> {
	
	Signin findLast();
	
	int updateEndtime(Signin entity);
}