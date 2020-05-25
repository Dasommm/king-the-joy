package com.project.kingthejoy.schedule.dto;

import lombok.Data;

@Data
public class ScheduleDto {
	
	private int schedule_seq;
	private int member_seq;
	private int school_seq;
	private String color;
	private String title;
	private String start;
	private String end;
	private String description;
	

}
