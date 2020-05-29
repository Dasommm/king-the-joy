package com.project.kingthejoy.pill.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.pill.biz.PillBiz;
import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

@Controller
public class PillController {
	@Autowired
	private PillBiz biz;

	private static final Logger logger = LoggerFactory.getLogger(PillDto.class);

	@RequestMapping("/pillrequest.do")
	public String insertForm() {

		return "parent/pillRequest";
	}

	@RequestMapping(value = "/pillInsert.do", method = RequestMethod.POST)
	public String insert(Model model, HttpSession session, String pill_title, String children_name, String pill_symptom,
			String pill_type, String pill_special, String member_name) {

		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		memberDto.getMember_seq();
		memberDto.getSchool_seq();
		PillDto pillDto = new PillDto();
		pillDto.setMember_seq(memberDto.getMember_seq());
		pillDto.setPill_title(pill_title);
		pillDto.setPill_symptom(pill_symptom);
		pillDto.setPill_type(pill_type);
		pillDto.setPill_special(pill_special);
		pillDto.setSchool_seq(memberDto.getSchool_seq());
		pillDto.setMember_name(member_name);
		pillDto.setChildren_name(children_name);

		int res = biz.insert(pillDto);
		if (res != 0) {
			logger.info("pillinsert");
			model.addAttribute("msg", "투약작성이 완료되었습니다.");
			model.addAttribute("url", "translate.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "투약 작성이 실패하였습니다.");
			model.addAttribute("url", "pillreuqest.do");
			return "common/alert";
		}

	}

	@RequestMapping("/pillList.do")
	public String pillList(Model model, HttpSession session, HttpServletRequest request) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");

		logger.info("pillList");
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("controller.page->>" + page);
		PillPagingDto pdto = new PillPagingDto();
		pdto.setPage(page);
		pdto.setRows(10);
		pdto.setPagescale(5);
		pdto.setTotalpage(biz.totalPage(pdto.getRows()));

		// 변경해라
		int school_seq = 1;// memberDto.getSchool_seq()
		model.addAttribute("list", biz.selectList(school_seq, pdto));
		// System.out.println("cotrollermodel->>"+model);
		model.addAttribute("pdto", pdto);

		return "parent/pillList";
	}

	@RequestMapping("/pillDetail.do")
	public String pillDetail(Model model, int pill_seq) {

		logger.info("pillDetail");
		model.addAttribute("dto", biz.selectOne(pill_seq));

		return "parent/pillDetail";
	}

	@RequestMapping("/pillDelete.do")
	public String pillDelete(Model model, int pill_seq) {

		logger.info("pillDelete");
		int res = biz.delete(pill_seq);
		if (res != 0) {
			model.addAttribute("msg", "삭제완료");
			model.addAttribute("url", "pillList.do");
			return "common/alert";
		} else {
			model.addAttribute("msg", "삭제실패");
			model.addAttribute("url", "pillDetail.do");
			return "common/alert";
		}

	}

	@RequestMapping(value = "/pillmuldel.do", method = RequestMethod.POST)
	public String pillMultiDelete(Model model, HttpServletRequest request) {
		// System.out.println("String[] pill_seq->>"+pill_seq);
		logger.info("pillmuldelete");
		String[] pill_seq = request.getParameterValues("chk");
		if (pill_seq == null || pill_seq.length == 0) {
			model.addAttribute("msg", "한개 이상 선택해 주세요");
			model.addAttribute("url", "pillList.do");
			return "common/alert";
		} else {
			int res = biz.mulDelete(pill_seq);
			if (res > 0) {
				model.addAttribute("msg", "삭제를 성공.");
				model.addAttribute("url", "pillList.do?page=1");
				return "common/alert";

			} else {
				model.addAttribute("msg", "삭제실패.");
				model.addAttribute("url", "pillList.do");
				return "common/alert";

			}
		}

	}

}
