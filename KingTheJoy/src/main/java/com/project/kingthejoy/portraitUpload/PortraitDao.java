package com.project.kingthejoy.portraitUpload;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.principal.dto.PrincipalDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PortraitDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int imagePathUpdate(PrincipalDto principalDto){
		int res = 0;
		try {
			res = sqlSession.update("portrait.imagePathInsert",principalDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PortraitDao - imagePathUpdate()");
		}
		return res;
	}

	public String imgPath(int member_seq) {
		String res = null;
		try {
			res = sqlSession.selectOne("portrait.imgPath",member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PortraitDao - imgPath()");
		}
		return res;
	}
	
	public int imageChildPathUpdate(PrincipalDto principalDto){
		int res = 0;
		try {
			res = sqlSession.update("portrait.imageChildPathInsert",principalDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PortraitDao - imageChildPathUpdate()");
		}
		return res;
	}

	public String imgChildPath(int member_seq) {
		String res = null;
		try {
			res = sqlSession.selectOne("portrait.imgChildPath",member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: PortraitDao - imgChildPath()");
		}
		return res;
	}
	
}
