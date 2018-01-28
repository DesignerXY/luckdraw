package com.ringbet.luckdraw.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ringbet.luckdraw.service.SigninService;

@Controller
public class IndexController {
	
	@Resource
	private SigninService signinService;

	@RequestMapping(value = {"", "/", "/index"})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "results")
	public String results() {
		return "results";
	}
}
