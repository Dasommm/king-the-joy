package com.project.kingthejoy.note.dao;

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
	public Set<String> selectClass(int school_seq) {
		
		List<String> selectClass = sqlSessionTemplate.selectList(NAMESPACE+"selectClass",school_seq);
		System.out.println("noteDaoImpl list:::::::::::"+selectClass.toString());
		Collection<String> collection = selectClass;
		
		Set<String> set = new HashSet<String>(collection);
		System.out.println(set);
		
		
		return set;

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
		System.out.println("noteInsertRES ="+res);
		return res;

	}

}
