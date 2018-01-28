package com.ringbet.luckdraw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ringbet.luckdraw.core.Mapper;
import com.ringbet.luckdraw.entity.LuckuserVO;
import com.ringbet.luckdraw.model.Luckuser;

public interface LuckuserMapper extends Mapper<Luckuser> {
	
	List<LuckuserVO> findLastLuckuserVOs(@Param("signinid") Integer signinid);
	
	LuckuserVO findLuckuserVO(@Param("signinuserid") Integer signinuserid);
	
}