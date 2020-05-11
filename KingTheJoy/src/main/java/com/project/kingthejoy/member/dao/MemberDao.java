package com.project.kingthejoy.member.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.school.dto.SchoolDto;

public interface MemberDao {

	public int memberJoin(MemberDto memberDto);
	
	public boolean loginCheck(MemberDto memberDto);
	
	public MemberDto memberView(MemberDto memberDto);
	
	public void logout(HttpSession session);
	
	public void insertChildInfo(Map<String,String> data);
	
	public void insertSchoolInfo(Map<String, String> data);

	public SchoolDto selectSchool(SchoolDto schoolDto);
	
	public int selectChildrenSeqOfSchool(int member_seq);
	
	public int selectSchoolSeq(Map<String, String> schoolListMap);
	
	public int insertSchool(Map<String, Integer> schoolMap);
	
	public int selectSchoolSeqOfParent(int member_seq);
} 
