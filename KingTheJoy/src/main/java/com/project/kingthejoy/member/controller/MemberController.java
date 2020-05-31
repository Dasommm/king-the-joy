package com.project.kingthejoy.member.controller;

import java.security.Principal;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(Model model, String member_oaddr, String member_addr, String member_detailaddr, String member_phone, String member_email, HttpSession session) {
		
		MemberDto memberDto = new MemberDto();
		
		MemberDto memberVO = (MemberDto)session.getAttribute("memberDto");
		int member_seq = memberVO.getMember_seq();
		
		memberDto.setMember_seq(member_seq);
		memberDto.setMember_oaddr(member_oaddr);
		memberDto.setMember_addr(member_addr);
		memberDto.setMember_detailaddr(member_detailaddr);
		memberDto.setMember_email(member_email);
		memberDto.setMember_phone(member_phone);
		
		int res = biz.memberUpdate(memberDto);
		 
		if(res>0) { 
			logger.info("�쉶�썝�닔�젙 �셿猷�");
			model.addAttribute("msg","�쉶�썝 �닔�젙 �셿猷�");
			model.addAttribute("url","/member/myPage.do");
			return "common/alert";
		}else {
			model.addAttribute("msg","�쉶�썝 �닔�젙 �떎�뙣");
			model.addAttribute("url","/member/myPage.do");
			return "commom/alert";
		}
	}
	
	@RequestMapping("/searchId.do")
	public String searchId (@RequestParam Map<String, Object> paramMap, Model model) {
		
		System.out.println(paramMap.get("member_name"));
		
		String id = biz.getId(paramMap);
		
		
		if (id != null) {
			model.addAttribute("msg", "�븘�씠�뵒�뒗 " + id + " �엯�땲�떎.");
			model.addAttribute("url", "/member/home.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "�씠由꾧낵 �씠硫붿씪�쓣�솗�씤�빐二쇱꽭�슂");
			model.addAttribute("url", "/member/home.do");
			return "common/alert";
		}
	} 
	
	@RequestMapping("/searchPw.do")
	public String sendEmailAction (@RequestParam Map<String, Object> paramMap, ModelMap model) {

		String pw = biz.getPw(paramMap);
		
		
		if (pw != null) {
			model.addAttribute("msg", "鍮꾨�踰덊샇�뒗 " + pw + " �엯�땲�떎.");
			model.addAttribute("url", "/member/home.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "�븘�씠�뵒�� �씠硫붿씪�쓣�솗�씤�빐二쇱꽭�슂");
			model.addAttribute("url", "/member/home.do");
			return "common/alert";
		}
		
	}

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
			model.addAttribute("school_name", biz.selectSchoolInfo(memberDto.getSchool_seq()));
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
		log.info("SNS check �꽆�뼱�삩 �뜲�씠�꽣 : {}", biz.snsMemberCheck(snsMap, session));

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
			logger.info("�쉶�썝媛��엯 �셿猷�");
			model.addAttribute("msg", "異붽��젙蹂� �엯�젰 �셿猷� > �옄��瑜� �벑濡앺빀�땲�떎.");
			model.addAttribute("url", "memberUpdateForm.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "�쉶�썝媛��엯�씠 �떎�뙣�븯���뒿�땲�떎. �떎�떆 �떆�룄�빐二쇱꽭�슂.");
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
		log.info("SNS check �꽆�뼱�삩 �뜲�씠�꽣 : {}", biz.snsMemberCheck(snsMap, session));

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

	@RequestMapping(value = "/memberParentUpdate.do")
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

		logger.info("Resist Form Open : " + member_role + "踰�");

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
			logger.info("�쉶�썝媛��엯 �셿猷�");
			model.addAttribute("msg", "�궓�뜑議곗씠�뿉 �삤�떊 寃껋쓣 �솚�쁺�빀�땲�떎.");
			model.addAttribute("url", "home.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "�쉶�썝媛��엯�씠 �떎�뙣�븯���뒿�땲�떎. �떎�떆 �떆�룄�빐二쇱꽭�슂.");
			model.addAttribute("url", "selectResistForm.do");
			return "commom/alert";
		}
	}

	@RequestMapping("/loginCheck.do")
	public String loginCheck(MemberDto memberDto, HttpSession session, Model model, String member_id,
			String member_pw, Principal principal) {
		
		System.out.println(principal.getName());
		
		memberDto.setMember_id(principal.getName());
		
		boolean result = biz.loginCheck(memberDto, session);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		
		if (result) {
			log.info("session value(member_role) = {}", memberDto.getMember_role());
			model.addAttribute("msg", "�궓�뜑議곗씠 濡쒓렇�씤 �꽦怨�");
			model.addAttribute("url", "roleCheck.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "�궓�뜑議곗씠 濡쒓렇�씤 �떎�뙣"); 
			model.addAttribute("url", "home.do");
			return "common/alert";
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session, Model model) {

		biz.logout(session);
		model.addAttribute("msg", "�븞�뀞�엳媛��꽭�슂");
		model.addAttribute("url", "home.do");
		return "common/alert";

	}

	@RequestMapping("/roleCheck.do")
	public String roleCheck(HttpSession session, Model model) {
		// 0愿�由ъ옄, 1�썝�옣, 2�꽑�깮�떂, 3�븰遺�紐�
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		log.info("濡쒓렇�씤�맂 �궗�슜�옄 �벑湲� = {} ", memberDto.getMember_role());
		log.info("濡쒓렇�씤�맂 �궗�슜�옄 踰덊샇 = {} ", memberDto.getMember_seq());

		if (memberDto.getMember_role() == 0) {
			// 愿�由ъ옄 (node濡� �뿰寃�)
			log.info("愿�由ъ옄 �럹�씠吏� �뿰寃�");
			return "common/home";
		} else if (memberDto.getMember_role() == 1) {
			// �썝�옣�떂 �럹�씠吏� �뿰寃�
			log.info("�썝�옣�떂 �럹�씠吏� �뿰寃�");
			if (biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()));

				session.setAttribute("memberDto", memberDto);
				return "main/principalMain";
			} else {
				model.addAttribute("msg", "�냼�냽�맂 �쑀移섏썝 �젙蹂대�� �엯�젰�빐二쇱꽭�슂");
				model.addAttribute("url", "schoolInfoUpdate.do");
				return "common/alert";
			}
		} else if (memberDto.getMember_role() == 2) {
			// �꽑�깮�떂 �럹�씠吏� �뿰寃�
			log.info("�꽑�깮�떂 �럹�씠吏� �뿰寃�");
			if (biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfMasterAndTeacher(memberDto.getMember_seq()));

				session.setAttribute("memberDto", memberDto);
				return "main/teacherMain";
			} else {
				model.addAttribute("msg", "�냼�냽�맂 �쑀移섏썝 �젙蹂대�� �엯�젰�빐二쇱꽭�슂");
				model.addAttribute("url", "schoolInfoUpdate.do");
				return "common/alert";
			}
		} else {
			// �븰遺�紐� �럹�씠吏� �뿰寃�
			log.info("�븰遺�紐� �럹�씠吏� �뿰寃�");

			if (biz.selectChildrenSeqOfSchool(memberDto.getMember_seq()) > 0) {
				memberDto.setSchool_seq(biz.selectSchoolSeqOfParent(memberDto.getMember_seq()));
				session.setAttribute("memberDto", memberDto);
				return "main/parentMain";
			} else {
				model.addAttribute("msg", "�옄���젙蹂대�� 異붽�濡� �엯�젰�빐二쇱꽭�슂");
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
		model.addAttribute("msg", "�쑀移섏썝 �젙蹂댁엯�젰�쓣 �꽦怨듯븯���뒿�땲�떎");
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
	@RequestMapping(value = "noRole.do")
	public String noRole(Model model, HttpServletRequest request) {
			
			String referer = request.getHeader("Referer");
			model.addAttribute("msg", "沅뚰븳�씠 �뾾�뒿�땲�떎.");
			model.addAttribute("url", "redirect:" + referer );
			return "common/alert";
		
	}

}