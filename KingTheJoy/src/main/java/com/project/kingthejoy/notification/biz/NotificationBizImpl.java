package com.project.kingthejoy.notification.biz;

import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.note.dto.NoteDto;
import com.project.kingthejoy.notification.dao.NotificationDao;
import com.project.kingthejoy.notification.dto.GetNewNotificationDto;
import com.project.kingthejoy.notification.dto.NotificationCheckDto;
import com.project.kingthejoy.notification.dto.NotificationDto;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class NotificationBizImpl implements NotificationBiz {

	@Autowired
	private NotificationDao dao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public List<NotificationDto> selectNotificationList(int school_seq) {
		// TODO Auto-generated method stub
		return dao.selectNotificationList(school_seq);
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
	public int insertNotificationCheck(int notification_seq, int school_seq, String notification_title) {
		// TODO Auto-generated method stub
		return dao.insertNotificationCheck(notification_seq, school_seq, notification_title);
	}

	@Override
	public GetNewNotificationDto newNotification() {
		// TODO Auto-generated method stub
		return dao.newNotification();
	}

	@Override
	public int selectWritten(int member_seq, int notification_seq) {
		NotificationCheckDto checkDto = new NotificationCheckDto();
		checkDto.setMember_seq(member_seq);
		checkDto.setNotification_seq(notification_seq);
		int res = dao.selectWritten(checkDto);
		if (res == 0) {
			return dao.updateWritten(checkDto);
		} else {
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

	@Override
	public List<NotificationCheckDto> selectNotificationCheckList(int school_seq) {
		// TODO Auto-generated method stub
		return dao.selectNotificationCheckList(school_seq);
	}

	@Override
	public int mailSend(int seq) {
		List<String> list = dao.selectMailAddress(seq);
		NotificationDto nDto = dao.selectNotificationOne(seq);
		int emailRes = 0;
		for(int i = 0; i < list.size(); i++) {
		
		String setFrom ="dasomkwon39@gmail.com";	//보내는 사람 
		String tomail = list.get(i);	//받는 사람
		String title = "[KingTheJoy] 공지사항 확인부탁드립니다.";	//제목
		String content = "공지사항 " + nDto.getNotification_title() + " 이 업로드 되었습니다 확인바랍니다.";//내용	
				
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setFrom);
				messageHelper.setTo(tomail);
				messageHelper.setSubject(title);
				messageHelper.setText(content);
				
				mailSender.send(message);
				emailRes++;
			} catch (MailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return emailRes;
	}

}