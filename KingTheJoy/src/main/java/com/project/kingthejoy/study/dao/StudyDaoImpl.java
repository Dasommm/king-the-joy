package com.project.kingthejoy.study.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.study.dto.StudyDto;

@Service
public class StudyDaoImpl implements StudyDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int checkSeq() {
		return sqlSession.selectOne("study.checkSeq");
	}

	@Override
	public int studyBookInsert(StudyDto studyDto) {
		return sqlSession.insert("study.studyBookInsert", studyDto);
		
	}

	@Override
	public List<StudyDto> selectStudyBooks(int school_seq) {
		return sqlSession.selectList("study.selectStudyBooks", school_seq);
	}

	@Override
	public StudyDto selectBookOne(int book_seq) {
		return sqlSession.selectOne("study.selectBookOne", book_seq);
	}

}
