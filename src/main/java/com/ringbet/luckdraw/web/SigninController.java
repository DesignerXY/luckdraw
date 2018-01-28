package com.ringbet.luckdraw.web;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ringbet.luckdraw.core.Result;
import com.ringbet.luckdraw.core.ResultGenerator;
import com.ringbet.luckdraw.model.Signin;
import com.ringbet.luckdraw.service.SigninService;

/**
* Created by CodeGenerator on 2018/01/24.
*/
@Controller
@RequestMapping("/signin")
public class SigninController {
    @Resource
    private SigninService signinService;
    
    @RequestMapping(value = {"", "/", "/index"})
	public String index(ModelMap map) {
		Signin signin = signinService.findLast();
		if (null != signin && null == signin.getEndtime()) {	//最新簽到時間已完成，需要新開
			map.put("canSignin", "canSignin");
		}
		return "signin";
	}
	
    @ResponseBody
    @RequestMapping("/start")
    public Result start() {
    	Signin signin = signinService.findLast();
    	if (null != signin && null == signin.getEndtime())
    		return ResultGenerator.genFailResult("Old signin has not finish.");
    	Date date = new Date();
    	signin.setId(null);
    	signin.setBegintime(date);
    	signin.setDescription(null);
    	signin.setEndtime(null);
    	signin.setUpdatetime(date);
    	
        signinService.save(signin);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping("/restart")
    public Result restart() {
    	Signin signin = signinService.findLast();
    	if (null == signin || null == signin.getEndtime())
    		return ResultGenerator.genFailResult("Signin has not finish.");
    	
    	signin.setEndtime(null);
    	signin.setUpdatetime(new Date());
        signinService.updateEndtime(signin);
        return ResultGenerator.genSuccessResult();
    }

    @ResponseBody
    @RequestMapping("/finish")
    public Result finish() {
    	Signin signin = signinService.findLast();
    	if (null == signin || null != signin.getEndtime())
    		return ResultGenerator.genFailResult("Signin is finish.");
    	
    	Date date = new Date();
    	signin.setEndtime(date);
    	signin.setUpdatetime(date);
        signinService.updateEndtime(signin);
        return ResultGenerator.genSuccessResult();
    }

}
