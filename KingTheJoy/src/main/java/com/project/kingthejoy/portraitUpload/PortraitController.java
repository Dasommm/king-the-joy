package com.project.kingthejoy.portraitUpload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.project.kingthejoy.principal.dto.PrincipalDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("portrait")
public class PortraitController {
	
	@Autowired
	private PortraitDao dao;

	@RequestMapping(value = "/portraitUpload.do", method = RequestMethod.GET)
	public String portraitUpload(int member_seq, int school_seq, Model model) {
		log.info("portraitUpload");		
		model.addAttribute("member_seq", member_seq);
		model.addAttribute("school_seq", school_seq);
		return "portrait/portraitUpload";
	}
	
	@RequestMapping(value = "/portraitAction.do", method = RequestMethod.POST)
	public void portraitAction(HttpServletRequest request, MultipartFile uploadFile) {
		log.info("-------------------------------");
		log.info("portraitAction");		
		int school_seq = Integer.parseInt(request.getParameter("school_seq"));
		int member_seq = Integer.parseInt(request.getParameter("member_seq"));
		log.info("member_seq: "+member_seq);		
		log.info("Upload File Name: " + uploadFile.getOriginalFilename());
		log.info("Upload File Size: "+uploadFile.getSize());
		String uploadFileName = uploadFile.getOriginalFilename();
		
		//실제경로
		String path = request.getSession().getServletContext().getRealPath("/resources/portrait/"+school_seq+"/");
		log.info("path: "+path);
		
		//폴더생성
		classMkdir(path);
		
		//이미 경로에 파일이 존재한다면 해당 파일을 삭제한다.
		String imgPath = dao.imgPath(member_seq);
		if(imgPath!=null) {
			int idx = imgPath.indexOf("/", 1);
			String imgPathRes = imgPath.substring(idx);
			System.out.println(imgPathRes);
			String fullImgPath = request.getSession().getServletContext().getRealPath(imgPathRes);
			System.out.println("fullImgPath: "+fullImgPath);
			File delPath = new File(fullImgPath);
			if (delPath.exists()) {
				delPath.delete();
				log.info("파일삭제 성공");
			}
		}

		//이름변경 구분자 추가
		UUID uuid = UUID.randomUUID();
		String s = Long.toString(uuid.getMostSignificantBits(), 36);
		String ss = s.substring(2);
		/* Random rand = new Random();
		String charString = new String();
		char[] chars = new char[8];
		for(int i=0;i<chars.length;i++) {
			chars[i] = (char)((int)(Math.random()*122)+49); //49~122
			charString += chars[i];
		} */
		String fullName = ss + "_" + uploadFileName;
		System.out.println(fullName);
		
		//DB경로추가
		PrincipalDto principalDto = new PrincipalDto();
		principalDto.setMember_seq(member_seq);
		principalDto.setSchool_seq(school_seq);
		principalDto.setMember_image("/kingthejoy/resources/portrait/"+school_seq+"/"+fullName);
		System.out.println(principalDto.getMember_image());
		int res = dao.imagePathUpdate(principalDto);
		if(res>0) {
			log.info("DB 이미지 경로 추가 완료!");
		}
		
		//파일생성
		log.info("uploadFileName: "+uploadFileName);
		File saveFile = new File(path, fullName);		
		try {
			uploadFile.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//make class folder
	private void classMkdir(String path) {
		log.info("into: classMkdir");
		File initFolder = new File(path);
		if(!initFolder.exists()) {
			try {
				log.info("make the gallery folder!");
				initFolder.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
				log.warn("----->fail the making directory ");
			}
		} else {
			log.info("gallery folder on");
		}
		log.info(initFolder.toString());
	}
	
	@RequestMapping(value = "/portraitChildUpload.do", method = RequestMethod.GET)
	public String portraitChildUpload(int children_seq, int school_seq, Model model) {
		log.info("portraitUpload");		
		model.addAttribute("children_seq", children_seq);
		model.addAttribute("school_seq", school_seq);
		return "portrait/portraitChildUpload";
	}
	
	@RequestMapping(value = "/portraitChildAction.do", method = RequestMethod.POST)
	public void portraitChildAction(HttpServletRequest request, MultipartFile uploadFile) {
		log.info("-------------------------------");
		log.info("portraitAction");		
		int school_seq = Integer.parseInt(request.getParameter("school_seq"));
		int children_seq = Integer.parseInt(request.getParameter("children_seq"));
		log.info("children_seq: "+children_seq);		
		log.info("Upload File Name: " + uploadFile.getOriginalFilename());
		log.info("Upload File Size: "+uploadFile.getSize());
		String uploadFileName = uploadFile.getOriginalFilename();
		
		//실제경로
		String path = request.getSession().getServletContext().getRealPath("/resources/portrait/"+school_seq+"/");
		log.info("path: "+path);
		
		//폴더생성
		classMkdir(path);
		
		//이미 경로에 파일이 존재한다면 해당 파일을 삭제한다.
		String imgPath = dao.imgChildPath(children_seq);
		if(imgPath!=null) {
			int idx = imgPath.indexOf("/", 1);
			String imgPathRes = imgPath.substring(idx);
			System.out.println(imgPathRes);
			String fullImgPath = request.getSession().getServletContext().getRealPath(imgPathRes);
			System.out.println("fullImgPath: "+fullImgPath);
			File delPath = new File(fullImgPath);
			if (delPath.exists()) {
				delPath.delete();
				log.info("파일삭제 성공");
			}
		}

		//이름변경 구분자 추가
		UUID uuid = UUID.randomUUID();
		String s = Long.toString(uuid.getMostSignificantBits(), 36);
		String ss = s.substring(2);
		String fullName = ss + "_" + uploadFileName;
		System.out.println(fullName);
		
		//DB경로추가
		PrincipalDto principalDto = new PrincipalDto();
		principalDto.setChildren_seq(children_seq);
		principalDto.setSchool_seq(school_seq);
		principalDto.setChildren_image("/kingthejoy/resources/portrait/"+school_seq+"/"+fullName);
		System.out.println(principalDto.getChildren_image());
		int res = dao.imageChildPathUpdate(principalDto);
		if(res>0) {
			log.info("DB 이미지 경로 추가 완료!");
		}
		
		//파일생성
		log.info("uploadFileName: "+uploadFileName);
		File saveFile = new File(path, fullName);		
		try {
			uploadFile.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
