package com.project.kingthejoy.menu.biz;

import java.util.List;

import com.project.kingthejoy.menu.dto.MenuDto;


public interface MenuBiz {
	
	//전체 리스트
	public List<MenuDto> selectAll(int school_seq);
	
	//selectOne
	public MenuDto menuSelectOne(int menu_seq);
	
	//insert
	public int menuInsert(MenuDto menuDto);
	
	//update
	public int menuUpdate(MenuDto menuDto);
	
	//delete
	public int menuDelete(int menu_seq);

}
