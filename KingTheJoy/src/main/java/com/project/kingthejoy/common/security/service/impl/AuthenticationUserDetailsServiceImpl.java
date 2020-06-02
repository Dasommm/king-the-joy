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
    public User loadUserByUsername(String member_id, String member_pw) throws UsernameNotFoundException {
		
		MemberDto usersVo = new MemberDto();
		MemberDto memberDto = new MemberDto();
		memberDto.setMember_id(member_id);
		
	    usersVo = biz.memberView(memberDto);
	    
	    
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		int roleNo = usersVo.getMember_role();
		System.out.println(roleNo);
		if (roleNo == 0) { 
		    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));                                   
		} else if(roleNo == 1 || roleNo == 2) { 
		    authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));   
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
	
		User user = new User(member_id, member_pw, true, true, true, true, authorities);
	
		return user;
    }
}
