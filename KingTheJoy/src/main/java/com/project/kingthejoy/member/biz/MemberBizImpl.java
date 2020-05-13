package com.project.kingthejoy.member.biz;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.member.dao.MemberDao;
import com.project.kingthejoy.school.dto.SchoolDto;


@Service
public class MemberBizImpl implements MemberBiz {

	@Autowired
	private MemberDao dao;
	
	@Override
	public int memberJoin(MemberDto memberDto) {
		
		return dao.memberJoin(memberDto);
	}

	@Override
	public boolean loginCheck(MemberDto memberDto, HttpSession session) {
		boolean result = dao.loginCheck(memberDto);
		if(result) {
			MemberDto dto = memberView(memberDto);
			session.setAttribute("memberDto", dto);
		}
		return result; 
	}

	@Override
	public MemberDto memberView(MemberDto memberDto) {
		return dao.memberView(memberDto);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override
	public void insertChildInfo(Map<String, String> data) {
		dao.insertChildInfo(data);
	}

	@Override
	public void insertSchoolInfo(Map<String, String> data) {
		dao.insertSchoolInfo(data);
	}

	@Override
	public SchoolDto selectSchool(SchoolDto schoolDto) {
		return dao.selectSchool(schoolDto);
	}

	@Override
	public int selectChildrenSeqOfSchool(int member_seq) {
		return dao.selectChildrenSeqOfSchool(member_seq);
	}

	@Override
	public int selectSchoolSeq(Map<String, String> schoolListMap) {
		return dao.selectSchoolSeq(schoolListMap);
	}

	@Override
	public int insertSchool(Map<String, Integer> schoolMap) {
		return dao.insertSchool(schoolMap);
	}

	@Override
	public int selectSchoolSeqOfParent(int member_seq) {
		return dao.selectSchoolSeqOfParent(member_seq);
	}

	@Override
	public int selectSchoolSeqOfMasterAndTeacher(int member_seq) {
		return dao.selectSchoolSeqOfMasterAndTeacher(member_seq);
	}

}
