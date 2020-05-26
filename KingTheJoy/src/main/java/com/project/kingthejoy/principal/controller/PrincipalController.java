package com.project.kingthejoy.principal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.kingthejoy.principal.dao.PrincipalDao;
import com.project.kingthejoy.principal.dto.PrincipalDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("principal")
public class PrincipalController {

	@Autowired
	PrincipalDao dao = new PrincipalDao(); 
	
	@RequestMapping(value = "/principalHeader.do", method = RequestMethod.GET)
	public String principalHeader() {
		log.info("into:principalHeader");		
		return "common/PrincipalHeader";
	}
	
	@RequestMapping(value = "/principal.do", method = RequestMethod.GET)
	public String principal() {
		log.info("into:principal");		
		return "main/principalMain";
	}
	
//	@RequestMapping(value = "/principalMain.do", method = RequestMethod.GET)
//	public String principalMain() {
//		log.info("into:principalMain");		
//		return "principal/principalMain";
//	}
	
	@RequestMapping(value = "/principalTeacherMgt.do", method = RequestMethod.GET)
	public String principalTeacherMgt(Model model) {
		log.info("into:principalTeacherMgt");	
		List<PrincipalDto>list = new ArrayList<PrincipalDto>();
		list = dao.teacherList();
		model.addAttribute("list", list);
		return "principal/principalTeacherMgt";
	}
	
	@RequestMapping(value = "/principalFlag.do", method = RequestMethod.GET)
	public void principalFlag(int member_seq, HttpServletResponse response) {
		log.info("into:principalFlag");	
		log.info("탈퇴처리 회원번호: "+member_seq);	
		int res = dao.delFlag(member_seq);
		if(res>0) {
			log.info("탈퇴처리 완료");
		}
		try {
			response.sendRedirect("principalTeacherMgt.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/principalClassChange.do", method = RequestMethod.GET)
	public String principalClassChange(int member_seq, Model model) {
		log.info("into:principalClassChange");	
		log.info("반 변경 회원번호: "+member_seq);	
		model.addAttribute("member_seq", member_seq);
		return "principal/principalClassChange";
	}
	
	@RequestMapping(value = "/principalClassChangeRes.do", method = RequestMethod.GET)
	public String principalClassChangeRes(int member_seq, String content, Model model) {
		log.info("into:principalClassChangeRes");	
		log.info("내용: "+content);
		int res = dao.teacherClassChange(member_seq, content);
		if(res>0) {
			log.info("반 변경 완료");	
		}
		return "principal/principalClassChange";
	}
	
	@RequestMapping(value = "/principalTeacherRegistry.do", method = RequestMethod.GET)
	public String principalTeacherRegistry() {
		log.info("into:principalTeacherRegistry");		
		return "principal/principalTeacherRegistry";
	}
	
	@RequestMapping(value = "/principalTeacherRegistryRes.do", method = RequestMethod.POST)
	public void principalTeacherRegistryRes(PrincipalDto principalDto) {
		log.info("into:principalTeacherRegistryRes");	
		log.info("등록전 member_seq: "+principalDto.getMember_seq()); // insert 전이라 0	
		log.info("school_seq: "+principalDto.getSchool_seq());	
		int res = dao.insertTeacher(principalDto);
		if(res>0) {
			log.info("선생님 등록 성공!");
		}
		int getMemberSeq = dao.teacherGetMember_seq(principalDto.getMember_id());
		principalDto.setMember_seq(getMemberSeq);
		log.info("등록후 member_seq: "+principalDto.getMember_seq());
		int res2 = dao.insertClass(principalDto);
		if(res2>0) {
			log.info("선생님 Class 테이블 등록!");
		}
	}
	
}
