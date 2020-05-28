package com.project.kingthejoy.study.dao;

import java.util.List;

import com.project.kingthejoy.study.dto.StudyDto;

public interface StudyDao {

	public int checkSeq();
	
	public int studyBookInsert(StudyDto studyDto);
	
	public List<StudyDto> selectStudyBooks(int school_seq);
	
	public StudyDto selectBookOne(int book_seq);
	
	public int studyBookDelete(int book_seq);
	
}
