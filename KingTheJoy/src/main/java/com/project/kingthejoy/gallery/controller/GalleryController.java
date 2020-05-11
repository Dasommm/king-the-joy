package com.project.kingthejoy.gallery.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.kingthejoy.gallery.dto.GalleryDto;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Controller
public class GalleryController {
	
	@RequestMapping(value = "/gallery.do", method = RequestMethod.GET)
	public String galleryMain () {
		log.info("into: galleryMain");
		return "gallery/galleryMain";
	}
	
	@RequestMapping(value = "/galleryForm.do", method = RequestMethod.GET)
	public String galleryForm () {
		log.info("into: galleryForm");
		return "gallery/galleryForm";
	}
	
	@RequestMapping(value = "/galleryUpload.do", method = RequestMethod.GET)
	public String galleryUpload (int classseq, Model model, HttpServletRequest request) {
		log.info("into: galleryUpload");
		//dto
		GalleryDto galleryDto = new GalleryDto();
		galleryDto.setClassseq(classseq);
		log.info("original_class: "+classseq);
		//make directory
		String path = request.getSession().getServletContext().getRealPath("/resources/gallery/"+classseq+"/");
		classMkdir(classseq, path);
		//to client
		model.addAttribute("dto", galleryDto);
		return "gallery/galleryUpload";
	}
	
	@RequestMapping(value = "/galleryPage.do", method = RequestMethod.GET)
	public String galleryPage (int classseq, Model model) {
		log.info("into: galleryPage");
		GalleryDto galleryDto = new GalleryDto();
		galleryDto.setClassseq(classseq);
		model.addAttribute("dto", galleryDto);
		return "gallery/galleryPage";
	}
	
	//make class folder
	private void classMkdir(int classseq, String path) {
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
	
	@RequestMapping(value = "/uploadFormAction.do", method = RequestMethod.POST)
	public String uploadFormPost (HttpServletRequest request, Model model, MultipartFile uploadFile) {
		
		System.out.println("uploadFormAction 진입...");
		String uploadFolder = "C:\\upload";
		
		System.out.println("filename: "+uploadFile.getOriginalFilename());
		System.out.println("filename: "+uploadFile.getSize());
		
		File saveFile = new File(uploadFolder, "1.jpg");
		
		try {
			uploadFile.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "gallery/galleryForm";
	}
	
	//ajax
	@RequestMapping(value = "/uploadAjaxAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAjaxPost (HttpServletRequest request, MultipartFile uploadFile) {
		log.info("into: ajax");
		
		int imageNumber = Integer.parseInt(request.getParameter("imageNumber"));
		log.info("imageNumber: "+imageNumber);
		
		//실제경로
		String path = request.getSession().getServletContext().getRealPath("\\resources\\gallery\\1\\");
		log.info("path: "+path);
		
		//파일생성
		String uploadFileName = uploadFile.getOriginalFilename();
		log.info("uploadFileName: "+uploadFileName);
		File saveFile = new File(path, imageNumber+".jpg");		
		try {
			uploadFile.transferTo(saveFile);
		
			//섬네일 파일 생성
			if(checkImageType(saveFile)) {
				FileOutputStream thumbnail = 
						new FileOutputStream(new File(path, "s_"+imageNumber+".jpg"));
				Thumbnailator.createThumbnail(uploadFile.getInputStream(), 
						thumbnail, 100, 100);
				thumbnail.close();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "HttpStatus.OK";
	}
	
	// 이미지인지 파일검사
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(value = "/display.do", method = RequestMethod.GET)
	@ResponseBody 
	public byte[] getFile(String fileName, HttpServletRequest request) {
		
		log.info("/display.do 진입");
		//log.info(fileName);
		String path = request.getSession().getServletContext().getRealPath("/resources/gallery/1/");
		
		File file = new File(path+fileName);
		byte[] result = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-type", Files.probeContentType(file.toPath()));
			//result = new byte[](FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			result = org.springframework.util.FileCopyUtils.copyToByteArray(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
