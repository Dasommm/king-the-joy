package com.project.kingthejoy.notification.dto;

import lombok.Data;

@Data
public class NotificationCheckDto {	
	private int notification_seq;
	private int member_seq;
	private int member_flag;
	private int school_seq;
	private String notification_title;
}
