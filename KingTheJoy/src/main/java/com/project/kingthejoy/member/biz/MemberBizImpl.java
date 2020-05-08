package com.project.kingthejoy.member.biz;

import org.springframework.stereotype.Service;

import com.project.kingthejoy.member.dao.MemberDao;
import com.project.kingthejoy.member.dto.MemberDto;

@Service
public class MemberBizImpl implements MemberBiz {

	private MemberDao dao;
	
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void memberJoin(MemberDto dto) {
		dao.memberJoin(dto);
	}

}
