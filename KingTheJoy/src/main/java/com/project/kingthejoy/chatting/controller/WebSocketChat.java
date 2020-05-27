package com.project.kingthejoy.chatting.controller;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="chat")
public class WebSocketChat {
    
	@RequestMapping(value="/chat.do")
    public String getChatViewPage(Model model) {
		
        return "parent/chatting";
    }
	
	@RequestMapping(value="/chattingRoom.do")
	public String getChattingRoom() {
		
		return "principal/chattingRoom";
	}
	
}