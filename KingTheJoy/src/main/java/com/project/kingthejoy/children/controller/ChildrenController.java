package com.project.kingthejoy.children.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.kingthejoy.children.biz.ChildrenBiz;
import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.children.dto.ChildrenPagingDto;
import com.project.kingthejoy.member.dto.MemberDto;

@Controller
public class ChildrenController {

	@Autowired
	ChildrenBiz biz;

	private static final Logger logger = LoggerFactory.getLogger(ChildrenDto.class);

	@RequestMapping("/childrenlist.do")
	public String childrenList(Model model, HttpSession session, HttpServletRequest request) {
		// 원아 관리 리스트
		logger.info("controller->childrenlist");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		// 변경해라
		// int school_seq = memberDto.getSchool_seq();
		int school_seq = 1;
        model.addAttribute("list", biz.selectList(school_seq));

		return "children/childrenlist";

	}
	
	@RequestMapping("/childrendelete.do")
	public String childrenDelete(Model model, Integer children_seq) {
		
		logger.info("controller.childrendelete");
		System.out.println("children_seq->>"+children_seq);
	
		
		
		return "";
	}

}
