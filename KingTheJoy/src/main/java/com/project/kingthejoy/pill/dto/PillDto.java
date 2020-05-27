package com.project.kingthejoy.pill.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PillDto {
	
	private int pill_seq;//투약 번호
    private int member_seq;//회원번호
	private String pill_title;
	private String pill_symptom;//증상
	private String pill_type;//약의 종류
	private String pill_special;//주의사항
	private int school_seq;//유치원 번호
	private String member_name;//회원이름(의뢰자)
	private String children_name;//자녀이름
	private Date pill_date;
	
	

}
