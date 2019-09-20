package com.example.usersapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
        .inMemoryAuthentication()
        .withUser("admin").password("{noop}admin").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http)   throws Exception{
		
		http.authorizeRequests().
		antMatchers("/user").permitAll()
		   .antMatchers(HttpMethod.GET, "/**").permitAll()
           .antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
           .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN").and()
		//.anyRequest().authenticated()
		//.and()
		.httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.csrf().disable();
		
	}
}
