package com.ringbet.luckdraw.service;
import java.util.List;

import com.ringbet.luckdraw.core.Service;
import com.ringbet.luckdraw.entity.LuckuserVO;
import com.ringbet.luckdraw.model.Luckuser;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
public interface LuckuserService extends Service<Luckuser> {

	List<LuckuserVO> findLastLuckuserVOs();
	
	LuckuserVO findLuckuserVO(Integer signinuserid);
	
}
