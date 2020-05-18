package com.project.kingthejoy.note.biz;

import java.util.List;
import java.util.Set;

import com.project.kingthejoy.note.dto.NoteDto;

public interface NoteBiz {
	
	
	// 해당유치원 반 찾기
	public List<String> selectClass(int school_seq);
	
	// 반별 학생들 찾기
	public Set<String> selectChildren(String children_class);
	
	// 알림장 내용 DB에 저장하기
	public int noteInsertDb(NoteDto noteDto);

	// 저장된 알림장 조회하기
	public NoteDto selectNoteInsert(int note_seq);
	
	//문자보내기
	public int sendText(NoteDto noteDto);

	//반별 부모님 이메일 가져오기
	public List<String> selectEmail(String mail_class);

	//메일 보내기
	public int sendEmail(List<String> selectEmail, String mail_title, String mail_body);
	
}
