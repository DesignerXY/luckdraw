package com.ringbet.luckdraw.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PathConfigurer extends WebMvcConfigurerAdapter {  
  
    @Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");  
        super.addResourceHandlers(registry);  
    }  
  
} 
