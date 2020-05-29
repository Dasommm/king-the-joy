package com.project.kingthejoy.menu.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.menu.dao.MenuDaoImpl;
import com.project.kingthejoy.menu.dto.MenuDto;

@Service
public class MenuBizImpl implements MenuBiz {

	@Autowired
	MenuDaoImpl menuDao;
	
	@Override
	public List<MenuDto> selectAll(int school_seq) {
		System.out.println("menubizImpl!!!!");
		return menuDao.selectAll(school_seq);

	}

	@Override
	public int menuInsert(MenuDto menuDto) {
		System.out.println("menubizImpl!!!!");
		
		return menuDao.menuInsert(menuDto);

	}

	@Override
	public int menuUpdate(MenuDto menuDto) {
		System.out.println("menuUpdate bizImpl:::::");
		return menuDao.menuUpdate(menuDto);

	}

	@Override
	public int menuDelete(int menu_seq) {
		System.out.println("menuDelete bizImpl :::::");
		return menuDao.menuDelete(menu_seq);

	}

	@Override
	public MenuDto menuSelectOne(int menu_seq) {
		System.out.println("menuSelectOne BizImpl!!!");
		
		MenuDto editDate = menuDao.menuSelectOne(menu_seq);
		String str = editDate.getStart();
		String result = str.substring(0, 10);
		System.out.println("substring:::::::::"+result);
		editDate.setStart(result);
		System.out.println("날짜 수정 ::::::::"+editDate.toString());
		
		return editDate;

	}

}