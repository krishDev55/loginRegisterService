package com.login_RegisterService.security.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class MyJwtFilter  extends OncePerRequestFilter{
	
	@Autowired ApplicationContext context;
	
	@Autowired MyJwtService myJwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
										HttpServletResponse response,
												FilterChain filterChain)
									throws ServletException, IOException {
		
			final String authHeader = request.getHeader("Authorization");
			String token=null;
			String username=null;
			
			if(authHeader!=null   && authHeader.startsWith("Bearer ")) {
						token =authHeader.substring(7);
						 username= myJwtService.extractUserName(token);
						
			}
			if(username!=null && 
					SecurityContextHolder.getContext().getAuthentication()==null) {
						UserDetails userDetails = context.getBean(MyUserDetails.class).loadUserByUsername(username);
						
					 if(myJwtService.validateToken(token,userDetails)) {
						  UsernamePasswordAuthenticationToken authToken = 
								  new UsernamePasswordAuthenticationToken(userDetails,
										  									null,
										  								userDetails.getAuthorities()
										  								);
						  authToken
						  	.setDetails(new WebAuthenticationDetailsSource()
						  							.buildDetails(request) 
						  			    );
						
						  SecurityContextHolder
						  				.getContext()
						  				.setAuthentication(authToken);
					 }
			}
			
			else if(SecurityContextHolder.getContext().getAuthentication()!=null){
				  WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder
						  									.getContext()
						  									.getAuthentication()
						  									.getDetails();
			}
			filterChain.doFilter(request, response);
	}

	
}
