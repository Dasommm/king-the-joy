package com.project.kingthejoy.note.biz;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.note.dao.NoteDao;
import com.project.kingthejoy.note.dto.NoteDto;

@Service
public class NoteBizImpl implements NoteBiz {
	
	@Autowired
	NoteDao noteDao;

	@Override
	public Set<String> selectClass(int school_seq) {
		System.out.println("noteBIzImpl!!!!!!!!!!");
		return noteDao.selectClass(school_seq);
	}

	@Override
	public Set<String> selectChildren(String children_class) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int noteInsertDb(NoteDto noteDto) {
		return noteDao.noteInsertDb(noteDto);
		
	}

}
