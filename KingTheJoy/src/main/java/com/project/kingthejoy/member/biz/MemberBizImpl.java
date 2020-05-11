package com.project.kingthejoy.member.biz;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.member.dao.MemberDao;



@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public int memberJoin(MemberDto dto) {
		
		return dao.memberJoin(dto);
	}

	@Override
	public boolean loginCheck(MemberDto dto, HttpSession session) {
		boolean result = dao.loginCheck(dto);
		if(result) {

			MemberDto memberDto = memberView(dto);
			
			session.setAttribute("memberDto", memberDto);	

		}
		return result;
	}

	@Override
	public MemberDto memberView(MemberDto dto) {
		return dao.memberView(dto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

}
