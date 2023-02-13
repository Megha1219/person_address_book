package com.test.addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class will provide authentication and authorization to access API end points.
 * Also defined role specific API's ends points.
 * @author megha.karampuri
 *
 */

@Configuration
@EnableWebSecurity
public class Config  extends WebSecurityConfigurerAdapter{
	
	public void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).hasAnyRole("NORMAL","ADMIN")
			.antMatchers("/person/**").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("User").password(this.passwordEncoder().encode("User")).roles("NORMAL");
		auth.inMemoryAuthentication().withUser("Admin").password(this.passwordEncoder().encode("Admin")).roles("ADMIN");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		}

}
