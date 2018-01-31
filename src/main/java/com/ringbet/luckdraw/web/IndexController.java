package com.ringbet.luckdraw.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ringbet.luckdraw.manager.LuckdrawManager;
import com.ringbet.luckdraw.service.SigninService;

@Controller
public class IndexController {
	
	@Resource
	private SigninService signinService;
	
	private LuckdrawManager ldm = LuckdrawManager.getInstance();

	@RequestMapping(value = {"", "/", "/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "results")
	public String results(ModelMap map) {
		map.put("awardsMap", ldm.getAwardsMap());
		return "results";
	}
}
