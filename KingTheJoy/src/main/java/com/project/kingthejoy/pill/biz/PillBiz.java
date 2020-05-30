
package com.project.kingthejoy.pill.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

public interface PillBiz {

	public int insert(PillDto pillDto);

	public List<PillDto> selectList(int school_seq,PillPagingDto pdto);
	
	public int totalPage(int rows,HttpSession session);
	
	public PillDto selectOne(int pill_seq);

	public int delete(int pill_seq);

	public int mulDelete(String[] pill_seq);

	}

