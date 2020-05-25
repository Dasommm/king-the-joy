package com.project.kingthejoy.study.dto;
import lombok.Data;

@Data
public class StudyDto {
	
	private int book_seq;
	private int member_seq;
	private int school_seq;
	private String book_name;
	private String book_content;
	private int book_img_lastnumber;
	private int book_folder_number;
	
}
