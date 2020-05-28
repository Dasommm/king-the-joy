package com.project.kingthejoy.note.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoteDto {
	
	private int note_seq;
	private int member_seq;
	private int children_seq;
	private String note_receiver;	//받는 사람(부모님 전화번호)
	private String note_sender;		//보내는 사람(선생님 전화번호)
	private Date note_date;
	private String note_feeling;
	private String note_health;
	private int note_temp;
	private String note_meal;
	private int note_sleep;
	private String note_def;
	private String note_spec;

	private String class_name;
}
