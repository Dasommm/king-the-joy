package com.project.kingthejoy.study.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.study.biz.StudyBiz;
import com.project.kingthejoy.study.dto.StudyDto;

@Controller
@RequestMapping(value = "study")
public class StudyController {
	
	@Autowired
	private StudyBiz biz ;
	
	@RequestMapping(value="/studyBookForm.do" )
	public String StudyBookForm(HttpSession session) {
		
		return "teacher/pptUpload";
	};
	
	@RequestMapping(value="/teacherStudyBookList.do")
	public String teacherStudyBookList(StudyDto studyDto, HttpSession session, Model model) {
		
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();
		
		model.addAttribute("studyBookList",biz.selectStudyBooks(school_seq));
		
		return "teacher/studyBookList";
	};
	
	@RequestMapping(value="/studyBookOne.do")
	public String studyBookOne(int book_seq, Model model) {
		
		model.addAttribute("studyBook", biz.selectBookOne(book_seq));
		
		return "parent/studyBookOne";
	}
	
	@RequestMapping(value="/studyBookDelete.do")
	public String studyBookDelete(int book_seq, Model model) {
		
		StudyDto studyDto = biz.selectBookOne(book_seq);
		int folderNumber = studyDto.getBook_folder_number();
		
		String imgLocation = "C:\\Final\\KingTheJoy\\src\\main\\webapp\\resources\\img\\ppt"+folderNumber;
		
		File deleteFolder = new File(imgLocation);
		
		File[] deleteFolderList = deleteFolder.listFiles();
		
		for ( int i = 0 ; i < deleteFolderList.length; i++ ) {
			deleteFolderList[i].delete();
		}
		
		deleteFolder.delete();
		
		int res = biz.studyBookDelete(book_seq);
		if(res>0) { 
			model.addAttribute("msg","수업자료 삭제 완료");
			model.addAttribute("url","/study/teacherStudyBookList.do");
			return "common/alert";
		}else {
			model.addAttribute("msg","수업자료 삭제 실패");
			model.addAttribute("url","/study/teacherStudyBookList.do");
			return "common/alert";
		}
	}
	
	@RequestMapping(value="/pptUpload.do" )
	public String pptUpload(MultipartHttpServletRequest mtf, HttpSession session, String book_name, String book_content, Model model) throws FileNotFoundException, IOException {
		
		// �뙆�씪 �깭洹�
		String fileTag = "file";
	    	// �뾽濡쒕뱶 �뙆�씪�씠 ���옣�맆 寃쎈줈 
		String filePath = "C:\\temp\\";
		// �뙆�씪 �씠由�	
		MultipartFile file = mtf.getFile(fileTag);
		String fileName = file.getOriginalFilename();
		// �뙆�씪 �쟾�넚
		try {
		    file.transferTo(new File(filePath + fileName));
		} catch(Exception e) {
		    System.out.println("파일 업로드 에러");
		}
		
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		if ( files == null || files.length == 0 ) {
			return null;
		}
		
		File lastModifiedFile = files[0];
		for ( int i = 1 ; i < files.length ; i++) {
			if ( lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		
		
		int fileSeq = biz.checkSeq();
		Integer pathPostfix = 0;	
		String imgLocation = "C:\\Final\\KingTheJoy\\src\\main\\webapp\\resources\\img\\";
		File checkDirPath = null;
		File pptFile = lastModifiedFile;
		
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(pptFile));
		
		Dimension pgsize = ppt.getPageSize();
		XSLFSlide[] slide = ppt.getSlides();
		
		//fileSeq = XXXService.getSeq();
		//湲곗��젙蹂� db �삉�뒗 Properties
		
		if ( fileSeq == 1 ) {	
			pathPostfix = 1 ;
			imgLocation = imgLocation + "ppt" + pathPostfix + "\\";
		} else if ( !(fileSeq == 1) ) { 
			imgLocation = imgLocation + "ppt" + fileSeq + "\\";
		}
		
		checkDirPath = new File(imgLocation);
		
		checkDirPath.mkdir();
		
		for( int i = 0 ; i < slide.length ; i++ ) {
			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			graphics.setPaint(Color.white); 
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height ));
			slide[i].draw(graphics);
			
            FileOutputStream out = new FileOutputStream(imgLocation + "ppt_image_" + ( i + 1 )+ ".png");
            javax.imageio.ImageIO.write(img, "png", out);
            out.close(); 
		}
		System.out.println("�씠誘몄� �벑濡� �꽦怨�");
		
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		
		StudyDto studyDto = new StudyDto();
		
		
		studyDto.setMember_seq(memberDto.getMember_seq());
		studyDto.setSchool_seq(memberDto.getSchool_seq());
		studyDto.setBook_img_lastnumber(slide.length);
		studyDto.setBook_content(book_content);
		studyDto.setBook_name(book_name);
		studyDto.setBook_folder_number(fileSeq);
		
		int res = biz.studyBookInsert(studyDto);
		
		if(res>0) { 
			model.addAttribute("msg","수업자료 등록 완료");
			model.addAttribute("url","/study/teacherStudyBookList.do");
			return "common/alert";
		}else { 
			model.addAttribute("msg","수업자료 등록 실패");
			model.addAttribute("url","/study/selectResistForm.do");
			return "commom/alert";
		}
		
	};
	
	@RequestMapping(value="/selectStudyBooks.do", method = RequestMethod.GET)
	public String selectStudyBooks(Model model, HttpSession session) {
		
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();
		System.out.println(school_seq);
		
		model.addAttribute("studyBookList",biz.selectStudyBooks(school_seq));
		
		return "parent/studyBookList";
	}
	
	
}