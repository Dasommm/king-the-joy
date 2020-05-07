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
		return "notification/notificationDetail";
	}
	// 공지사항 글쓰기 폼 열기
	@RequestMapping(value = "/notificationInsert.do")
	public String notificationInsert() {
		return "notification/notificationInsertForm";
	}
	
	// 공지사항 글 쓰기
	// session 연결하면 회원 번호, 아이디 추가할것
	@RequestMapping(value = "/notificationInsertRes.do")
	public String notificationInsertRes(Model model, String notification_title, String notification_content) {
		logger.info("notification insert res");
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setNotification_title(notification_title);
		notificationDto.setNotification_content(notification_content);
		notificationDto.setMember_seq(1);
		notificationDto.setNotification_writer("admin");
		int res = notificationBiz.insertNotification(notificationDto);
		if(res>0) {
			System.out.println("공지사항 등록성공");
			int notification_seq = notificationBiz.newNotification();
			int checkres = notificationBiz.insertNotificationCheck(notification_seq);
			if(checkres>0) {
				System.out.println("공지사항 열람여부 등록성공");
				model.addAttribute("msg","공지사항 작성에 성공하셨습니다.");
				model.addAttribute("url","notification.do");
				return "common/alert";
			}else {
				notificationBiz.deleteNotification(notification_seq);
				System.out.println("공지사항 열람여부 등록실패");
				model.addAttribute("msg","공지사항 작성에 실패하셨습니다.");
				model.addAttribute("url","notificationInsert.do");
				return "common/alert";
			}
		}else {
			System.out.println("공지사항 등록실패");
			model.addAttribute("msg","공지사항 작성에 실패하셨습니다.");
			model.addAttribute("url","notificationInsert.do");
			return "common/alert";
		}
	}
	// 공지사항 수정폼 열기
	@RequestMapping(value = "/notificationUpdate.do")
	public String notificationUpdate(Model model, int notification_seq) {
		logger.info("notification updateForm notification_seq : " +notification_seq+ " Open");
		NotificationDto notificationDto = notificationBiz.selectNotificationOne(notification_seq);
		model.addAttribute("notificationDto", notificationDto);
		return "notification/notificationUpdateForm";
	}
	// 공지사항 수정하기
	// session 연결하면 회원번호, 아이디 추가할것
	@RequestMapping(value = "notificationUpdateRes.do")
	public String notificationUpdateRes(Model model, String notification_title, String notification_content, int notification_seq) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setNotification_seq(notification_seq);
		notificationDto.setNotification_title(notification_title);
		notificationDto.setNotification_content(notification_content);
		System.out.println("컨트롤러 title "+notificationDto.getNotification_title());
		int res = notificationBiz.updateNotification(notificationDto);
		
		if(res>0) {
			System.out.println("공지사항 수정성공");
			model.addAttribute("msg","공지사항 수정에 성공하셨습니다.");
			model.addAttribute("url","notificationDetail.do?notification_seq="+notification_seq);
			return "common/alert";
		}else {
			System.out.println("공지사항 수정실패");
			model.addAttribute("msg","공지사항 수정에 실패하셨습니다.");
			model.addAttribute("url","notificationUpdate.do?notification_seq="+notification_seq);
			return "common/alert";
		}
	}
	
	// 공지사항 삭제
	@RequestMapping(value = "/notificationDelete.do")
	public String notificationDelete(Model model, int notification_seq) {
		int res = notificationBiz.deleteNotification(notification_seq);
		if(res>0) {
			System.out.println("공지사항 삭제성공");
			model.addAttribute("msg","공지사항 삭제에 성공하셨습니다.");
			model.addAttribute("url","notification.do");
			return "common/alert";
		}else {
			System.out.println("공지사항 삭제실패");
			model.addAttribute("msg","공지사항 삭제에 실패하셨습니다.");
			model.addAttribute("url","notificationDetail.do?notification_seq="+notification_seq);
			return "common/alert";
		}
	}
	
	
	
	
}