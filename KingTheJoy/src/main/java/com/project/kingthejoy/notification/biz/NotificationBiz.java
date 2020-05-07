package com.project.kingthejoy.notification.biz;

import java.util.List;

import com.project.kingthejoy.notification.dto.NotificationDto;

public interface NotificationBiz {
	public List<NotificationDto> selectNotificationList();
	public NotificationDto selectNotificationOne(int notification_seq);
	public int insertNotification(NotificationDto notificationDto);
	public int updateNotification(NotificationDto notificationDto);
	public int deleteNotification(int notification_seq);
	public int insertNotificationCheck(int notification_seq);
	public int newNotification();
}