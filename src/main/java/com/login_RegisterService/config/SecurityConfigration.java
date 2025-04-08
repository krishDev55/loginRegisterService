package com.login_RegisterService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.login_RegisterService.security.service.MyJwtFilter;
import com.login_RegisterService.security.service.MyUserDetails;



@Configuration
public class SecurityConfigration {
  
	@Autowired MyUserDetails myUserDetails;
	@Autowired MyJwtFilter myJwtFilter;
	
  @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			http
				.httpBasic(Customizer.withDefaults())
				.csrf(customizer-> customizer.disable())	
				.authorizeHttpRequests(request->
						request
								.requestMatchers("/api/v1/Login",
												"/api/v1/saveUser")
//								.hasAnyRole("user").anyRequest()
								.permitAll()
								.anyRequest().authenticated())
				.sessionManagement(session->
						session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.addFilter(new UsernamePasswordAuthenticationFilter())
				.addFilterBefore(myJwtFilter,UsernamePasswordAuthenticationFilter.class);
				;
		return http.build();
	}
  
  @Bean
  	AuthenticationProvider authenticationProvider() {
	  	DaoAuthenticationProvider dao= new DaoAuthenticationProvider();
	  	dao.setPasswordEncoder(new BCryptPasswordEncoder(12));
//	  	dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	  	dao.setUserDetailsService(myUserDetails);
	  	return dao;  
  }
  	
  	@Bean
  	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
  		return authenticationConfiguration.getAuthenticationManager();
  	}
}
