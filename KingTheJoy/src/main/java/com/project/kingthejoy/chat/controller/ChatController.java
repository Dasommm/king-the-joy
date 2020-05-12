package com.project.kingthejoy.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import javax.websocket.RemoteEndpoint.Basic;





@Controller
@ServerEndpoint(value = "/chat.do")
public class ChatController {
	private static final List<Session> sessionList = new ArrayList<Session>();
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	public ChatController() {
		System.out.println("웹소켓(서버) 생성");
	}
	
	@RequestMapping(value = "/chatStart.do")
	public ModelAndView getChatViewPage(ModelAndView mav) {
		mav.setViewName("chat");
		return mav;
	}
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Open session id :"+session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sessionList.add(session);
	}
	
	public void sendAllSessionToMessage(Session self, String message) {
		try {
            for(Session session : ChatController.sessionList) {
                if(!self.getId().equals(session.getId())) {
                    session.getBasicRemote().sendText(message.split(",")[1]+" : "+message);
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
	}
	@OnMessage
    public void onMessage(String message,Session session) {
        logger.info("Message From "+message.split(",")[1] + ": "+message.split(",")[0]);
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText("to : "+message);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sendAllSessionToMessage(session, message);
    }
    @OnError
    public void onError(Throwable e,Session session) {
        
    }
    @OnClose
    public void onClose(Session session) {
        logger.info("Session "+session.getId()+" has ended");
        sessionList.remove(session);
    }
    @RequestMapping(value = "chatgo.do")
    public String goChat() {
    	return "common/chat";
    }
}
