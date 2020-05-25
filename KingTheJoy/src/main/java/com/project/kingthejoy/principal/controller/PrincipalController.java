package com.project.kingthejoy.principal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PrincipalController {

	@RequestMapping(value = "/principalHeader.do", method = RequestMethod.GET)
	public String principalHeader() {
		log.info("principalHeader");		
		return "common/PrincipalHeader";
	}
	
	@RequestMapping(value = "/principal.do", method = RequestMethod.GET)
	public String principal() {
		log.info("into:principal");		
		return "main/principalMain";
	}
	
	@RequestMapping(value = "/principalMain.do", method = RequestMethod.GET)
	public String principalMain() {
		log.info("into:principalMain");		
		return "principal/principalMain";
	}
	
	@RequestMapping(value = "/principalTeacherMgt.do", method = RequestMethod.GET)
	public String principalTeacherMgt() {
		log.info("principalTeacherMgt");		
		return "principal/principalTeacherMgt";
	}
	
	@RequestMapping(value = "/portraitUpload.do", method = RequestMethod.GET)
	public String portraitUpload() {
		log.info("portraitUpload");		
		return "principal/portraitUpload";
	}
	
	//To-do
	//if(회원 테이블의 이미지 경로가 비어 (count=0) 있다면) => 등록하시겠습니까?
	//if(회원 테이블의 이미지가 있다면) => 변경하시겠습니까?
	//변경함 => 기존 이미지는 지워집니다. jsResponse
	
	//파일 업로드 (회원아이디로 폴더 생성)
	//파일 경로를 회원 테이블에 업데이트
	
	//선생님 관리 페이지에서 회원 테이블 list로 뿌림
	//이미지 경로를 회원테이블에서 연결
	
	
}
