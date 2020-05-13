package com.project.kingthejoy.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.school.dto.SchoolDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController<dataList> {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberBiz biz;

	@RequestMapping(value = "/home.do")
	public String homepageForm() {

		logger.info("home.jsp on");

		return "common/home";
	}

	@RequestMapping(value = "/naverLoginResult.do")
	public String naverLoginCallback() {
		return "member/callback";
	}

	@RequestMapping(value = "/naverLoginSuccess.do", method = { RequestMethod.POST, RequestMethod.GET })

	public String naverLoginSuccess(HttpSession session, String naver_name, String naver_email) {

		session.setAttribute("sns_name", naver_name);
		session.setAttribute("sns_email", naver_email);

		return "member/snsMemberUpdate";

	}

	@RequestMapping(value = "/kakaoLogin.do")
	public String kakaoLogin(HttpSession session, String kakao_nickname, String kakao_email) {
		session.setAttribute("sns_name", kakao_nickname);
		session.setAttribute("sns_email", kakao_email);

		return "member/snsMemberUpdate";
	}

	@RequestMapping(value = "/memberUpdateForm.do")
	public String memberUpdateForm() {
		return "member/memberUpdate";
	}

	@RequestMapping(value = "snsLoginUpdatePage.do")
	public String snsLoginUpdatePage() {
		return "member/snsMemberUpdate";
	}

	@ResponseBody
	@RequestMapping(value = "/tableDataSend.do")
	public String insertChildOrSchool(String data, HttpSession session, Model model) {
		String[] dataArray = null;
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");

		dataArray = data.split(",");

		log.info("session value = {}", memberDto.getMember_seq());
		log.info("dataArray Length = {}", dataArray[1]);
		for (int i = 0; i < dataArray.length; i++) {
			SchoolDto schoolDto = new SchoolDto();
			String[] temp = dataArray[i].split("/");
			schoolDto.setSchool_addr(temp[1]);
			schoolDto.setSchool_name(temp[2]);

			if (biz.selectSchool(schoolDto) == null) {
				Map<String, String> childMap = new HashMap<String, String>();
				Map<String, String> schoolListMap = new HashMap<String, String>();
				Map<String, Integer> schoolMap = new HashMap<String, Integer>();

				log.info("children_name = {}", temp[0]);
				childMap.put("children_name", temp[0]);
				childMap.put("member_seq", Integer.toString(memberDto.getMember_seq()));
				biz.insertChildInfo(childMap);

				schoolListMap.put("school_addr", temp[1]);
				schoolListMap.put("school_name", temp[2]);
				biz.insertSchoolInfo(schoolListMap);
				int children_seq = biz.selectChildrenSeqOfSchool(memberDto.getMember_seq());
				int school_seq = biz.selectSchoolSeq(schoolListMap);

				schoolMap.put("children_seq", children_seq);
				schoolMap.put("school_seq", school_seq);
				schoolMap.put("member_seq", memberDto.getMember_seq());

				biz.insertSchool(schoolMap);

			} else {
				Map<String, String> childMap = new HashMap<String, String>();
				Map<String, Integer> schoolMap = new HashMap<String, Integer>();
				Map<String, String> schoolListMap = new HashMap<String, String>();

				childMap.put("children_name", temp[0]);
				childMap.put("member_seq", Integer.toString(memberDto.getMember_seq()));
				biz.insertChildInfo(childMap);

				schoolListMap.put("school_addr", temp[1]);
				schoolListMap.put("school_name", temp[2]);

				int children_seq = biz.selectChildrenSeqOfSchool(memberDto.getMember_seq());
				int school_seq = biz.selectSchoolSeq(schoolListMap);

				schoolMap.put("children_seq", children_seq);
				schoolMap.put("school_seq", school_seq);
				schoolMap.put("member_seq", memberDto.getMember_seq());

				biz.insertSchool(schoolMap);

			}
		}
		model.addAttribute("msg", "자녀정보 입력을 성공하였습니다");
		model.addAttribute("url", "roleCheck.do");
		return "common/alert";
		
	}

	@RequestMapping(value = "/selectResistForm.do")
	public String selectResistForm() {
		logger.info("Resist Form Open");
		return "member/selectResist";
	}

	@RequestMapping(value = "/resistForm.do")
	public String resistForm(int member_role, Model model) {

		logger.info("Resist Form Open : " + member_role + "번");

		model.addAttribute("member_role", member_role);

		return "member/resist";
	}

	@RequestMapping(value = "/memberJoin.do", method = RequestMethod.POST)
	public String memberJoin(MemberDto dto, Model model) {
		logger.info("Member Join");

		int res = biz.memberJoin(dto);

		if (res > 0) {
			logger.info("회원가입 완료");
			model.addAttribute("msg", "킨더조이에 오신 것을 환영합니다.");
			model.addAttribute("url", "home.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			model.addAttribute("url", "selectResistForm.do");
			return "commom/alert";
		}
	}

	@RequestMapping("/loginCheck.do")
	public String loginCheck(MemberDto memberDto, HttpSession session, Model model) {
		boolean result = biz.loginCheck(memberDto, session);
		if (result == true) {
			log.info("session value(member_role) = {}", memberDto.getMember_role());
			model.addAttribute("msg", "킨더조이 로그인 성공");
			model.addAttribute("url", "roleCheck.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "킨더조이 로그인 실패");
			model.addAttribute("url", "home.do");
			return "common/alert";
		}
	}

	@RequestMapping("/roleCheck.do")
	public String roleCheck(HttpSession session, Model model) {
		// 0관리자, 1원장, 2선생님, 3학부모
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		log.info("로그인된 사용자 등급 = {} ", memberDto.getMember_role());
		if (memberDto.getMember_role() == 0) {
			// 관리자 (node로 연결)
			log.info("관리자 페이지 연결");
			return "common/home";
		} else if (memberDto.getMember_role() == 1) {
			// 원장님 페이지 연결
			log.info("원장님 페이지 연결");
			if (biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()));
				session.setAttribute("memberDto", memberDto);
				return "main/masterMain";
			} else {
				model.addAttribute("msg", "소속된 유치원 정보를 입력해주세요");
				model.addAttribute("url", "schoolInfoUpdate.do");
				return "common/alert";
			}
		} else if (memberDto.getMember_role() == 2) {
			// 선생님 페이지 연결
			log.info("선생님 페이지 연결");
			if (biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()));
				session.setAttribute("memberDto", memberDto);
				return "main/teacherMain";
			} else {
				model.addAttribute("msg", "소속된 유치원 정보를 입력해주세요");
				model.addAttribute("url", "schoolInfoUpdate.do");
				return "common/alert";
			}
		} else {
			// 학부모 페이지 연결
			log.info("학부모 페이지 연결");
			if (biz.selectChildrenSeqOfSchool(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfParent(memberDto.getMember_seq()));
				session.setAttribute("memberDto", memberDto);
				return "main/parentMain";
			} else {
				model.addAttribute("msg", "자녀정보를 추가로 입력해주세요");
				model.addAttribute("url", "memberUpdateForm.do");
				return "common/alert";
			}
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session, Model model) {

		biz.logout(session);
		model.addAttribute("msg", "안녕히가세요");
		model.addAttribute("url", "home.do");
		return "common/alert";

	}
	@RequestMapping("/schoolInfoUpdate.do")
	public String fowardToSchoolInfoUpdate(Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto"); 
		model.addAttribute("member_role",memberDto.getMember_role());
		return "member/schoolInfoUpdate";
	}
	
	@RequestMapping(value = "/schoolInfo.do", method = RequestMethod.POST)
	public String schoolInfoInsert(String school_addr, String school_name, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		Map<String, Integer> schoolMap = new HashMap<String, Integer>();
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setSchool_addr(school_addr);
		schoolDto.setSchool_name(school_name);
		Map<String, String> schoolListMap = new HashMap<String, String>();
		schoolListMap.put("school_addr", school_addr);
		schoolListMap.put("school_name", school_name);
		System.out.println("school_addr : "+school_addr);
		if(biz.selectSchool(schoolDto)==null) {
			biz.insertSchoolInfo(schoolListMap);
		}
		int school_seq = biz.selectSchoolSeq(schoolListMap);
		schoolMap.put("school_seq", school_seq);
		schoolMap.put("member_seq", memberDto.getMember_seq());
		biz.insertSchool(schoolMap);
		log.info("session value = {}", memberDto.getMember_seq());
		model.addAttribute("msg", "유치원 정보입력을 성공하였습니다");
		model.addAttribute("url", "roleCheck.do");
		return "common/alert";
	}

}