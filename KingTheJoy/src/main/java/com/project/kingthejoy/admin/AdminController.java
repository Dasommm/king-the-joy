package com.project.kingthejoy.admin;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.notification.biz.NotificationBiz;
import com.project.kingthejoy.notification.dto.NotificationDto;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private NotificationBiz notificationBiz;
	
	@RequestMapping("/admin.do")
	@ResponseBody
	public String admin() {
		JSONObject child = new JSONObject();
		JSONObject parent = new JSONObject();
		URLConn conn = new URLConn("http://127.0.0.1", 1516);
		
		List<NotificationDto> nList = notificationBiz.selectNotificationList(1);
		for(int i=0; i<nList.size(); i++) {
			child.put("title", nList.get(i).getNotification_title());
			child.put("content", nList.get(i).getNotification_content());
			child.put("writer", nList.get(i).getNotification_writer());
			parent.put(i+"번째글",child);
		}
		conn.urlPost(parent);
		return parent.toString();
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String startApp(@RequestBody String body) {
		log.info("start info = {}", body);
		return "/";
	}

	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {
		JSONObject cred = new JSONObject();
		JSONObject auth = new JSONObject();
		JSONObject parent = new JSONObject();
		cred.put("username", "adm");
		cred.put("password", "pwd");
		auth.put("tenantName", "adm");
		auth.put("passwordCredentials", cred);
		parent.put("auth", auth);
		

		URLConn conn = new URLConn("http://127.0.0.1", 1516);
		conn.urlPost(parent);

		return "index";
	}

}
