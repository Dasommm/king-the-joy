
package com.project.kingthejoy.children.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.children.dao.ChildrenDao;
import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.children.dto.ChildrenPagingDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

@Service
public class ChildrenBizImpl implements ChildrenBiz {

	@Autowired
	private ChildrenDao dao;

	@Override
	public List<ChildrenDto> selectList(int school_seq, PillPagingDto pdto) {
		int page = pdto.getPage();
		int rows = pdto.getRows();

		int from = rows * (page - 1) + 1;
		int to = rows * page;

		return dao.selectList(school_seq, from, to);
	}

	@Override
	public int insert(ChildrenDto dto) {

		return 0;
	}

	@Override
	public int totalpage(int rows, HttpSession session) {

		int totalpage = (int) Math.ceil((double) dao.totalpage(session) / rows);

		return totalpage;
	}

}

