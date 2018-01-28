package com.ringbet.luckdraw.service;
import java.util.List;

import com.ringbet.luckdraw.core.Service;
import com.ringbet.luckdraw.entity.SigninuserVO;
import com.ringbet.luckdraw.model.Signinuser;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
public interface SigninuserService extends Service<Signinuser> {

	Signinuser findBy(Integer signinid, Integer userid);

	List<SigninuserVO> findLastSigninuserVOs();
}
