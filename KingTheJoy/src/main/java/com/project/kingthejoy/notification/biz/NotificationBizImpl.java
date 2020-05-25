package com.project.kingthejoy.notification.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.notification.dao.NotificationDao;
import com.project.kingthejoy.notification.dto.NotificationCheckDto;
import com.project.kingthejoy.notification.dto.NotificationDto;
import com.project.kingthejoy.notification.dto.PagingDto;

@Service
public class NotificationBizImpl implements NotificationBiz {

	@Autowired
	private NotificationDao dao;
	
	@Override
	public List<NotificationDto> selectNotificationList(PagingDto pagingDto) {
		// TODO Auto-generated method stub
		return dao.selectNotificationList(pagingDto);
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
	public int insertNotificationCheck(int notification_seq, int school_seq) {
		// TODO Auto-generated method stub
		return dao.insertNotificationCheck(notification_seq, school_seq);
	}

	@Override
	public int newNotification() {
		// TODO Auto-generated method stub
		return dao.newNotification();
	}

	@Override
	public int selectWritten(int member_seq, int notification_seq) {
		NotificationCheckDto checkDto = new NotificationCheckDto();
		checkDto.setMember_seq(member_seq);
		checkDto.setNotification_seq(notification_seq);
		int res = dao.selectWritten(checkDto);
		if(res==0) {
			return dao.updateWritten(checkDto);
		}else {
			return 1;
		}
	}

	@Override
	public int updateWritten(NotificationCheckDto checkDto) {
		// TODO Auto-generated method stub
		return dao.updateWritten(checkDto);
	}

	@Override
	public String selectSchoolName(int school_seq) {
		// TODO Auto-generated method stub
		return dao.selectSchoolName(school_seq);
	}

	@Override
	public int countList(int school_seq) {
		// TODO Auto-generated method stub
		return dao.countList(school_seq);
	}

	@Override
	public List<NotificationDto> selectRollingNotificationList(int school_seq) {
		// TODO Auto-generated method stub
		return dao.selectRollingNotificationList(school_seq);
	}

}