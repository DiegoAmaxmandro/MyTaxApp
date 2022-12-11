package com.project.mytaxapp.mytaxapp.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This is the configuration of the spring security.

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//This method just set the "access-denied" view .
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/access-denied").setViewName("access-denied");
        registry.addViewController("/").setViewName("home");
        
    }

}
