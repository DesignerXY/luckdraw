package com.ringbet.luckdraw.service;
import com.ringbet.luckdraw.core.Service;
import com.ringbet.luckdraw.model.Signin;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
public interface SigninService extends Service<Signin> {

	Signin findLast();
	
	int updateEndtime(Signin entity);
}
