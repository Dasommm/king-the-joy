package com.project.kingthejoy.notification.biz;

import java.util.List;

import com.project.kingthejoy.notification.dto.GetNewNotificationDto;
import com.project.kingthejoy.notification.dto.NotificationCheckDto;
import com.project.kingthejoy.notification.dto.NotificationDto;


public interface NotificationBiz {
	public List<NotificationDto> selectNotificationList(int school_seq);
	public NotificationDto selectNotificationOne(int notification_seq);
	public int insertNotification(NotificationDto notificationDto);
	public int updateNotification(NotificationDto notificationDto);
	public int deleteNotification(int notification_seq);
	public int insertNotificationCheck(int notification_seq, int school_seq, String notification_title);
	public GetNewNotificationDto newNotification();
	public int selectWritten(int member_seq, int notification_seq);
	public int updateWritten(NotificationCheckDto checkDto);
	public String selectSchoolName(int school_seq);
	public int countList(int school_seq);
	public List<NotificationDto> selectRollingNotificationList(int school_seq);
	public List<NotificationCheckDto> selectNotificationCheckList(int school_seq);
	public int mailSend(int seq);

}