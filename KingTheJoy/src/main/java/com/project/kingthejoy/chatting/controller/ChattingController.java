package com.project.kingthejoy.chatting.controller;

 
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.SessionScope;

import com.project.kingthejoy.member.dto.MemberDto;



@Controller
@RequestMapping(value="chat")
public class ChattingController {
    
	@RequestMapping(value="/chat.do")
    public String getChatViewPage(Model model, HttpSession session) {
		
		
			return "parent/chatting";
		
		
    }
	
	
}