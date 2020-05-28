package com.project.kingthejoy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.kingthejoy.common.security.service.impl.AuthenticationUserDetailsServiceImpl;
import com.project.kingthejoy.member.biz.MemberBiz;
import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.notification.biz.NotificationBiz;
import com.project.kingthejoy.school.dto.SchoolDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController<dataList> {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberBiz biz;
	@Autowired
	AuthenticationUserDetailsServiceImpl sc;
	@Autowired
	NotificationBiz notificationBiz;

	@RequestMapping(value = "/home.do")
	public String homepageForm() {

		logger.info("home.jsp on");

		return "common/home";
	}

	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String mainPageForm(Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int member_seq = memberDto.getMember_seq();
		int member_role = memberDto.getMember_role();

		if (member_role == 3) {
			model.addAttribute("childrenList", biz.childrenList(member_seq));
		} else if (member_role == 2 || member_role == 1) {
			if (member_role == 1) {
				model.addAttribute("notificationList",
						notificationBiz.selectNotificationList(memberDto.getSchool_seq()));
			}
		}
		return "member/myPage";
	}

	@RequestMapping(value = "/naverLoginResult.do")
	public String naverLoginCallback() {
		return "member/callback";
	}

	@ResponseBody
	@RequestMapping(value = "/naverLoginSuccess.do", method = { RequestMethod.POST, RequestMethod.GET })

	public String naverLoginSuccess(HttpSession session, String naver_name, String naver_email, MemberDto memberDto) {

		session.setAttribute("member_name", naver_name);
		session.setAttribute("member_id", naver_email);

		Map<String, String> snsMap = new HashMap<String, String>();
		snsMap.put("member_name", naver_name);
		snsMap.put("member_id", naver_email);
		System.out.println(naver_name);
		System.out.println(naver_email);
		log.info("SNS check 넘어온 데이터 : {}", biz.snsMemberCheck(snsMap, session));

		boolean result = biz.snsMemberCheck(snsMap, session);

		if (result == true) {
			return "main";
		} else {
			return "selectResistForm";
		}
	}

	@RequestMapping(value = "/snsMemberUpdate.do", method = RequestMethod.POST)
	public String snsMemberUpdate(MemberDto memberDto, Model model) {

		logger.info("SNS Member Join");

		int res = biz.insertSnsInfo(memberDto);

		if (res > 0) {
			logger.info("회원가입 완료");
			model.addAttribute("msg", "추가정보 입력 완료 > 자녀를 등록합니다.");
			model.addAttribute("url", "memberUpdateForm.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			model.addAttribute("url", "selectResistForm.do");
			return "commom/alert";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/kakaoLogin.do")
	public String kakaoLogin(HttpSession session, String kakao_nickname, String kakao_email, Model model,
			MemberDto memberDto) {

		session.setAttribute("member_name", kakao_nickname);
		session.setAttribute("member_id", kakao_email);
		System.out.println(kakao_nickname);
		System.out.println(kakao_email);
		Map<String, String> snsMap = new HashMap<String, String>();
		snsMap.put("member_name", kakao_nickname);
		snsMap.put("member_id", kakao_email);
		log.info("SNS check 넘어온 데이터 : {}", biz.snsMemberCheck(snsMap, session));

		boolean result = biz.snsMemberCheck(snsMap, session);

		if (result == true) {
			return "roleCheck";
		} else {
			return "selectResistForm";
		}
	}

	@RequestMapping(value = "/memberUpdateForm.do")
	public String memberUpdateForm() {
		return "member/memberUpdate";
	}

	@RequestMapping(value = "snsLoginUpdatePage.do")
	public String snsLoginUpdatePage() {
		return "member/snsMemberUpdate";
	}

	@RequestMapping(value = "/memberUpdate.do")
	public String insertChildOrSchool(String data, HttpSession session, HttpServletResponse res,
			HttpServletRequest request) {

		String[] children_name = request.getParameterValues("children_name");
		String[] school_addr = request.getParameterValues("school_addr");
		String[] school_name = request.getParameterValues("school_name");

		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");

		log.info("session value = {}", memberDto.getMember_seq());
		for (int i = 0; i < children_name.length; i++) {
			SchoolDto schoolDto = new SchoolDto();
			schoolDto.setSchool_addr(school_addr[i]);
			schoolDto.setSchool_name(school_name[i]);

			if (biz.selectSchool(schoolDto) == null) {
				Map<String, String> childMap = new HashMap<String, String>();
				Map<String, String> schoolListMap = new HashMap<String, String>();
				Map<String, Integer> schoolMap = new HashMap<String, Integer>();

				childMap.put("children_name", children_name[i]);
				childMap.put("member_seq", Integer.toString(memberDto.getMember_seq()));
				biz.insertChildInfo(childMap);

				schoolListMap.put("school_addr", school_addr[i]);
				schoolListMap.put("school_name", school_name[i]);
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

				childMap.put("children_name", children_name[i]);
				childMap.put("member_seq", Integer.toString(memberDto.getMember_seq()));
				biz.insertChildInfo(childMap);

				schoolListMap.put("school_addr", school_addr[i]);
				schoolListMap.put("school_name", school_name[i]);

				int children_seq = biz.selectChildrenSeqOfSchool(memberDto.getMember_seq());
				int school_seq = biz.selectSchoolSeq(schoolListMap);

				schoolMap.put("children_seq", children_seq);
				schoolMap.put("school_seq", school_seq);
				schoolMap.put("member_seq", memberDto.getMember_seq());

				biz.insertSchool(schoolMap);
			}
		}
		return "main/parentMain";

	}

	@RequestMapping(value = "/selectResistForm.do")
	public String selectResistForm() {

		logger.info("Resist Form Open");

		return "member/selectResist";
	}

	@RequestMapping(value = "/resistForm.do")
	public String resistForm(int member_role, Model model, HttpSession session) {

		logger.info("Resist Form Open : " + member_role + "번");

		model.addAttribute("member_role", member_role);

		if (session.getAttribute("member_id") == null) {
			return "member/resist";
		} else {
			return "member/snsMemberUpdate";
		}
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
	public String loginCheck(MemberDto memberDto, HttpSession session, Model model, String member_id,
			String member_pw) {

		boolean result = biz.loginCheck(memberDto, session);
		User user = sc.loadUserByUsername(member_id, member_pw);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (result) {
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

	@RequestMapping("/logout.do")
	public String logout(HttpSession session, Model model) {

		biz.logout(session);
		model.addAttribute("msg", "안녕히가세요");
		model.addAttribute("url", "home.do");
		return "common/alert";

	}

	@RequestMapping("/roleCheck.do")
	public String roleCheck(HttpSession session, Model model) {
		// 0관리자, 1원장, 2선생님, 3학부모
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		log.info("로그인된 사용자 등급 = {} ", memberDto.getMember_role());
		log.info("로그인된 사용자 번호 = {} ", memberDto.getMember_seq());

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
				return "main/principalMain";
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

	@RequestMapping("/schoolInfoUpdate.do")
	public String fowardToSchoolInfoUpdate(Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		model.addAttribute("member_role", memberDto.getMember_role());
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
		System.out.println("school_name : " + school_name);
		if (biz.selectSchool(schoolDto) == null) {
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

	@RequestMapping(value = "childrenMap.do")
	public String childrenMap(Model model, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		model.addAttribute("school_address", biz.selectSchoolAddress(memberDto.getSchool_seq()));
		model.addAttribute("memberAddressList", biz.selectMemberAddress(memberDto.getSchool_seq()));
		model.addAttribute("listSize", biz.selectMemberAddress(memberDto.getSchool_seq()).size());
		return "principal/teacherPrincipalChildrenMap";
	}

}