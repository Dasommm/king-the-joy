package com.project.kingthejoy.translate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "member")
public class TraslateController {
	
	@RequestMapping("/translate.do")
	public String translateRequest() {

		return "parent/parentTranslate";

	}

	@RequestMapping(value = "/transResponse.do", method = RequestMethod.POST)
	@ResponseBody
	public StringBuffer translateResponse(@RequestParam("lang") String language, @RequestParam("bef") String content) {
		BufferedReader br = null;
		StringBuffer res = null;

		try {
			String text = URLEncoder.encode(content, "UTF-8");// URLEncoder class:일반 문자열을 웹에서 통용되는 'x-www-form-urlencoded' 형식으로 변환하는 역할을 담당.
			String postParams = "src_lang=" + language + "&target_lang=kr&query=" + text;
			String apiURL = "https://kapi.kakao.com/v1/translation/translate?" + postParams;

			URL url = new URL(apiURL);//웹의 주소를 나타내는 클래스.
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();//어렵다 이해가 안된다...
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK 8bd3093c0c557eef9ced46dd548a28cb");// 카카오 인증키
			con.setUseCaches(false);// 캐싱 데이터를 받을 지 않받을지
			con.setDoInput(true);// 쓰기 모드 지정
			con.setDoOutput(true);// 읽기 모드 지정

			int responseCode = con.getResponseCode();

			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));//InputStreamReader:바이트->문자
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
