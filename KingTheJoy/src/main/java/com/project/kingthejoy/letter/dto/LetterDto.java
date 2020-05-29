package com.project.kingthejoy.letter.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LetterDto {
	private int letter_seq;
	private int member_seq;
	private String letter_sender;
	private String letter_title;
	private String letter_content;
	private Date letter_regdate;
	private int letter_status;
	private int from;
	private int to;
	private String member_id;
	private String member_name;
}
