package com.project.kingthejoy.note.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.note.dto.NoteDto;

@Repository
public class NoteDaoImpl implements NoteDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	String NAMESPACE = "note.";

	@Override
	public List<String> selectClass(int school_seq) {
		
		List<String> selectClass = sqlSessionTemplate.selectList(NAMESPACE+"selectClass",school_seq);
		System.out.println("noteDaoImpl list:::::::::::"+selectClass.toString());
		Collection<String> collection = selectClass;
		
		Set<String> set = new HashSet<String>(collection);
		List<String> selectClazz = new ArrayList<String>(set);
		
		System.out.println(selectClazz);
		
		
		return selectClazz;

	}

	@Override
	public Set<String> selectChildren(String children_class) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int noteInsertDb(NoteDto noteDto) {
		System.out.println("noteDaoImpl :::::: noteInsertDB");
		int res = sqlSessionTemplate.insert(NAMESPACE+"noteInsert",noteDto);
		System.out.println("::::::::::noteSeq::::::::"+noteDto.getNote_seq());
		int note_seq = noteDto.getNote_seq();
		System.out.println("noteInsertRES ="+res);
		return note_seq;

	}

	@Override
	public NoteDto selectNoteInsert(int note_seq) {
		NoteDto selectNote = sqlSessionTemplate.selectOne(NAMESPACE+"selectNote",note_seq);
		System.out.println("::::::noteDto::::::"+selectNote.toString());
		
		return selectNote;

	}

	@Override
	public List<String> selectEmail(String mail_class) {
		List<String> selectEmail = sqlSessionTemplate.selectList(NAMESPACE+"selectEmail", mail_class);
		//System.out.println(":::::::selectEmail:::::"+selectEmail.toString());
		return selectEmail;

	}

	@Override
	public List<NoteDto> selectNote(String member_phone) {
		List<NoteDto> selectNote = sqlSessionTemplate.selectList(NAMESPACE+"selectNoteAll", member_phone);
		System.out.println(":::::::::selectNoteAll"+selectNote);		
		
		return selectNote;

	}

}
