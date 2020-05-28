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
import com.project.kingthejoy.member.dto.MemberDto;


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
	private MemberBiz mBiz;
	
	@RequestMapping("/admin.do")
	@ResponseBody
	public String admin() throws IOException {
		JSONObject child = new JSONObject();
		JSONObject parent = new JSONObject();
		
		
		URLConn conn = new URLConn("http://127.0.0.1", 1516);
		return parent.toString();
	}

	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {
		JSONObject cred = new JSONObject();
		JSONObject auth = new JSONObject();
		JSONObject parent = new JSONObject();

		

		URLConn conn = new URLConn("http://127.0.0.1", 1516);
		conn.urlPost(parent);

		return "index";
	}

}
