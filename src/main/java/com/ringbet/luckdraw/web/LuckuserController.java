package com.ringbet.luckdraw.web;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ringbet.luckdraw.core.Result;
import com.ringbet.luckdraw.core.ResultGenerator;
import com.ringbet.luckdraw.entity.LuckuserVO;
import com.ringbet.luckdraw.manager.LuckdrawManager;
import com.ringbet.luckdraw.service.LuckuserService;
import com.ringbet.luckdraw.service.SigninuserService;

/**
* Created by CodeGenerator on 2018/01/24.
*/
@Controller
@RequestMapping("/luckuser")
public class LuckuserController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Resource
    private LuckuserService luckuserService;
    @Resource
    private SigninuserService signinuserService;
    
    private LuckdrawManager ldm = LuckdrawManager.getInstance();
    
    @RequestMapping(value = {"", "/", "/index"})
	public String index(Integer signinuserid, ModelMap map) {
    	log.info("signinuserid>>>"+signinuserid);
    	if (null == signinuserid || signinuserid.equals(0)) {//没有中奖信息
    		map.put("errorMsg", "没找到您的签到信息哦！");
    	}
    	
    	LuckuserVO lu = luckuserService.findLuckuserVO(signinuserid);
    	log.info(JSON.toJSONString(lu));
    	if (null != lu && null != lu.getId() && !lu.getId().equals(0)) {//没查到获奖信息
    		return "redirect:/luckuser/luckdraw?signinuserid="+signinuserid;
    	}
    	map.put("signinuserid", signinuserid);
    	return "luckuser";
    }

    @RequestMapping("/shake")
    public String add(Integer signinuserid, ModelMap map) {
    	map.put("signinuserid", signinuserid);
        return "shake";
    }
    
    @ResponseBody
    @RequestMapping("afterShake")
    public String afterShake(Integer signinuserid) {
    	log.info("signinuserid="+signinuserid);
    	if (null == signinuserid || signinuserid.equals(0))
    		return ResultGenerator.genFailResult("未获取到签到信息哦！").toString();
    	
    	LuckuserVO luvo = luckuserService.findLuckuserVO(signinuserid);
    	log.info(JSON.toJSONString(luvo));
    	if (null != luvo && null != luvo.getId() && !luvo.getId().equals(0))
    		return ResultGenerator.genSuccessResult(JSON.toJSONString(luvo)).toString();
    	
    	if (ldm.isInit())
    		return ResultGenerator.genFailResult("抽奖活动还未开始哦！").toString();
    	if (ldm.isPause())
    		return ResultGenerator.genFailResult("本轮抽奖还已经结束了，下次手要快啊！").toString();
    	if (ldm.isFinish())
    		return ResultGenerator.genFailResult("呜呜……抽奖活动已经结束了！").toString();
    	
    	if (ldm.isStart())
    		luvo = ldm.joinDraw(signinuserid);
    	
    	log.info(JSON.toJSONString(luvo));
    	if (null != luvo && null != luvo.getId() && !luvo.getId().equals(0)) {
    		return ResultGenerator.genSuccessResult(luvo).toString();
    	}
    	
    	return ResultGenerator.genFailResult("居然没抽中，再来过！！").toString();
    }

    @RequestMapping("/luckdraw")
    public String luckdraw(Integer signinuserid, ModelMap map) {
    	log.info("signinuserid="+signinuserid);
    	if (null == signinuserid || signinuserid.equals(0)) {
    		map.put("errorMsg", "未获取到签到信息哦！");
    		return "luckdraw";
    	}
    	
    	LuckuserVO luvo = luckuserService.findLuckuserVO(signinuserid);
    	log.info(JSON.toJSONString(luvo));
    	map.put("luvo", luvo);
    	
    	List<LuckuserVO> list = ldm.getLuckuserVOs();
    	log.info(JSON.toJSONString(list));
    	map.put("luvos", list);
    	
        return "luckdraw";
    }

    @ResponseBody
    @RequestMapping("/list")
    public Result list() {
        List<LuckuserVO> list = ldm.getLuckuserVOs();
        return ResultGenerator.genSuccessResult(list);
    }
    
    
    @RequestMapping("/controller")
    public String controller(ModelMap map) {
    	String status = "init";
        if (ldm.isStart())
        	status = "start";
        else if (ldm.isPause())
        	status = "pause";
        else if (ldm.isFinish())
        	status = "finish";
        map.put("status", status);
        return "controller";
    }
    
    @ResponseBody
    @RequestMapping("/drawStatus")
    public Result drawStatus() {
    	String status = "";
    	if (ldm.isStart())
    		status = "start";
        if (ldm.isPause())
        	status = "pause";
        if (ldm.isFinish())
        	status = "finish";
    	return ResultGenerator.genSuccessResult(status);
    }
    
    @ResponseBody
    @RequestMapping("start")
    public Result start() {
    	if (ldm.startDraw()) {
    		return ResultGenerator.genSuccessResult();
    	}
    	return ResultGenerator.genFailResult("启动抽奖失败！");
    }
    
    @ResponseBody
    @RequestMapping("pause")
    public Result pause() {
    	if (ldm.pauseDraw()) {
    		return ResultGenerator.genSuccessResult();
    	}
    	return ResultGenerator.genFailResult("暂停抽奖失败！");
    }
    
}
