package com.project.kingthejoy.note.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.kingthejoy.member.controller.MemberController;
import com.project.kingthejoy.note.biz.NoteBiz;
import com.project.kingthejoy.note.dto.NoteDto;

@Controller
public class NoteController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	NoteBiz notebiz;
	
	@RequestMapping(value = "/note.do")
	public String noteForm(Model model, HttpSession session) {
		logger.info("반 데려오기");
		
		//MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		//int school_seq = memberDto.getSchool_seq();
		//해당 유치원의 반
		//Set<String> selectClass = notebiz.selectClass(school_seq);
		//System.out.println("반::::::::::::"+selectClass.toString());
		
		//반 담기
		//model.addAttribute("selectClass", selectClass);
		
		//해당 반의 아이들 이름 
		//List<String> selectChildren = notebiz.selectChildren(children_class);		
		return "/note/noteMain";
	}
	
	@RequestMapping(value = "/noteInsert.do", method = RequestMethod.POST)
	public String noteInsert(NoteDto noteDto, Model model) {
		logger.info("알림장 DB저장하기");

		System.out.println(noteDto.toString());
		
		int noteInsertRes = notebiz.noteInsertDb(noteDto);
		if(noteInsertRes>0) {
			
			// 문자보내기
			// 전송완료 알림띄우기
			return "common/alert";
		}else {
			model.addAttribute("msg", "전송에 실패했습니다.");
			model.addAttribute("url", "");
			return "common/alert";
		}
		
	}
}
