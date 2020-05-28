package com.project.kingthejoy.member.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.school.dto.SchoolDto;

public interface MemberDao {

	public int memberJoin(MemberDto memberDto);

	public boolean loginCheck(MemberDto memberDto);

	public MemberDto memberView(MemberDto memberDto);

	public MemberDto snsMemberView(MemberDto membetDto);

	public void logout(HttpSession session);

	public void insertChildInfo(Map<String, String> data);

	public void insertSchoolInfo(Map<String, String> data);

	public SchoolDto selectSchool(SchoolDto schoolDto);

	public int selectChildrenSeqOfSchool(int member_seq);

	public int selectSchoolSeq(Map<String, String> schoolListMap);

	public int insertSchool(Map<String, Integer> schoolMap);

	public int selectSchoolSeqOfParent(int member_seq);

	public boolean snsMemberCheck(Map<String, String> snsMap);

	public int insertSnsInfo(MemberDto memberDto);

	public int selectSchoolSeqOfMasterAndTeacher(int member_seq);

	// 자녀 정보 목록
	public List<ChildrenDto> childrenList(int member_seq);

	// // 유치원 정보
	public String selectSchoolInfo(int school_seq);

	public List<MemberDto> selectMemberAddress(int school_seq);

	public String selectSchoolAddress(int school_seq);

	public String getPw(Map<String, Object> paramMap);

	public String getId(Map<String, Object> paramMap);

	public ChildrenDto getChildren(Map<String, Integer> childrenMap);

	public int memberUpdate(MemberDto memberDto);

}
