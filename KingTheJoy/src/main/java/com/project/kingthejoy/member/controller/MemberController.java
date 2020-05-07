package com.project.kingthejoy.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.member.dto.MemberDto;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberBiz biz;
	
	@RequestMapping(value = "/selectResistForm.do")
	public String selectResistForm() { 
		 
		logger.info("Resist Form Open");
		
		return "selectResist";
	}
	
	@RequestMapping(value= "/resistForm.do")
	public String resistForm(int mem_role, Model model) {
		
		logger.info("Resist Form Open : " + mem_role + "踰�");
		
		model.addAttribute("mem_role", mem_role);
		
		return "resist"; 
	}
	
	@RequestMapping(value="/memberJoin.do", method=RequestMethod.POST)
	public String memberJoin(MemberDto dto) {
		 
		biz.memberJoin(dto);
		
		return "";
	}

}