package com.project.kingthejoy.menu.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.project.kingthejoy.member.controller.MemberController;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.menu.biz.MenuBiz;
import com.project.kingthejoy.menu.dto.MenuDto;

@Controller
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MenuBiz menuBiz;	


	@RequestMapping(value = "/menuList.do")
	@ResponseBody
	public void menuList(HttpSession session, HttpServletResponse response) throws IOException{	
		// 학부모 메인  식단표 List - 유치원별 식단표(school_seq로 불러오기)
		logger.info("menuList 컨트롤러");
		
		//MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		//int school_seq = memberDto.getSchool_seq;
		int school_seq = 1;
		List<MenuDto> menuList = menuBiz.selectAll(school_seq);

		Gson gson = new Gson();
		String menus = gson.toJson(menuList);
		System.out.println("menus:::::::"+menus);
		PrintWriter out = response.getWriter();
		out.println(menus);
		
	}
	
	@RequestMapping(value = "/menuteacher.do")
	public String menuTeacher() {
		
		return "parent/menuTeacher";
		
	}
	
	@RequestMapping(value="/tooltip.do")
	public String tollTipTest() {
		
		return "parent/tooltipTest";
		
	}
	
	@RequestMapping(value = "/menuInsertForm.do")	//식단입력 팝업 페이지로 이동
	public String menuInsert() {
		return "parent/menuInsert";
	}
	
	@RequestMapping(value = "/menuInsertDb.do", method = RequestMethod.POST)
	public String menuInsertDB(HttpSession session, Model model, String start, String title, String description) {
		// 식단 입력
		logger.info("menuInsertDB 컨트롤러");
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		//int school_seq = memberDto.getSchool_seq();
		int school_seq = 1;
		int member_seq = memberDto.getMember_seq();
		//받아온값 뽑아서 수정 후 다시 값 추가
		System.out.println("start:::::::::::::"+start);
		System.out.println("title:::::::::::::"+title);
		System.out.println("desc:::::::::::::"+description);
		String startTemp = "";
		String end ="";
		// type별 시간데이터 추가하기
		if(title.equals("아침")) {
			startTemp = start+"T09:00:00";
			end = start+"T10:00:00";			
		}else if(title.equals("간식")) {
			startTemp = start+"T11:00:00";
			end = start+"T12:00:00";	
		}else {	//중식
			startTemp = start+"T13:00:00";
			end = start+"T14:00:00";	
		}
		
		// 값 추가
		MenuDto menuDto = new MenuDto();
		menuDto.setMember_seq(member_seq);//회원번호
		menuDto.setSchool_seq(school_seq);//유치원번호
		menuDto.setTitle(title);
		menuDto.setDescription(description);
		menuDto.setStart(startTemp);
		menuDto.setEnd(end);
		
		int res = menuBiz.menuInsert(menuDto);
		if(res > 0) {
			//입력성공
			logger.info("식단입력 성공");
			model.addAttribute("msg", "입력 성공");
			model.addAttribute("url", "alertClose.do");
			return "/common/alert";
		}else {
			//입력실패
			logger.info("식단입력 실패");
			model.addAttribute("msg", "입력 실패");
			model.addAttribute("url", "menuInsertForm.do");
			return "/common/alert";
		}
			
	}
	
	@RequestMapping(value = "/alertClose.do")
	public String alertClose() {
		return "/parent/menuAlertClose";
	}

}
