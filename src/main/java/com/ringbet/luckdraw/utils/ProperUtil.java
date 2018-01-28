package com.ringbet.luckdraw.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProperUtil {
	
    static Logger log = LoggerFactory.getLogger(ProperUtil.class);
	public static final String fileName = "configuration.properties";
	
	private static Properties properties = new Properties(); 
	static{ 
		try { 
			properties.load(ProperUtil.class.getClassLoader().getResourceAsStream(fileName));//通过反射机制取得WEB工程ClassPath下属性文件 
		} catch (Exception e) { 
			log.error(" msg : ",e); 
		} 
	} 

	//读取properties的全部信息
    public static void readProperties() {
        try {
            Enumeration en = properties.propertyNames();
             while (en.hasMoreElements()) {
              String key = (String) en.nextElement();
                    String Property = properties.getProperty (key);
                }
        } catch (Exception e) {
        	log.error(" msg : ",e);
        }
    }
    
  //根据key读取value
    public static String readValue(String key) {
           try {
            String value = properties.getProperty (key);
               return value;
           } catch (Exception e) {
            log.error(" msg : ",e);
            return null;
           }
    }
    
  //写入properties信息
    public static void writeProperties(String filePath,String parameterName,String parameterValue) {
     try {
            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(filePath);
            properties.setProperty(parameterName, parameterValue);
            //以适合使用 load 方法加载到 Properties 表中的格式，
            //将此 Properties 表中的属性列表（键和元素对）写入输出流
            properties.store(fos, "Update '" + parameterName + "' value");
        } catch (IOException e) {
        	log.error("Visit "+filePath+" for updating "+parameterName+" value error");
        }
    }
}
