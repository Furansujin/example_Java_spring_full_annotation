package com.rubis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
@EnableWebMvc  
public class WebAppConfig extends WebMvcConfigurerAdapter {  

        @Override  
        public void addResourceHandlers(ResourceHandlerRegistry registry) {  
                registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
                
        }  
        
        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }
     
        
      
}