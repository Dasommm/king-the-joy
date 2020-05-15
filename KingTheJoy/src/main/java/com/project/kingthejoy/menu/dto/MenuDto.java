package com.project.kingthejoy.menu.dto;

import lombok.Data;

@Data
public class MenuDto {
	
	private int menu_seq;
	private int member_seq;
	private int school_seq;
	private String title;	// 식단 구분 (조식, 간식, 저녁)
	private String description; // 식단 내용
	private String start; // 시작
	private String end;	//끝 

}
