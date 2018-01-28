package com.ringbet.luckdraw;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration//标注一个类是配置类，spring boot在扫到这个注解时自动加载这个类相关的功能，比如配置AOP和拦截器时加在类上的Configuration  
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)//启用自动配置 该框架就能够进行行为的配置，以引导应用程序的启动与运行, 根据导入的starter-pom 自动加载配置
//@EnableJpaRepositories(basePackages={"com.ringbet.wawa.app.mybatis.repository"})//dao对应的路径
@ComponentScan(value={"com.ringbet.luckdraw.*"})//扫描组件 @ComponentScan(value = "com.spriboot.controller") 配置扫描组件的路径  
@SpringBootApplication
@EntityScan("com.ringbet.wawa.model")
public class Application extends SpringBootServletInitializer  implements CommandLineRunner {
	
	public Application() {
	    super();
	    setRegisterErrorPageFilter(false); // <- this one
	}
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

 
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * ServletRegistrationBean,
	 * @see com.alibaba.druid.support.http.ResourceServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean druid = new ServletRegistrationBean();
		druid.setServlet(new StatViewServlet());
		druid.setUrlMappings(Arrays.asList("/druid/*"));
		
		Map<String,String> params = new HashMap<>();
		params.put("loginUsername", "admin");
		params.put("loginPassword", "admin");
		druid.setInitParameters(params);
		return druid;
	}
	
	/**
	 * @see com.alibaba.druid.support.http.WebStatFilter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean webStatFilter(){
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		fitler.setFilter(new WebStatFilter());
		fitler.setUrlPatterns(Arrays.asList("/*"));
		fitler.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return fitler;
	}

    
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}