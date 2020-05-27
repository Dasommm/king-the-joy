package com.project.kingthejoy.pill.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

		/*
		 * int page = Integer.parseInt(request.getParameter("page"));
		 * System.out.println("bizimplpage->>"+page); pdto.setPage(page);
		 * pdto.setRows(10); pdto.setPagescale(5);
		 * pdto.setTotalpage(this.totalPage(pdto.getRows()));
		 */
		int page = pdto.getPage();
		int rows = pdto.getRows();

		int to = rows * (page - 1) + 1;
		int from = rows * page;

		return dao.selectList(school_seq, to, from);
	}

	/*
	 * @Override public List<PillDto> selectList01(PillPagingDto pdto) {
	 * 
	 * int page = pdto.getPage(); int rows = pdto.getRows();
	 * 
	 * int to = rows*(page-1)+1; int from = rows*page; return dao.selectPaging(to,
	 * from); }
	 */

	/*
	 * @Override public List<PillPagingDto> selectPaging(int to, int from) {
	 * 
	 * 
	 * return dao.selectPaging(to, from); }
	 */
	@Override
	public int totalPage(int rows) {
		int totalpage = (int) Math.ceil((double) dao.totalPage() / rows);

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

	@Override
	public List<PillDto> selectList01(PillPagingDto pdto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PillPagingDto> selectPaging(int to, int from) {
		// TODO Auto-generated method stub
		return null;
	}

}
