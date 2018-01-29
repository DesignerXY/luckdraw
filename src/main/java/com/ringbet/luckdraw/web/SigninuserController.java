package com.ringbet.luckdraw.web;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ringbet.luckdraw.core.Result;
import com.ringbet.luckdraw.core.ResultGenerator;
import com.ringbet.luckdraw.entity.SigninuserVO;
import com.ringbet.luckdraw.manager.LuckdrawManager;
import com.ringbet.luckdraw.model.Signin;
import com.ringbet.luckdraw.model.Signinuser;
import com.ringbet.luckdraw.model.User;
import com.ringbet.luckdraw.service.SigninService;
import com.ringbet.luckdraw.service.SigninuserService;
import com.ringbet.luckdraw.service.UserService;

/**
* Created by CodeGenerator on 2018/01/24.
*/
@Controller
@RequestMapping("/signinuser")
public class SigninuserController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private SigninService signinService;
    @Resource
    private SigninuserService signinuserService;
    @Resource
    private UserService userService;
    
    private LuckdrawManager ldm = LuckdrawManager.getInstance();
    
    @RequestMapping(value = {"", "/", "/index"})
	public String index(Integer userid, Integer firstRquest, HttpServletRequest request, ModelMap map) throws IOException {
    	log.info(String.format("userid=%d", userid));
    	if (null == firstRquest || firstRquest.equals(0))
    		return "signinuser";
    	
    	Signin signin = signinService.findLast();
    	log.info(JSON.toJSONString(signin));
    	if (null == signin || null != signin.getEndtime()) {
    		map.put("errorMsg", "还没开始签到哦！");
    		return "luckuser";
    	}
    	
    	if (null == userid || userid.equals(0)) {//没有用户信息，去授权页面授权
			return "redirect:/social/wechat/mobile";
		}
    	User user = userService.findById(userid);
    	if (null == user || null == user.getId() || user.getId().equals(0))
    		return "redirect:/social/wechat/mobile";
    	
    	Signinuser su = signinuserService.findBy(signin.getId(), userid);
    	if (null == su || null == su.getId() || su.getId().equals(0)) {//还未签到
    		Date date = new Date();
    		Signinuser signinuser = new Signinuser();
    		signinuser.setCreatetime(date);
    		signinuser.setSigninid(null);
    		signinuser.setSigninid(signin.getId());
    		signinuser.setUpdatetime(date);
    		signinuser.setUserid(userid);
    		
    		log.info(JSON.toJSONString(signinuser));
    		signinuserService.save(signinuser);
    		
    		su = signinuserService.findBy(signin.getId(), userid);
    		if (null == su || null == su.getId() || su.getId().equals(0)) {//还未签到
    			map.put("errorMsg", "签到失败啦亲！");
    			return "luckuser";
    		}
    	}
    	
    	log.info(JSON.toJSONString(su));
    	map.put("signinuser", su);
    	//已经签到，去抽奖
		return "signinuser";
	}

    @ResponseBody
    @RequestMapping("/list")
    public Result detail() {
        List<SigninuserVO> list = ldm.getSigninuserVOs();
        return ResultGenerator.genSuccessResult(list);
    }

}
