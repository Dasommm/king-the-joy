package com.project.kingthejoy.children.dao;

import java.util.List;

import com.project.kingthejoy.children.dto.ChildrenDto;

public interface ChildrenDao {
	
	public List<ChildrenDto> selectList(int school_seq);
	
	public int insert(ChildrenDto dto);
	
	 public int delete(int children_seq);

}
