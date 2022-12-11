package com.project.mytaxapp.mytaxapp.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

//This is the configuration of the spring security.

@Configuration
public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	//This method is to handle the authentication of the users, to allowed them to enter on the application. 
	@Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
	
	//This method just directs the authenticated user to views that they are allowed according to their role.
    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }
        if(roles.contains("ACCOUNTANT")){
            url = "/accountant/dashboard";
        }else if(roles.contains("USER")) {
            url = "/dashboard";
        }
        return url;
    }

}
