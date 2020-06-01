package com.project.kingthejoy.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("teacher")
public class TeacherController {

	@RequestMapping(value = "/teacher.do", method = RequestMethod.GET)
	public String teacher() {
		log.info("into: teacher");		
		return "main/teacherMain";
	}
	
//	@RequestMapping(value = "/teacherMain.do", method = RequestMethod.GET)
//	public String teacherMain() {
//		log.info("into: teacherMain");		
//		return "teacher/teacherMain";
//	}
	
}