package com.project.kingthejoy.notification.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.notification.dto.NotificationCheckDto;
import com.project.kingthejoy.notification.dto.NotificationDto;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<NotificationDto> selectNotificationList(int school_seq) {
		List<NotificationDto> list = new ArrayList<NotificationDto>();
		try {
			list = sqlSession.selectList(NAMESPACE + "selectList",school_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NotificationDto selectNotificationOne(int notification_seq) {
		NotificationDto notificationDto = null;
		try {
			notificationDto = sqlSession.selectOne(NAMESPACE + "selectOne", notification_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notificationDto;
	}

	@Override
	public int insertNotification(NotificationDto notificationDto) {
		int res = 0;
		try {
			res = sqlSession.insert(NAMESPACE + "insert", notificationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateNotification(NotificationDto notificationDto) {
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE + "update", notificationDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public int deleteNotification(int notification_seq) {
		int res = 0;
		try {
			res = sqlSession.delete(NAMESPACE + "delete", notification_seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertNotificationCheck(int notification_seq, int school_seq) {
		List<Integer> list = new ArrayList<Integer>();
		int res = 0;
		try {
			list = sqlSession.selectList(NAMESPACE + "selectMember");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<NotificationCheckDto> checkList = new ArrayList<NotificationCheckDto>();
		for (int i = 0; i < list.size(); i++) {
			NotificationCheckDto dto = new NotificationCheckDto();
			dto.setNotification_seq(notification_seq);
			dto.setMember_flag(0);
			dto.setMember_seq(list.get(i));
			dto.setSchool_seq(school_seq);
			checkList.add(dto);
		}
		try {
			res = sqlSession.insert(NAMESPACE + "insertCheck", checkList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int newNotification() {
		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE + "selectLastNotification");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int selectWritten(NotificationCheckDto checkDto) {

		int res = 0;
		try {
			res = sqlSession.selectOne(NAMESPACE + "selectWritten", checkDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateWritten(NotificationCheckDto checkDto) {
		int res = 0;
		try {
			res = sqlSession.update(NAMESPACE + "updateWritten", checkDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}