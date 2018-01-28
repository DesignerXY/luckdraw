package com.ringbet.luckdraw.web;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ringbet.luckdraw.entity.Constants;
import com.ringbet.luckdraw.entity.WxUser;
import com.ringbet.luckdraw.model.User;
import com.ringbet.luckdraw.service.UserService;
import com.ringbet.luckdraw.utils.ProperUtil;
import com.ringbet.luckdraw.utils.WeChatDevUtils;

/**
 * 
 *  类功能说明 
 *     
 *  @版本更新列表
 * 	<pre>
 * 	修改版本: 1.0.0
 * 	修改日期：2017年12月18日
 * 	修改人 : xy
 * 	修改说明：
 * 	</pre>
 */
@Controller
@RequestMapping("/social/wechat")
public class WeChatController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件
	
	@Resource
	private UserService userService;

	//PC页面登录，扫码登录
	@RequestMapping("/web")
	public void web(HttpServletResponse response) throws IOException {
		String state = getUUID();
		String redirectUrl = String.format(ProperUtil.readValue(Constants.WX.SOCIAL_LOGIN_REDIRECT_URI), ProperUtil.readValue(Constants.CLIENT_HOST));
		String wxRedirectUrl = WeChatDevUtils.getQrconnect(redirectUrl, state);
		log.info(String.format("Wechat pc login redirectUrl[%s] wxRedirectUrl[%s]", redirectUrl, wxRedirectUrl));
		response.sendRedirect(wxRedirectUrl);
	}
	
	//手机微信打开登录
	@RequestMapping("/mobile")
	public void mobile(HttpSession session, HttpServletResponse response) throws IOException {
		session.setAttribute(Constants.SessionKey.SOCIAL_LOGIN_STATE, "1");
		String redirectUrl = String.format(ProperUtil.readValue(Constants.WX.SOCIAL_LOGIN_REDIRECT_URI), ProperUtil.readValue(Constants.CLIENT_HOST));
		String url = WeChatDevUtils.getAuthrUrl(redirectUrl, "snsapi_userinfo");
		log.info(String.format("Wechat client auth url[%s] wxRedirectUrl[%s]", redirectUrl, url));
		response.sendRedirect(url);
	}
	
	//登录回调，PC、mobile共用
	@RequestMapping("/callback")
	public String callback(String code, String state, ModelMap map, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception { // web and mobile common method
		String sessionState = session.getAttribute(Constants.SessionKey.SOCIAL_LOGIN_STATE).toString();
		
		if(state == null || !state.equals(sessionState)) {
			log.error(String.format("Response state[%s] not equals session state[%s]", state, sessionState));
		}
		
		JSONObject result = WeChatDevUtils.oauth(WeChatDevUtils.SOCIAL_LOGIN_CLIENT_ID, WeChatDevUtils.SOCIAL_LOGIN_CLIENT_SERCRET, code);
		log.info("======================Get accesstoken result " + result.toString());
		
		String openId = result.getString("openid");
		String access_token = result.getString("access_token");
		String refresh_token = result.getString("refresh_token");
		session.setAttribute(Constants.SessionKey.OAUTH_OPEND_ID, openId);
		session.setAttribute(Constants.SessionKey.OAUTH_ACCESS_TOKEN, access_token);
		session.setAttribute(Constants.SessionKey.OAUTH_REFRESH_TOKEN, refresh_token);
		
		// by Access Token and openid in exchange for user's base information
		WxUser wxUser = wechatResultJson(session);
		if (null == wxUser) {	//没拿到授权信息，提示登录失败
			map.put("errorMsg", "签到失败了亲，再试试吧！");
			return "redirect:/signinuser";
		}
		
		//看看有没有保存本地，没有就保存下
		User user = chkLogin(wxUser);
	
		log.info("After check login user>>>>>" + JSON.toJSONString(user));
		if (null == user) {
			map.put("errorMsg", "签到信息保存失败了，再试试吧亲！");
			log.info("Redirect to signinuser");
			return "redirect:/signinuser";
		}
		
		String url = "redirect:/signinuser?firstRquest=1&userid=" + user.getId();
		log.info(url);
		return url;
	}
	
	private WxUser wechatResultJson(HttpSession session) throws Exception {		
		if(//session.getAttribute("oauth_name") == null||
				session.getAttribute(Constants.SessionKey.OAUTH_ACCESS_TOKEN) == null
				|| session.getAttribute(Constants.SessionKey.OAUTH_OPEND_ID) == null) {
			log.error("======================== Access Token has expired or does not exist!");
			return null;
		}
		
//		String oauthName = session.getAttribute("oauth_name").toString();
		String openId = session.getAttribute(Constants.SessionKey.OAUTH_OPEND_ID).toString();
		String accessToken = session.getAttribute(Constants.SessionKey.OAUTH_ACCESS_TOKEN).toString();
		String refreshToken = session.getAttribute(Constants.SessionKey.OAUTH_REFRESH_TOKEN).toString();
		
		if(!chkAccessToken(accessToken, openId)) {
			JSONObject jsonResult = WeChatDevUtils.refreshToken(refreshToken);
			log.info("=====================RefreshToken " + jsonResult.toString());
			accessToken = jsonResult.getString("access_token");
		}
		
		WxUser wxUser = WeChatDevUtils.getUserInfoBySns(accessToken, openId);
		if(wxUser == null) {
			log.error("======================== unable to obtain authorization user information!");
			return null;
		}
		
		log.info(String.format("openId[%s], access_token[%s], refresh_token[%s], wxUser[%s]", openId, accessToken, refreshToken, wxUser.toJSON()));
		
		return wxUser;
	}
	
	private boolean chkAccessToken(String accessToken, String openId) throws Exception {
		JSONObject jsonResult = WeChatDevUtils.checkTokenIsValid(accessToken, openId);
		log.info("========================Check TokenIsValid " + jsonResult.toString());
		String errmsg = jsonResult.getString("errmsg");
		if(errmsg.equals("ok")) {
			return true;
		} else {
			return false;
		}
	}
	
	public User chkLogin(WxUser entity) throws IOException {
		User user = entity.wxUserToUser();
		log.info("Check login>>>>>>>>>>>>" + JSON.toJSONString(user));
		User u = userService.findBy("openid", user.getOpenid());
		if (null == u || null == u.getId() || u.getId().equals(0)) {
			user.setAgentCode("10000");
			userService.save(user);
			u = userService.findBy("openid", user.getOpenid());
			if (null == u || null == u.getId() || u.getId().equals(0)) {
				return null;
			}
		}
		return u;
	}
	
	
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		return (s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24)).substring(0, 32);
	}
}
