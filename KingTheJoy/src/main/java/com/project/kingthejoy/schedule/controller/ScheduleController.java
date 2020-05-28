package com.project.kingthejoy.schedule.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.schedule.biz.ScheduleBiz;
import com.project.kingthejoy.schedule.dto.ScheduleDto;

@Controller
public class ScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class); 
	
	@Autowired
	ScheduleBiz schedulebiz;
	
	@RequestMapping("/parentSchedule.do")
	public String parentSchedule() {
		logger.info("부모 행사일정 ");
		return "parent/parentSchedule";
	}
	
	@RequestMapping("/selectSchedule.do")
	@ResponseBody
	public void selectSchedule(HttpSession session, HttpServletResponse response ) throws IOException {
		logger.info("selectSchedule Controller::::::::");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();
		
		List<ScheduleDto> selectSchedule = schedulebiz.selectAll(school_seq);
		System.out.println("selectAll controller :::::::::"+selectSchedule.toString());
		Gson gson = new Gson();
		String schedules = gson.toJson(selectSchedule);
		System.out.println(schedules);
		PrintWriter out = response.getWriter();
		out.println(schedules);
		
	}
	
	@RequestMapping("/scheduleOne.do")
	public String scheduleOne(int schedule_seq, Model model) {
		logger.info("행사 조회하기");
		ScheduleDto scheduleOne = schedulebiz.selectOne(schedule_seq);
		model.addAttribute("scheduleOne", scheduleOne);
		System.out.println("이벤트 클릭 ::::::::::::"+scheduleOne.toString());
		
		return "parent/selectSchedule";
	}
	
	@RequestMapping("/teacherSchedule.do")
	public String teacherSchedule() {
		logger.info("선생님용 행사페이지");
		return "parent/teacherSchedule";
	}
	
	@RequestMapping("/scheduleInsertForm.do")
	public String scheduleInsertForm() {
		logger.info("스케쥴 입력 폼");
		return "parent/scheduleForm";
	}
	
	@RequestMapping("/scheduleInsertDb.do")
	public String scheduleInsertDb(ScheduleDto scheduleDto, Model model, HttpSession session) {
		logger.info("디비 입력");
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		int member_seq = memberDto.getMember_seq();
		int school_seq = memberDto.getSchool_seq();
		
		ScheduleDto scheduleInsert = new ScheduleDto();
		scheduleInsert.setMember_seq(member_seq);
		scheduleInsert.setSchool_seq(school_seq);
		scheduleInsert.setSchedule_seq(scheduleDto.getSchedule_seq());
		scheduleInsert.setTitle(scheduleDto.getTitle());
		scheduleInsert.setStart(scheduleDto.getStart()+"T09:00:00");
		scheduleInsert.setEnd(scheduleDto.getEnd()+"T10:00:00");
		scheduleInsert.setDescription(scheduleDto.getDescription());
		scheduleInsert.setColor(scheduleDto.getColor());
		
		int insertRes = schedulebiz.scheduleInsert(scheduleInsert);
		if(insertRes>0) {
			model.addAttribute("msg", "입력성공");
			model.addAttribute("url", "alertClose.do");
			return "common/alert";
		}else {
			model.addAttribute("msg", "입력실패");
			model.addAttribute("url", "parentSchedule.do");
			return "common/alert";
		}
	}
	
	@RequestMapping("/scheduleUpdate.do")
	public String scheduleUpdate(int schedule_seq, Model model) {
		logger.info("스케쥴 업뎃 폼");
		//하나 뽑아서 뿌리기
		ScheduleDto updateform = schedulebiz.selectOne(schedule_seq);
		model.addAttribute("updateform", updateform);
				
		return "parent/scheduleUpdate";
	}
	
	@RequestMapping("/scheduleDelete.do")
	public String deleteSchedule(int schedule_seq, Model model) {		
		int deleteRes = schedulebiz.scheduleDelete(schedule_seq);
		if(deleteRes>0) {
			model.addAttribute("msg", "삭제성공");
			model.addAttribute("url", "parentSchedule.do");
			return "common/alert";
		}else {
			model.addAttribute("msg", "삭제실패");
			model.addAttribute("url", "parentSchedule.do");
			return "common/alert";			
		}
		
	}
	
	@RequestMapping("/scheduleSelect.do")
	@ResponseBody
	public void scheduleSelect(HttpSession session, HttpServletResponse response) throws IOException {
		logger.info("선생님용 리스트뿌리기");
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();
		
		List<ScheduleDto> selectSchdules = schedulebiz.selectAll(school_seq);
		Gson Gson = new Gson();
		String schedules = Gson.toJson(selectSchdules);
		System.out.println(schedules);
		PrintWriter out = response.getWriter();
		out.println(schedules);
	
	}
}
