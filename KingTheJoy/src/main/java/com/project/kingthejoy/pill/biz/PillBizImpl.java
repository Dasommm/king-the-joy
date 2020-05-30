
package com.project.kingthejoy.pill.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.member.dto.MemberDto;
import com.project.kingthejoy.pill.dao.PillDao;
import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

@Service
public class PillBizImpl implements PillBiz {

	@Autowired
	private PillDao dao;

	@Override
	public int insert(PillDto pillDto) {

		return dao.insert(pillDto);
	}

	@Override
	public List<PillDto> selectList(int school_seq, PillPagingDto pdto) {

		int page = pdto.getPage();
		int rows = pdto.getRows();

		int from = rows * (page - 1) + 1;
		int to = rows * page;

		return dao.selectList(school_seq, from, to);
	}

	
	@Override
	public int totalPage(int rows,HttpSession session) {
		int totalpage = (int) Math.ceil((double) dao.totalPage(session) / rows);

		return totalpage;

	}

	@Override
	public PillDto selectOne(int pill_seq) {

		return dao.selectOne(pill_seq);
	}

	@Override
	public int delete(int pill_seq) {

		return dao.delete(pill_seq);
	}

	@Override
	public int mulDelete(String[] pill_seq) {

		return dao.mulDelete(pill_seq);
	}

	

}

