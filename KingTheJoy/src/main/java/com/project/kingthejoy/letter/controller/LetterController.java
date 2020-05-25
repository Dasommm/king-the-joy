package com.project.kingthejoy.letter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.kingthejoy.letter.dao.LetterDao;
import com.project.kingthejoy.letter.dto.LetterDto;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.paging.Paging;
import com.project.kingthejoy.paging.PagingDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LetterController {

	@Autowired
	LetterDao letterDao;
	@Autowired
	Paging paging;
	
	@RequestMapping(value = "/letterPage.do", method = RequestMethod.GET)
	public String letterPage (int currentpage, int member_seq, Model model) {
		log.info("into: letterPage");
		log.info("회원번호 : "+member_seq+"의 받은 쪽지함으로 이동" );
		log.info("현재페이지 : "+currentpage+"입니다." );
		PagingDto pagingDto = new PagingDto();
		LetterDto letterDto = new LetterDto();
		int totalrows = letterDao.letterTotalrows(member_seq);		//전체 로우 갯수
		pagingDto = paging.pagingMethod(currentpage, totalrows);	//페이징 세팅
		letterDto.setMember_seq(member_seq);						//해당 쪽지 유저번호
		letterDto.setFrom(pagingDto.getFrompage());					//시작번호 설정
		letterDto.setTo(pagingDto.getTopage());						//끝 번호 설정
		log.info("보여줄 글 : "+letterDto.getFrom()+"부터" );
		log.info("보여줄 글 : "+letterDto.getTo()+"까지" );
		List<LetterDto> list = letterDao.selectList(letterDto);		//DB에서 자료 받아오기
		model.addAttribute("list", list);
		model.addAttribute("paging", pagingDto);
		model.addAttribute("member_seq", member_seq);
		
		return "letter/letterPage";
	}
	
	@RequestMapping(value = "/letterSendPage.do", method = RequestMethod.GET)
	public String letterSendPage (int currentpage, int member_seq, Model model) {
		log.info("into: letterSendPage");
		log.info("회원번호 : "+member_seq+"의 보낸 쪽지함으로 이동" );
		log.info("현재페이지 : "+currentpage+"입니다." );
		PagingDto pagingDto = new PagingDto();
		LetterDto letterDto = new LetterDto();
		int totalrows = letterDao.letterTotalrows(member_seq);		//전체 로우 갯수
		pagingDto = paging.pagingMethod(currentpage, totalrows);	//페이징 세팅
		// 회원정보 찾기
		MemberDto memberDto = new MemberDto();
		memberDto = letterDao.senderMemberOne(member_seq);
		String letter_sender = memberDto.getMember_id();
		System.out.println(letter_sender);
		letterDto.setLetter_sender(letter_sender);					//보낸 사람 설정
		letterDto.setFrom(pagingDto.getFrompage());					//시작번호 설정
		letterDto.setTo(pagingDto.getTopage());						//끝 번호 설정
		log.info("보여줄 글 : "+letterDto.getFrom()+"부터" );
		log.info("보여줄 글 : "+letterDto.getTo()+"까지" );
		List<LetterDto> list = letterDao.selectSendList(letterDto);	//DB에서 자료 받아오기
		System.out.println(list);	
		
		model.addAttribute("list", list);
		model.addAttribute("paging", pagingDto);
		model.addAttribute("member_seq", member_seq);
		
		return "letter/letterSendPage";
	}
		
	@RequestMapping(value = "/letterDel.do", method = RequestMethod.POST)
	public void letterDel (HttpServletRequest request, HttpServletResponse response) {
		log.info("into: letterDel");
		String[] letter_chk = request.getParameterValues("chk");
		int member_seq = Integer.parseInt(request.getParameter("member_seq"));
		int res = letterDao.letterDelete(letter_chk);
		if(res>0) log.info("쪽지 삭제 완료");
		try {
			response.sendRedirect("letterPage.do?currentpage=1&member_seq="+member_seq);
		} catch (IOException e) {
			e.printStackTrace();
			log.warn("ERROR letterDel: 삭제 후 페이지를 넘어가는데 문제가 발생했습니다.");
		}
	}
	
	@RequestMapping(value = "/letterDelOne.do", method = RequestMethod.GET)
	public void letterDelOne (HttpServletRequest request, HttpServletResponse response) {
		log.info("into: letterDelOne");
		int letter_seq = Integer.parseInt(request.getParameter("letter_seq"));
		int member_seq = Integer.parseInt(request.getParameter("member_seq"));
		int res = letterDao.letterDeleteOne(letter_seq);
		if(res>0) log.info("쪽지 삭제 (1개) 완료");
		try {
			response.sendRedirect("letterPage.do?currentpage=1&member_seq="+member_seq);
		} catch (IOException e) {
			e.printStackTrace();
			log.warn("ERROR letterDel: 삭제 후 페이지를 넘어가는데 문제가 발생했습니다.");
		}
	}
	
	
	@RequestMapping(value = "/letterWrite.do", method = RequestMethod.GET)
	public String letterWrite (int member_seq, Model model) {
		log.info("into: letterWrite");
		model.addAttribute("member_seq",member_seq);
		return "letter/letterWrite";
	}
	
	@RequestMapping(value = "/letterPickedWrite.do", method = RequestMethod.GET)
	public String letterPickedWrite (int member_seq, String letter_sender ,Model model) {
		log.info("into: letterWrite");
		model.addAttribute("member_seq",member_seq);
		model.addAttribute("letter_sender",letter_sender);
		return "letter/letterPickedWrite";
	}
	
	@RequestMapping(value = "/letterWriteRes.do", method = RequestMethod.POST)
	public void letterWriteRes (HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); 
		} catch (UnsupportedEncodingException e1) {
			log.warn("warn: 인코딩 처리 오류...");
			e1.printStackTrace();
		}
		log.info("into: letterWriteRes");
		
		//변수 설정
		String member_id = request.getParameter("member_id");			// 받는 사람
		String letter_title = request.getParameter("letter_title");		// 제목
		String letter_content = request.getParameter("letter_content");	// 내용
		int member_seq = Integer.parseInt(request.getParameter("member_seq")); // 보낸사람 번호
		
		//아이디 존재 확인
		int isId = letterDao.isIdSelectOne(member_id);
		if(isId==0) {
			try {
				log.info("아이디가 없습니다!");
				jsResponse("해당 아이디가 없습니다.", "letterWrite.do?member_seq="+member_seq, response);
				//response.sendRedirect("letterPage.do?currentpage=1&member_seq="+member_seq);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//보낸 사람 정보(id) 찾기
		MemberDto senderMemberDto = new MemberDto();
		senderMemberDto = letterDao.senderMemberOne(member_seq);
		String senderMember_id = senderMemberDto.getMember_id();
		//받는 사람 정보(seq) 찾기
		MemberDto receiverMemberDto = new MemberDto();
		receiverMemberDto = letterDao.receiverMemberOne(member_id);
		int receiverMember_seq = receiverMemberDto.getMember_seq();
		
		//DTO 세팅
		LetterDto letterDto = new LetterDto();
		letterDto.setLetter_sender(senderMember_id);	//보낸 사람
		letterDto.setMember_seq(receiverMember_seq);	//받는 사람 번호
		letterDto.setLetter_title(letter_title);		//제목
		letterDto.setLetter_content(letter_content);	//내용

		int insertRes = letterDao.letterInsert(letterDto);
		if(insertRes>0) log.info("쪽지 보내기 (등록) 완료");
		try {
			response.sendRedirect("letterPage.do?currentpage=1&member_seq="+member_seq);
		} catch (IOException e) {
			e.printStackTrace();
			log.warn("ERROR letterInsert: 쪽지 보내기 이후 페이지를 넘어가는데 문제가 발생했습니다.");
		}	
	}
	
	@RequestMapping(value = "/letterDetail.do", method = RequestMethod.GET)
	public String letterDetail (int member_seq, int letter_seq ,Model model) {
		log.info("into: letterDetail");
		LetterDto letterDto = new LetterDto();
		//해당 쪽지 찾기
		letterDto = letterDao.selectOne(letter_seq);
		//쪽지 확인 변경
		int res = letterDao.letterCheck(letter_seq);
		if(res>0) {
			log.info(letter_seq+"번 쪽지 읽음처리 완료");
		}
		model.addAttribute("member_seq",member_seq);
		model.addAttribute("letterDto",letterDto);
		return "letter/letterDetail";
	}
	
	//자바스크립트 메시지 응답
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); 
		String res = "<script type='text/javascript'>"
					+"alert('"+msg+"');"
					+ "location.href='"+url+"';"
					+ "</script>";
		PrintWriter out = response.getWriter();
		out.println(res);
	}
	
	
}
