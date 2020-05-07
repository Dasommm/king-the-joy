package com.project.kingthejoy.notification.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.notification.dao.NotificationDao;
import com.project.kingthejoy.notification.dto.NotificationDto;

@Service
public class NotificationBizImpl implements NotificationBiz {

	@Autowired
	private NotificationDao dao;
	@Override
	public List<NotificationDto> selectNotificationList() {
		// TODO Auto-generated method stub
		return dao.selectNotificationList();
	}

	@Override
	public NotificationDto selectNotificationOne(int notification_seq) {
		// TODO Auto-generated method stub
		return dao.selectNotificationOne(notification_seq);
	}

	@Override
	public int insertNotification(NotificationDto notificationDto) {
		// TODO Auto-generated method stub
		return dao.insertNotification(notificationDto);
	}

	@Override
	public int updateNotification(NotificationDto notificationDto) {
		// TODO Auto-generated method stub
		return dao.updateNotification(notificationDto);
	}

	@Override
	public int deleteNotification(int notification_seq) {
		// TODO Auto-generated method stub
		return dao.deleteNotification(notification_seq);
	}

	@Override
	public int insertNotificationCheck(int notification_seq) {
		// TODO Auto-generated method stub
		return dao.insertNotificationCheck(notification_seq);
	}

	@Override
	public int newNotification() {
		// TODO Auto-generated method stub
		return dao.newNotification();
	}

}