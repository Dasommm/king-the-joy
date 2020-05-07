package com.project.kingthejoy.notification.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.notification.dto.NotificationDto;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<NotificationDto> selectNotificationList() {
		List<NotificationDto> list = new ArrayList<NotificationDto>();
		try {
			list = sqlSession.selectList(NAMESPACE + "selectList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NotificationDto selectNotificationOne(int notification_seq) {
		NotificationDto notificationDto = null;
		try {
			notificationDto = sqlSession.selectOne(NAMESPACE+"selectOne");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificationDto;
	}

	@Override
	public int insertNotification(NotificationDto notificationDto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE+"insert",notificationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateNotification(NotificationDto notificationDto) {
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE+"update",notificationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int deleteNotification(int notification_seq) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE+"delete",notification_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}