package com.project.kingthejoy.notification.controller;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.project.kingthejoy.member.controller.MemberController;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.notification.biz.NotificationBiz;
import com.project.kingthejoy.notification.dto.NotificationDto;
import com.project.kingthejoy.notification.dto.PagingDto;

@Controller
public class NotificationController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private NotificationBiz notificationBiz;

	// 공지사항 리스트 출력
	@RequestMapping(value = "/notification.do")
	public String notificationList(Model model, HttpSession session, PagingDto pagingDto,
			@RequestParam(value = "nowPage", required = false) String nowPage,
			@RequestParam(value = "cntPerPage", required = false) String cntPerPage) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		
		
		int total = notificationBiz.countList(memberDto.getSchool_seq());
		// 처음 List.do 를 하면 nowPage와 cntPerPage의 값이 없을테니 현재페이지를 1, 페이지당 글 갯수를 10으로 설정
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		}

		// 가져온 값들을 생성자 파라미터르 연산처리를 위해 보내준 후 dto에 대입
		pagingDto = new PagingDto(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		pagingDto.setSchool_seq(memberDto.getSchool_seq());
		model.addAttribute("paging", pagingDto);
		
		logger.info("notification List Open");
		
		model.addAttribute("notificationList", notificationBiz.selectNotificationList(pagingDto));
		System.out.println("검색한 리스트 개수"+ notificationBiz.selectNotificationList(pagingDto).size());
		return "notification/notificationList";
	}

	// 공지사항 세부내용
	@RequestMapping(value = "/notificationDetail.do")
	public String notificationOne(HttpSession session, Model model, int notification_seq) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int res = notificationBiz.selectWritten(memberDto.getMember_seq(), notification_seq);
		System.out.println("공지사항 열람여부 " + res);
		logger.info("notification Detail notification_seq : " + notification_seq + " Open");
		NotificationDto notificationDto = notificationBiz.selectNotificationOne(notification_seq);
		model.addAttribute("notificationDto", notificationDto);
		return "notification/notificationDetail";
	}

	// 공지사항 글쓰기 폼 열기
	@RequestMapping(value = "/notificationInsert.do")
	public String notificationInsert(HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		String school_name = notificationBiz.selectSchoolName(memberDto.getSchool_seq());
		if (memberDto.getMember_role() == 3) {
			System.out.println("공지사항 글쓰기 권한없음");
			model.addAttribute("msg", "공지사항을 작성할 권한이 없습니다.");
			model.addAttribute("url", "notification.do");
			return "common/alert";
		} else {
			model.addAttribute("school_name", school_name);
			return "notification/notificationInsertForm";
		}
	}

	// 공지사항 글 쓰기
	// session 연결하면 회원 번호, 아이디 추가할것
	@RequestMapping(value = "/notificationInsertRes.do")
	public String notificationInsertRes(HttpSession session, Model model, String notification_title,
			String notification_content) {
		logger.info("notification insert res");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setNotification_title(notification_title);
		notificationDto.setNotification_content(notification_content);
		notificationDto.setMember_seq(memberDto.getMember_seq());
		notificationDto.setNotification_writer(memberDto.getMember_name());
		notificationDto.setSchool_seq(memberDto.getSchool_seq());
		int res = notificationBiz.insertNotification(notificationDto);
		if (res > 0) {
			System.out.println("공지사항 등록성공");
			int notification_seq = notificationBiz.newNotification();
			int checkres = notificationBiz.insertNotificationCheck(notification_seq, memberDto.getSchool_seq());
			if (checkres > 0) {
				System.out.println("공지사항 열람여부 등록성공");
				model.addAttribute("msg", "공지사항 작성에 성공하셨습니다.");
				model.addAttribute("url", "notification.do");
				return "common/alert";
			} else {
				notificationBiz.deleteNotification(notification_seq);
				System.out.println("공지사항 열람여부 등록실패");
				model.addAttribute("msg", "공지사항 작성에 실패하셨습니다.");
				model.addAttribute("url", "notificationInsert.do");
				return "common/alert";
			}
		} else {
			System.out.println("공지사항 등록실패");
			model.addAttribute("msg", "공지사항 작성에 실패하셨습니다.");
			model.addAttribute("url", "notificationInsert.do");
			return "common/alert";
		}
	}

	// 공지사항 수정폼 열기
	@RequestMapping(value = "/notificationUpdate.do")
	public String notificationUpdate(HttpSession session, Model model, int notification_seq) {
		logger.info("notification updateForm notification_seq : " + notification_seq + " Open");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		if (memberDto.getMember_role() == 3) {
			model.addAttribute("msg", "공지사항을 수정할 권한이 없습니다.");
			model.addAttribute("url", "notificationUpdate.do?notification_seq=" + notification_seq);
			return "common/alert";
		} else {
			NotificationDto notificationDto = notificationBiz.selectNotificationOne(notification_seq);
			model.addAttribute("notificationDto", notificationDto);
			return "notification/notificationUpdateForm";
		}
	}

	// 공지사항 수정하기
	// session 연결하면 회원번호, 아이디 추가할것
	@RequestMapping(value = "notificationUpdateRes.do")
	public String notificationUpdateRes(Model model, String notification_title, String notification_content,
			int notification_seq) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setNotification_seq(notification_seq);
		notificationDto.setNotification_title(notification_title);
		notificationDto.setNotification_content(notification_content);
		int res = notificationBiz.updateNotification(notificationDto);

		if (res > 0) {
			System.out.println("공지사항 수정성공");
			model.addAttribute("msg", "공지사항 수정에 성공하셨습니다.");
			model.addAttribute("url", "notificationDetail.do?notification_seq=" + notification_seq);
			return "common/alert";
		} else {
			System.out.println("공지사항 수정실패");
			model.addAttribute("msg", "공지사항 수정에 실패하셨습니다.");
			model.addAttribute("url", "notificationUpdate.do?notification_seq=" + notification_seq);
			return "common/alert";
		}
	}

	// 공지사항 삭제
	@RequestMapping(value = "/notificationDelete.do")
	public String notificationDelete(Model model, int notification_seq, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		if (memberDto.getMember_role() == 3) {
			model.addAttribute("msg", "공지사항을 삭제할 권한이 없습니다.");
			model.addAttribute("url", "notificationUpdate.do?notification_seq=" + notification_seq);
			return "common/alert";
		} else {

			int res = notificationBiz.deleteNotification(notification_seq);
			if (res > 0) {
				System.out.println("공지사항 삭제성공");
				model.addAttribute("msg", "공지사항 삭제에 성공하셨습니다.");
				model.addAttribute("url", "notification.do");
				return "common/alert";
			} else {
				System.out.println("공지사항 삭제실패");
				model.addAttribute("msg", "공지사항 삭제에 실패하셨습니다.");
				model.addAttribute("url", "notificationDetail.do?notification_seq=" + notification_seq);
				return "common/alert";
			}
		}
	}

	@RequestMapping(value = "/rollingtest.do", method = RequestMethod.GET)
	@ResponseBody
	public List<NotificationDto> rollingNotification(HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		
		
		List<NotificationDto> notificationList = notificationBiz.selectRollingNotificationList(memberDto.getSchool_seq());
		return notificationList;
	}

}