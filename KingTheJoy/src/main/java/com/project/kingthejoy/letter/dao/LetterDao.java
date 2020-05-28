package com.project.kingthejoy.letter.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.letter.dto.LetterDto;
import com.project.kingthejoy.member.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class LetterDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int letterTotalrows(int member_seq) {
		int res = 0;
		try {
			res = sqlSession.selectOne("letter.letterTotalrows", member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - letterTotalrows()");
		}
		return res;
	}
	
	public List<LetterDto> selectList(LetterDto letterDto){
		List<LetterDto> list = new ArrayList<LetterDto>();
		try {
			list = sqlSession.selectList("letter.letterList",letterDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - selectList()");
		}
		return list;
	}
	
	public List<LetterDto> selectSendList(LetterDto letterDto){
		List<LetterDto> list = new ArrayList<LetterDto>();
		try {
			list = sqlSession.selectList("letter.selectSendList",letterDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - selectSendList()");
		}
		return list;
	}
	
	public LetterDto selectOne(int letter_seq) {
		LetterDto dto = null;
		try {
			dto = sqlSession.selectOne("letter.selectOne",letter_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - selectOne()");
		} 
		return dto;
	}

	public int letterDelete(String[] letter_chk) {
		int res = 0;
		Map<String,String[]> map = new HashMap<String, String[]>();
		map.put("letter_seqs", letter_chk);
		try {
			res = sqlSession.delete("letter.letterDelete",map);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - letterDelete()");
		} 
		return res;
	}
	
	public int letterDeleteOne(int letter_seq) {
		int res = 0;
		try {
			res = sqlSession.delete("letter.letterDeleteOne",letter_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - letterDeleteOne()");
		} 
		return res;
	}

	public MemberDto senderMemberOne(int member_seq) {
		MemberDto dto = null;
		try {
			dto = sqlSession.selectOne("letter.senderMemberOne",member_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - senderMemberOne()");
		} 
		return dto;
	}
	
	public MemberDto receiverMemberOne(String member_id) {
		MemberDto dto = null;
		try {
			dto = sqlSession.selectOne("letter.receiverMemberOne",member_id);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - receiverMemberOne()");
		} 
		return dto;
	}

	public int letterInsert(LetterDto letterDto) {
		int res = 0;
		try {
			res = sqlSession.insert("letter.letterInsert",letterDto);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - letterInsert()");
		} 
		return res;
	}

	public int isIdSelectOne(String member_id) {
		int res = 0;
		try {
			res = sqlSession.insert("letter.isIdSelectOne",member_id);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - isIdSelectOne()");
		} 
		return res;
	}

	public int letterCheck(int letter_seq) {
		int res = 0;
		try {
			res = sqlSession.update("letter.letterCheck",letter_seq);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - letterCheck()");
		}
		return res;
	}

	public List<String> receiver(List<Integer> memberArray) {
		List<String> list = null;
		try {
			list = sqlSession.selectOne("letter.receiver",memberArray);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("----->ERROR: LetterDao - receiver()");
		}
		return list;
	}

}
