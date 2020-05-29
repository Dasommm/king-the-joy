package com.project.kingthejoy.pill.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

public interface PillBiz {

	public int insert(PillDto pillDto);

	public List<PillDto> selectList(int school_seq,PillPagingDto pdto);
	
	public List<PillDto> selectList01(PillPagingDto pdto);
	public List<PillPagingDto> selectPaging(int to ,int from );

	
	
	public int totalPage(int rows);
	
	public PillDto selectOne(int pill_seq);

	public int delete(int pill_seq);

	public int mulDelete(String[] pill_seq);

	

	

}
