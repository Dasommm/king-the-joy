package com.project.kingthejoy.pill.dao;

import java.util.List;


import com.project.kingthejoy.pill.dto.PillDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

public interface PillDao {
	
	public int insert(PillDto pillDto);

	public List<PillDto> selectList(int school_seq,int to,int from);
	public List<PillPagingDto> selectPaging(int to ,int from );

	public int totalPage();
	
	public PillDto selectOne(int pill_seq);

	public int delete(int pill_seq);

	public int mulDelete(String[] pill_seq);
	
	
	
	

}
