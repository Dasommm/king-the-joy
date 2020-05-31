
package com.project.kingthejoy.children.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.project.kingthejoy.children.biz.ChildrenBiz;
import com.project.kingthejoy.children.dto.ChildrenDto;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

@Controller
@RequestMapping(value = "children")
public class ChildrenController {

	@Autowired
	ChildrenBiz biz;

	private static final Logger logger = LoggerFactory.getLogger(ChildrenDto.class);

	@RequestMapping("/childrenlist.do")
	public String childrenList(Model model, HttpSession session, HttpServletRequest request, Integer page) {
		// 원아 관리 리스트
		logger.info("controller->childrenlist");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();

		PillPagingDto pdto = new PillPagingDto();
		pdto.setRows(10);
		pdto.setPage(page);
		pdto.setTotalpage(biz.totalpage(pdto.getRows(), session));

		model.addAttribute("list", biz.selectList(school_seq, pdto));

		return "children/childrenlist";

	}

	@RequestMapping(value = "/childlistajaxdown.do", method = RequestMethod.POST)
	public @ResponseBody List<ChildrenDto> selectListDown(@RequestParam("page") Integer page, Model model,
			HttpSession session, HttpServletRequest request) {
		// @RequestBody ->HTTP 요청의 body 내용을 자바 객체로 매핑하는 역할을 합니다.
		// @ResponseBody->자바 객체를 HTTP 요청의 body 내용으로 매핑하는 역할을 합니다.

		logger.info("controller->>childrenlistdown");
	    MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();

		PillPagingDto pdto = new PillPagingDto();
		pdto.setRows(10);
		pdto.setPage(page);
		pdto.setTotalpage(biz.totalpage(pdto.getRows(), session));
		// model.addAttribute("list", biz.selectList(school_seq, pdto));
		return biz.selectList(school_seq, pdto);

	}

}

