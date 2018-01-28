package com.ringbet.luckdraw.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

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
public class HttpClientUtils {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	public static JSONObject sendRequest(String url) {
		JSONObject jsonObject = null;
		String responseStr = doConnet(url);
		if(StringUtils.hasLength(responseStr)){
			jsonObject = JSONObject.parseObject(responseStr);
		}
		return jsonObject;
	}
	
	public static String doConnet(String httpurl) {
		log.info("httpurl=" + httpurl);

		String respStr = null;
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream inputStream = null;
		try {
			url = new URL(httpurl);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			inputStream = httpConn.getInputStream();
			int size = inputStream.available();
			byte[] jsonBytes = new byte[size];
			inputStream.read(jsonBytes);
			respStr = new String(jsonBytes, "UTF-8");
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				httpConn.disconnect();
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
		return respStr;
	}
}
