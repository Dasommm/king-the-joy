
package com.project.kingthejoy.pill.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

public interface PillDao {
	
	public int insert(PillDto pillDto);

	public List<PillDto> selectList(int school_seq,int from,int to);
	public List<PillPagingDto> selectPaging(int to ,int from );

	public int totalPage(HttpSession session);
	
	public PillDto selectOne(int pill_seq);

	public int delete(int pill_seq);

	public int mulDelete(String[] pill_seq);
	
	
	
	

}

