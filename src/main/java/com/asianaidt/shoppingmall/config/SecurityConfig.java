package com.asianaidt.shoppingmall.config;

import com.asianaidt.shoppingmall.oauth.CustomLoginSuccessHandler;
import com.asianaidt.shoppingmall.oauth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    
        http.authorizeRequests()
        	.antMatchers("/").permitAll()
            .antMatchers("/user/login").permitAll() 
            .antMatchers("/user/logout").permitAll()
            .antMatchers("/user/signUp").permitAll()
            .antMatchers("/error").permitAll()
            .anyRequest().authenticated()
        .and().logout()
	        .logoutUrl("/user/logout")
	        .logoutSuccessUrl("/")
	        .invalidateHttpSession(true)
        .and().csrf().disable();
        
        http
          	.formLogin()
          	.loginPage("/user/login")
          	.loginProcessingUrl("/user/login")
          	.defaultSuccessUrl("/", true)
          	.failureForwardUrl("/user/login")
          	.permitAll()
            .and()
            .oauth2Login()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }

    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/css/**")
            .antMatchers("/vendor/**")
            .antMatchers("/js/**")
            .antMatchers("/favicon*/**")
            .antMatchers("/img/**")
            .antMatchers("/images/**")
            .antMatchers("/plugins/**");
    }
    
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
    
    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

}
