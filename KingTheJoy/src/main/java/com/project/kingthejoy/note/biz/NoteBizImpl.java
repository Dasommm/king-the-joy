package com.project.kingthejoy.note.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.note.dao.NoteDao;
import com.project.kingthejoy.note.dto.NoteDto;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class NoteBizImpl implements NoteBiz {
	
	@Autowired
	NoteDao noteDao;
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	public List<String> selectClass(int school_seq) {
		System.out.println("noteBIzImpl!!!!!!!!!!");
		return noteDao.selectClass(school_seq);
	}

	@Override
	public Set<String> selectChildren(String children_class) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int noteInsertDb(NoteDto noteDto) {
		return noteDao.noteInsertDb(noteDto);
		
	}

	@Override
	public NoteDto selectNoteInsert(int note_seq) {
		return noteDao.selectNoteInsert(note_seq);

	}

	@Override
	public int sendText(NoteDto noteDto) {
		System.out.println("문자보내기 biz입성 !!!!!!");
		int res = 0;
		String api_key = "NCSVCJVNJVT88XHA";
	    String api_secret = "GDDB4HEPH53AVA3MEDYWJCUJI9YWAQ80";
	    Message coolsms = new Message(api_key, api_secret);
	    
	    //특이사항 조건주기
	    String note_spec = "";	    
	    if(noteDto.getNote_spec() == null) {
	    	note_spec = "특이사항이 없습니다.";
	    }else {
	    	note_spec = noteDto.getNote_spec();
	    }

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", noteDto.getNote_receiver());	//수신자 : 학부모 번호 
	    params.put("from", "01030046470");	//발신자 : 선생님 번호 -- api에서 등록한 번호로 고정 
	    params.put("type", "LMS");
	    params.put("text", "<알림장>\r\n" + 
	    		noteDto.getChildren_seq()+"어린이 알림장입니다\r\n" + 
	    		"기분 : "+ noteDto.getNote_feeling() +"\r\n" + 
	    		"건강 : "+ noteDto.getNote_health() +"\r\n" + 
	    		"체온 : "+ noteDto.getNote_temp() +"도\r\n" + 
	    		"식사 : "+ noteDto.getNote_meal() +"\r\n" + 
	    		"수면 : "+ noteDto.getNote_sleep() +"시간\r\n" + 
	    		"배변 : "+ noteDto.getNote_def() +"\r\n" + 
	    		"특이사항 : "+note_spec);
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	      res++;
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
		return res;
	    
	  }

	@Override
	public List<String> selectEmail(String mail_class) {

		return noteDao.selectEmail(mail_class);

	}

	@Override
	public int sendEmail(List<String> selectEmail, String mail_title, String mail_body) {
		System.out.println("이메일 보내기 biz~~");
		System.out.println("이메일 주소 숫자~~~~~"+selectEmail.size());

		int emailRes = 0;
		System.out.println("리스트에서 값 꺼내기::::::::::::"+selectEmail.get(0));
		for(int i = 0; i < selectEmail.size(); i++) {
		
		String setFrom ="dasomkwon39@gmail.com";	//보내는 사람 
		String tomail = selectEmail.get(i);	//받는 사람
		String title = mail_title;	//제목
		String content = mail_body;	//내용	
				
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
		
		System.out.println(emailRes);
		
		return emailRes;
	}

	@Override
	public List<NoteDto> selectNote(String member_phone) {
		return noteDao.selectNote(member_phone);

	}



}
