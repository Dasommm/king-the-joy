package com.project.kingthejoy.note.dao;

import java.util.List;
import java.util.Set;

import com.project.kingthejoy.note.dto.NoteDto;

public interface NoteDao {
	
	// 해당유치원 반 찾기
	public List<String> selectClass(int school_seq);

	// 알림장 DB에 저장하기
	public int noteInsertDb(NoteDto noteDto);
	
	// 저장된 알림장 조회하기
	public NoteDto selectNoteInsert(int note_seq);

	//반별 부모님 이메일 가져오기
	public List<String> selectEmail(String mail_class);
	
	//내가 받은 알림장 조회하기
	public List<NoteDto> selectNote(String member_phone);

	//부모 전화번호 가져오기
	public NoteDto selectParentNum(NoteDto noteDto);
}

