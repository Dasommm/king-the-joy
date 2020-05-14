package com.project.kingthejoy.note.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoteDto {
	
	private int note_seq;
	private int member_seq;
	private int children_seq;
	private Date note_date;
	private int note_feeling;
	private int note_health;
	private int note_temp;
	private int note_meal;
	private int note_sleep;
	private int note_def;
	private String note_spec;

	private String class_name;
}
