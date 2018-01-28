package com.ringbet.luckdraw.service.impl;

import com.ringbet.luckdraw.dao.AwardsMapper;
import com.ringbet.luckdraw.model.Awards;
import com.ringbet.luckdraw.service.AwardsService;
import com.ringbet.luckdraw.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/24.
 */
@Service
@Transactional
public class AwardsServiceImpl extends AbstractService<Awards> implements AwardsService {
    @Resource
    private AwardsMapper awardsMapper;

}
