package com.project.kingthejoy.notification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.kingthejoy.member.controller.MemberController;
import com.project.kingthejoy.notification.biz.NotificationBiz;
import com.project.kingthejoy.notification.dto.NotificationDto;

@Controller
public class NotificationController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private NotificationBiz notificationBiz;
	// 공지사항 리스트 출력
	@RequestMapping(value= "/notification.do")
	public String notificationList(Model model) {
		logger.info("notification List Open");
		List<NotificationDto> notificationList = notificationBiz.selectNotificationList();
		model.addAttribute("notificationList",notificationList);
		return "notification/notificationList"; 
	}
	// 공지사항 세부내용
	@RequestMapping(value = "/notificationDetail.do")
	public String notificationOne(Model model, int notification_seq) {
		logger.info("notification Detail notification_seq : " +notification_seq+ " Open");
		NotificationDto notificationDto = notificationBiz.selectNotificationOne(notification_seq);
		model.addAttribute("notificationDto",notificationDto);
		return "notificationDetail";
	}
	// 공지사항 글쓰기
	@RequestMapping(value = "/notificationInsert.do")
	public String notificationInsert() {
		
		return "redirect:notification.do";
	}
	
	
}