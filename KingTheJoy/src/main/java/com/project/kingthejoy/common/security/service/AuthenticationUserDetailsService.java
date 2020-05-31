package com.project.kingthejoy.common.security.service;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface AuthenticationUserDetailsService {
	
	User loadUserByUsername(String username, String password) throws UsernameNotFoundException;
}
