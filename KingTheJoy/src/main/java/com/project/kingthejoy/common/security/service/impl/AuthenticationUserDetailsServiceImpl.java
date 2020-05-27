package com.project.kingthejoy.common.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.kingthejoy.common.security.service.AuthenticationUserDetailsService;
import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.member.dto.MemberDto;

public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService{
	
	@Autowired
	MemberBiz biz;
	
	@Override
    public User loadUserByUsername(String userId, String password) throws UsernameNotFoundException {
		
		MemberDto usersVo = new MemberDto();
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_id(userId);
		
	    usersVo = biz.memberView(memberDto);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
		if (usersVo.getMember_role() == 1 ||
				usersVo.getMember_role() == 0 ||
				usersVo.getMember_role() == 2 ) { 
		    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));                            
		} else { // �Ϲ�
		    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	
		User user = new User(userId, password, true, true, true, true, authorities);
	
		return user;
    }
}
