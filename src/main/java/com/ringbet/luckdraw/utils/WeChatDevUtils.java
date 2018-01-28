package com.ringbet.luckdraw.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ringbet.luckdraw.entity.Constants;
import com.ringbet.luckdraw.entity.WxUser;
/**
 * 
 *  微信相关链接工具类
 *     
 *  @版本更新列表
 * 	<pre>
 * 	修改版本: 1.0.0
 * 	修改日期：2017年12月18日
 * 	修改人 : xy
 * 	修改说明：
 * 	</pre>
 */
public class WeChatDevUtils {
	private static Logger log = LoggerFactory.getLogger(WeChatDevUtils.class);
	
	public static final String GET_QRCONNECT_URL = "https://open.weixin.qq.com/connect/qrconnect?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_login&state={2}#wechat_redirect";
	public static final String GET_OAUTH_USER_AGREE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state=1#wechat_redirect";
	public static final String GET_OAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	public static final String GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}";
	public static final String CHECK_OAUTH_TOKEN_IS_VALID = "https://api.weixin.qq.com/sns/auth?access_token={0}&openid={1}";
	public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type=refresh_token&refresh_token={1}";

	public static final String SOCIAL_LOGIN_CLIENT_ID = ProperUtil.readValue(Constants.WX.SOCIAL_LOGIN_CLIENT_ID);
	public static final String SOCIAL_LOGIN_CLIENT_SERCRET = ProperUtil.readValue(Constants.WX.SOCIAL_LOGIN_CLIENT_SERCRET);
	
	/**
	 *  wechat login request code
	 */
	public static String getQrconnect(String redirectUri, String state) {
		String getQrcodeUrl = MessageFormat.format(GET_QRCONNECT_URL, SOCIAL_LOGIN_CLIENT_ID, redirectUri, state);
		return getQrcodeUrl;
	}
	
	/**
	 * get authorization address - scope snsapi_userinfo | snsapi_base
	 */
	public static String getAuthrUrl(String redirectUri, String scope) {
		String getCodeUrl = null;
		try {
			getCodeUrl = MessageFormat.format(GET_OAUTH_USER_AGREE_URL, SOCIAL_LOGIN_CLIENT_ID, URLEncoder.encode(redirectUri, "utf-8"), scope);
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
		return getCodeUrl;
	}
	
	/**
	 * by code in exchange for web authorization - access_token
	 */
	public static JSONObject oauth(String appId, String appSecret, String code) {
		String oauthUrl = MessageFormat.format(GET_OAUTH_URL, SOCIAL_LOGIN_CLIENT_ID, SOCIAL_LOGIN_CLIENT_SERCRET, code);
		JSONObject response = HttpClientUtils.sendRequest(oauthUrl);
		return response;
	}
	
	/**
	 * Gets the basic information for the login user
	 */
	public static WxUser getUserInfoBySns(String accessToken, String openId) {
		String getUserInfoUrl = MessageFormat.format(GET_USER_INFO, accessToken, openId);
		String response = HttpClientUtils.doConnet(getUserInfoUrl);
		WxUser wxUserinfo = JSON.parseObject(response, WxUser.class);
		return wxUserinfo;
	}
	
	/**
	 * Check whether the authorization certificate is valid accessToken
	 */
	public static JSONObject checkTokenIsValid(String accessToken, String openId) {
		String checkTokenUrl = MessageFormat.format(CHECK_OAUTH_TOKEN_IS_VALID, accessToken, openId);
		JSONObject response = HttpClientUtils.sendRequest(checkTokenUrl);
		return response;
	}
	
	/**
	 * refresh access_token(if need) refreshToken
	 */
	public static JSONObject refreshToken(String refreshToken) {
		String refreshTokenUrl = MessageFormat.format(REFRESH_TOKEN_URL, SOCIAL_LOGIN_CLIENT_ID, refreshToken);
		JSONObject response = HttpClientUtils.sendRequest(refreshTokenUrl);
		return response;
	}
}
