package com.ringbet.luckdraw.entity;
/**
 * 
 *  类功能说明 
 *     
 *  @版本更新列表
 * 	<pre>
 * 	修改版本: 1.0.0
 * 	修改日期：2017年12月15日
 * 	修改人 : xy
 * 	修改说明：
 * 	</pre>
 */
public class Constants {

	public static final String JSON_RESPONSE = "application/json;charset=UTF-8";

	/** 返回Code */
	public interface ResponseCode {
		public static int SUCCESS = 0;
		public static int FAIL = 1;
		public static int ERROR = -1;
		
		public static int EXCLUSIVE_LOGIN = 1001;
		public static int NEED_LOGIN = 1002;
		public static int SESSION_EXPIRED = 1003;
		
		public static int WX_AUTH_FAIL = 100;
		
		public static int ApiMedthodError = 2001;//	请求API接口无效
		public static int DesError = 2002;//	DES加密值不正确
		public static int KeyError = 2003;//	Key值错误
		public static int AgentConfigError = 2004;//	代理商配置错误
		public static int InvalidReq = 2005;//	非法请求，请求头无效
		public static int ParamsError = 2006;	//参数错误
		
		public static int AgentError = 2101;//	代理商无效
		public static int UserError = 2102;	//用户名无效
		public static int UserUnusual = 2103;//	账户异常
		public static int PassWdError = 2104;//	密码无效
		public static int UserPwdError = 2105;//	用户/密码错误
		public static int BalanceError = 2106;//	余额不足
		public static int IpAddrError = 2107;//	Ip地址无效
		public static int OrderExist = 2108;//	订单已存在
		public static int OrderNoExist =2109;//	订单不存在
		public static int DateError =2110;//	日期错误，日期必须在30分钟以内
		
		public static int Undefined = 9999;//未定义代码
	}
	
	/** 状态码 */
	public interface Status {
		/** 无效的/已删除的 */
		public static String DISABLE = "0";
		/** 有效的/已完成的 */
		public static String ENABLE = "1";
		/** 预处理 */
		public static String PRETREATMENT = "2";
		/** 已取消 */
		public static String CANCEL = "3";
		/** 已完成 */
		public static String FINISH = "4";
	}
	
	/** Session Key */
	public interface SessionKey {
		/** 用户和session信息 */
		public static String USER_SESSION = "USER_SESSION";
		/** 当前登录地址 */
		public static String LOGIN_CURRENT_URL = "login_current_url";
		/** 第三方登录state */
		public static String SOCIAL_LOGIN_STATE = "social_login_state";
		/** 授权码 */
		public static String OAUTH_OPEND_ID = "oauth_openid";
		/** 范围token */
		public static String OAUTH_ACCESS_TOKEN = "oauth_access_token";
		/** 刷新token */
		public static String OAUTH_REFRESH_TOKEN = "oauth_refresh_token";
		
		/** token */
	}
	
	
	/** 服务器地址配置KEY */
	public static final String CLIENT_HOST = "client_host";
	
	/** 微信配置KEY */
	public class WX {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "wechat_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCRET = "wechat_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "wechat_redirect_uri";
		public static final String SOCIAL_LOGIN_MOBILE_REDIRECT_URI = "mobile_wechat_redirect_uri";
	}
	
	/** 微博配置KEY */
	public class WEIBO {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "weibo_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCRET = "weibo_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "weibo_redirect_uri";
	}
	
	/** QQ配置KEY */
	public class QQ {
		public static final String SOCIAL_LOGIN_CLIENT_ID = "qq_client_id";
		public static final String SOCIAL_LOGIN_CLIENT_SERCREt = "qq_client_sercret";
		public static final String SOCIAL_LOGIN_REDIRECT_URI = "qq_redirect_uri";
	}
	
	/** 打开方式 */
	public class Source {
		public static final int OTHERS = 0;
		public static final int WEIBO = 1;
		public static final int QQ = 2;
		public static final int WECHAT = 3;
	}
	
	public class Method {
		/** 登录 */
		public static final int LOGIN = 1;
		/** 用户信息 */
		public static final int USER_INFO = 2;
		/** 用户列表 */
		public static final int USER_LIST = 3;
		/** 修改用户 */
		public static final int USER_UPDATE = 4;
		/** 用户玩分记录 */
		public static final int USER_PLAY_LOG = 5;
		/** 用户资金日志 */
		public static final int USER_MONEY_LOG = 6;
		/** 娃娃列表 */
		public static final int WAWA_LIST = 7;
	}

}
