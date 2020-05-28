package com.project.kingthejoy.note.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.kingthejoy.member.controller.MemberController;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.note.biz.NoteBiz;
import com.project.kingthejoy.note.dto.NoteDto;

import lombok.val;

@Controller
@RequestMapping("note")
public class NoteController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	NoteBiz notebiz;
	
	@RequestMapping(value = "/note.do")
	public String noteForm(Model model, HttpSession session, int member_seq, int children_seq) {
		//System.out.println(member_seq);
		//System.out.println(children_seq);
		model.addAttribute("member_seq", member_seq);
		model.addAttribute("children_seq", children_seq);
		logger.info("알림장 양식");
			
		return "/note/noteMain";
	}
	
	@RequestMapping(value = "/noteInsert.do", method = RequestMethod.POST)
	public String noteInsert(NoteDto noteDto, Model model, HttpSession session) {
		logger.info("알림장 DB저장하기");
		//System.out.println(noteDto.getMember_seq()); 		//부모 seq
		//System.out.println(noteDto.getChildren_seq());	//자녀 seq
		System.out.println(noteDto.toString());	//알림장 입력 값
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");	//로그인 정보 가져오기(알림장 보내는 사람=선생)
		
		NoteDto receiverInfo = notebiz.selectParentNum(noteDto);	//부모 전화번호, 아이이름 가져오기
		noteDto.setNote_receiver(receiverInfo.getMember_phone());
		noteDto.setMember_seq(memberDto.getMember_seq());
		noteDto.setChildren_name(receiverInfo.getChildren_name());
		System.out.println("DB에 저장할 값::::"+noteDto.toString());
		
		int insertDbNoteSeq = notebiz.noteInsertDb(noteDto);	//입력한 알림장 저장하고, 저장한 알림장 번호 가져옴
		if(insertDbNoteSeq>0) {
			NoteDto sendNote = notebiz.selectNoteInsert(insertDbNoteSeq);	//가져온 알림장 번호로 내용 가져와서 문자 보내기
			System.out.println("::::sendNote controller:::::"+sendNote.toString());

			// 문자보내기
			int sendRes = notebiz.sendText(sendNote);
			if(sendRes>0) {
				// 전송완료 알림띄우기
				model.addAttribute("msg", "전송 완료");
				model.addAttribute("url", "/menu/alertClose.do");
				return "common/alert";				
			}else {
				//전송실패 알림띄우기
				model.addAttribute("msg", "전송실패");
				model.addAttribute("url", "/menu/alertClose.do");
				return "common/alert";
			}
		}else {
			model.addAttribute("msg", "전송에 실패했습니다.");
			model.addAttribute("url", "/menu/alertClose.do");
			return "common/alert";
		}
		
	}
	
	@RequestMapping(value = "/mailform.do")
	public String mailform(HttpSession session, Model model) {
		logger.info(":::::::mailForm controller");
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();
		//해당 유치원의 반
		List<String> selectClass = notebiz.selectClass(school_seq);
		System.out.println("반::::::::::::"+selectClass.toString());
		
		model.addAttribute("clazz", selectClass);
		return "note/mailform";
	}
	
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	public String sendMail(Model model, String mail_title, String mail_body, String mail_class) {
		logger.info(":::::::::메일보내기");
		System.out.println(mail_title+ mail_body+ mail_class);
		//선택된 반 이름으로 학부모 메일 뽑아오기
		List<String> selectEmail = notebiz.selectEmail(mail_class);
		System.out.println("::::::::selectEmail controller:::::::"+selectEmail.toString());
		//메일 보내기 biz
		int res = notebiz.sendEmail(selectEmail, mail_title, mail_body);
		
		if(res >0) {
			//메일 보내기 성공
			model.addAttribute("msg", "메일 발송 성공");
			model.addAttribute("url", "/menu/alertClose.do");
			
			return "common/alert";
		}else {
			//메일 보내기 실패
			model.addAttribute("msg", "메일 발송 실패");
			model.addAttribute("url", "/menu/alertClose.do");
			
			return "common/alert";			
		}
		
	}
	
	@RequestMapping(value = "/parentNote.do")
	public String parentNote(HttpSession session, Model model) {
		//부모님용 알림장 확인
		MemberDto noteMemDto = (MemberDto) session.getAttribute("memberDto");	
		
		//로그인 된(부모)의 번호를 가져와서 번호로 select해서 알림장 가져오기
		String member_phone = noteMemDto.getMember_phone();
				
		//receiver에 저장되어있는 번호와 로그인 번호와 맞는 것 뽑기
		//알림장 내용들 
		List<NoteDto> selectAll = notebiz.selectNote(member_phone);
		System.out.println("알림장 뿌리자~~~~~~~"+selectAll.toString());
		model.addAttribute("noteSelect", selectAll);
		
		return "note/noteParent";
	}
	
}
