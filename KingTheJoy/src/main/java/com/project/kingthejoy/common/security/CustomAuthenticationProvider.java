package com.project.kingthejoy.common.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.kingthejoy.common.security.service.AuthenticationUserDetailsService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	
	@Autowired
	private AuthenticationUserDetailsService authenticationUserDetailsService;
	
	private static final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		String userId = authentication.getName();
		UserDetails userDetails = null;
		try {
			
			userDetails = this.retrieveUser(userId, (UsernamePasswordAuthenticationToken)authentication) ;
		} catch (AuthenticationException ex) {
			throw ex;
		}
		return createSuccessAuthentication(authentication.getName(), (String)authentication.getCredentials(), (UsernamePasswordAuthenticationToken)authentication, userDetails);
	  }
	
	private UserDetails retrieveUser(String userId, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException{
		return this.authenticationUserDetailsService.loadUserByUsername(userId, (String)authentication.getCredentials());
	}
	
	private Authentication createSuccessAuthentication(String principal, String credentials, 
			UsernamePasswordAuthenticationToken authentication, UserDetails userDetails) {
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, credentials, authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
		result.setDetails(authentication.getDetails());
	
		return result;
	}
	
	  @Override
	  public boolean supports(Class<?> authentication) {
	    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	  }
}
