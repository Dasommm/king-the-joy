package com.project.kingthejoy.study.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.study.dao.StudyDao;
import com.project.kingthejoy.study.dto.StudyDto;

@Service
public class StudyBizImpl implements StudyBiz {

	@Autowired
	private StudyDao dao;
	
	@Override
	public int checkSeq() {
		
		return dao.checkSeq();
	}

	@Override
	public int studyBookInsert(StudyDto studyDto) {
		return dao.studyBookInsert(studyDto);
	}

	@Override
	public List<StudyDto> selectStudyBooks(int school_seq) {
		return dao.selectStudyBooks(school_seq);
	}

	@Override
	public StudyDto selectBookOne(int book_seq) {
		return dao.selectBookOne(book_seq);
	}

}