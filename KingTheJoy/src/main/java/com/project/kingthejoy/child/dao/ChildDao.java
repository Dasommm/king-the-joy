package com.project.kingthejoy.child.dao;

import java.util.List;

import com.project.kingthejoy.child.dto.ChildDto;

public interface ChildDao {
	
	String NAMESPACE = "childDto.";
	public List<ChildDto> selectList();
    public ChildDto selectOne(int childno);
    public int insert(ChildDto dto);
    public int update(ChildDto dto);
    public int delete(int childno);

}
