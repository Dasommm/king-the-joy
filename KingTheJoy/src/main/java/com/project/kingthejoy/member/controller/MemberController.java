package com.project.kingthejoy.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



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

import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.member.dto.MemberDto;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberBiz biz;

	@RequestMapping(value = "/home.do")
	public String homepageForm() {

		logger.info("home.jsp on");

		return "common/home";
	}

	@RequestMapping(value = "/main.do")
	public String mainPageForm(HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		System.out.println(memberDto.getMember_role());
		System.out.println(memberDto.getMember_seq());
		if (memberDto.getMember_role() == 0) {
			// 관리자
			return "main/main";
		} else if (memberDto.getMember_role() == 1) {
			// 원장
			return "main/main";
		} else if (memberDto.getMember_role() == 2) {
			// 선생님
			return "main/main";
		} else {
			// 학부모
			return "main/parentMain";
		}

	}

	@RequestMapping(value = "/selectResistForm.do")
	public String selectResistForm() {

		logger.info("Resist Form Open");

		return "member/selectResist";
	}

	@RequestMapping(value = "/resistForm.do")
	public String resistForm(int member_role, Model model) {

		logger.info("Resist Form Open : " + member_role + "번");

		model.addAttribute("member_role", member_role);

		return "member/resist";
	}

	@RequestMapping(value = "/memberJoin.do", method = RequestMethod.POST)
	public String memberJoin(MemberDto dto, Model model) {
		logger.info("Member Join");

		int res = biz.memberJoin(dto);

		if (res > 0) {
			logger.info("회원가입 완료");
			model.addAttribute("msg", "킨더조이에 오신 것을 환영합니다.");
			model.addAttribute("url", "home.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			model.addAttribute("url", "selectResistForm.do");
			return "commom/alert";
		}
	}

	@RequestMapping("/loginCheck.do")
	public String loginCheck(MemberDto dto, HttpSession session, Model model) {

		boolean result = biz.loginCheck(dto, session);

		if (result == true) {
			model.addAttribute("msg", "킨더조이 로그인 성공");
			model.addAttribute("url", "main.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "킨더조이 로그인 실패");
			model.addAttribute("url", "home.do");
			return "common/alert";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session, Model model) {

		biz.logout(session);
		model.addAttribute("msg", "안녕히가세요");
		model.addAttribute("url", "home.do");
		return "common/alert";

	}

	@RequestMapping("/translate.do")
	public String translateRequest() {

		return "parent01/translate";

	}

	@RequestMapping(value = "/transResponse.do", method = RequestMethod.POST)
	@ResponseBody
	public StringBuffer translateResponse(@RequestParam("lang") String language, @RequestParam("bef") String content) {
		BufferedReader br = null;
		StringBuffer res = null;

		try {
			String text = URLEncoder.encode(content, "UTF-8");// 번역될 문장
			String postParams = "src_lang=" + language + "&target_lang=kr&query=" + text;
			String apiURL = "https://kapi.kakao.com/v1/translation/translate?" + postParams;

			URL url = new URL(apiURL);
			// URLConnection: 웹을 통해 데이터를 주고 받음.
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK 8bd3093c0c557eef9ced46dd548a28cb");// 카카오 인증키
			con.setUseCaches(false);// 캐싱 데이터를 받을 지 않받을지
			con.setDoInput(true);// 쓰기 모드 지정
			con.setDoOutput(true);// 읽기 모드 지정

			int responseCode = con.getResponseCode();

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

		} catch (Exception e) {

		}
		String inputLine;
		res = new StringBuffer();

		try {
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
				res.toString().replace("[[", "").replace("]]", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return res;
	}

}