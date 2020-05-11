package com.project.kingthejoy.notification.dto;

import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class NotificationDto {
	private int notification_seq;
	private int member_seq;
	private String notification_writer;
	private String notification_title;
	private String notification_content;
	private Date notification_date;
	private int school_seq;
}