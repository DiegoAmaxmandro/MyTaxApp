package com.project.mytaxapp.mytaxapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.project.mytaxapp.mytaxapp.service.UserServiceImpl;

//This is the configuration of the spring security.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	//This is injection of the class of configuration "CustomLoginSucessHandler".
    @Autowired
    private CustomLoginSucessHandler sucessHandler;
    
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }
    
  
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    //This method is where all the security permissions will go though as the matches pages for accountants and users, the access of the
    //browser to the css or images files, the path to the login and register pages.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http.authorizeRequests()
                .antMatchers("/", "/login","/register","/css/**", "/img/**").permitAll()
                .antMatchers("/accountant/**").hasAnyAuthority("ACCOUNTANT")
                .antMatchers("/user/**").hasAnyAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(sucessHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

                http.authenticationProvider(authenticationProvider());
                http.headers().frameOptions().sameOrigin();

                return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }

}
