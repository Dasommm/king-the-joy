package com.project.kingthejoy.principal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.principal.dto.PrincipalDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PrincipalDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<PrincipalDto> teacherList(){
		List<PrincipalDto> list = new ArrayList<PrincipalDto>();
		try {
			list = sqlSession.selectList("principal.teacherList");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - selectList()");
		}
		return list;
	}
	
	public int teacherGetMember_seq(String member_id){
		int res = 0;
		try {
			res = sqlSession.selectOne("principal.teacherGetMember_seq", member_id);
			log.warn("가져온 member_seq 값: "+res);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - teacherGetMember_seq()");
		}
		return res;
	}

	public int delFlag(int member_seq) {
		int res = 0;
		try {
			res = sqlSession.update("principal.teacherDrop",member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - delFlag()");
		}
		return res;
	}

	public int teacherClassChange(int member_seq, String content) {
		int res = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_seq", member_seq);
		map.put("content", content);
		try {
			res = sqlSession.update("principal.teacherClassChange",map);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - teacherClassChange()");
		}
		return res;
	}

	public int insertTeacher(PrincipalDto principalDto) {
		int res = 0;
		try {
			res = sqlSession.insert("principal.insertTeacher",principalDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - insertTeacher()");
		}
		return res;
	}
	
	public int insertClass(PrincipalDto principalDto) {
		int res = 0;
		try {
			res = sqlSession.insert("principal.insertClass",principalDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - insertClass()");
		}
		return res;
	}

	public int isClass(int member_seq) {
		int res = 0;
		try {
			res = sqlSession.selectOne("principal.isClass",member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PrincipalDao - isClass()");
		}
		return res;
	}
	
	
}