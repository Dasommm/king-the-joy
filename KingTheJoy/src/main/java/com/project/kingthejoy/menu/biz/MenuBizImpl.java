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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int menuDelete(MenuDto menuDto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
