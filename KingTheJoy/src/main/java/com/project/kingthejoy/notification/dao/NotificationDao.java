package com.project.kingthejoy.notification.dao;

import java.util.List;

import com.project.kingthejoy.notification.dto.NotificationCheckDto;
import com.project.kingthejoy.notification.dto.NotificationDto;

public interface NotificationDao {
	String NAMESPACE = "notification.";
	public List<NotificationDto> selectNotificationList(int school_seq);
	public NotificationDto selectNotificationOne(int notification_seq);
	public int insertNotification(NotificationDto notificationDto);
	public int updateNotification(NotificationDto notificationDto);
	public int deleteNotification(int notification_seq);
	public int insertNotificationCheck(int notification_seq, int school_seq);
	public int newNotification();
	public int selectWritten(NotificationCheckDto checkDto);
	public int updateWritten(NotificationCheckDto checkDto);

}