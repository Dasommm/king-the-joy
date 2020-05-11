package com.project.kingthejoy.menu.dao;

import java.util.List;

import com.project.kingthejoy.menu.dto.MenuDto;

public interface MenuDao {
	
	//전체 리스트
	public List<MenuDto> selectAll(int school_seq);
	
	//insert
	public int menuInsert(MenuDto menuDto);
	
	//update
	public int menuUpdate(MenuDto menuDto);
	
	//delete
	public int menuDelete(MenuDto menuDto);

}
