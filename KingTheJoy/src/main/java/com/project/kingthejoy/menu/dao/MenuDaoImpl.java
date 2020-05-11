package com.project.kingthejoy.menu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.menu.dto.MenuDto;

@Repository
public class MenuDaoImpl implements MenuDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private String NAMESPACE = "menu.";

	@Override
	public List<MenuDto> selectAll(int school_seq) {
		System.out.println("menuDaoimpl!!!");
		List<MenuDto> menulist  = sqlSessionTemplate.selectList(NAMESPACE+"menuList",school_seq);
		System.out.println("dao menuList::::::::"+menulist);
		
		return menulist;

	}

	@Override
	public int menuInsert(MenuDto menuDto) {
		System.out.println("menuDaoImpl!!!!");
		int insertRes = sqlSessionTemplate.insert(NAMESPACE+"menuInsert", menuDto);
		System.out.println("dao insertRes" +insertRes);
		
		return insertRes;
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
