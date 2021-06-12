package com.burg.recipe.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	/* Authentication : set user/password and set the roles */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("burg").password("{noop}burg").roles("USER").and()
		.withUser("admin").password("{noop}admin").roles("USER","ADMIN");

	}

	/* Authorization : mention which role can access which URL */
	protected void configure(HttpSecurity http) throws Exception
	{
		http.httpBasic().and().authorizeRequests().antMatchers("/recipe/add","/recipe/get").hasRole("USER")
		.antMatchers("/recipe/update","/recipe/delete/{id}").hasRole("ADMIN").and().csrf().disable().headers()
		.frameOptions().disable();
	}
}
