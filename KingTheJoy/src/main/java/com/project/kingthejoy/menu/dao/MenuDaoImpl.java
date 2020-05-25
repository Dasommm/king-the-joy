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
		System.out.println("menuUpdate DaoImpl:::::");
		int updateRes = sqlSessionTemplate.update(NAMESPACE+"menuUpdate",menuDto);
		System.out.println("updateRes"+updateRes);
		return updateRes;

	}

	@Override
	public int menuDelete(int menu_seq) {
		System.out.println("menuDelete DaoImpl:::::");
		int deleteRes = sqlSessionTemplate.delete(NAMESPACE+"menuDelete",menu_seq);
		System.out.println("deleteRes"+deleteRes);
		return deleteRes;

	}

	@Override
	public MenuDto menuSelectOne(int menu_seq) {
		System.out.println("menuUpdate DaoImpl!!!!");
		MenuDto menuSelectOne = sqlSessionTemplate.selectOne(NAMESPACE+"menuSelectOne",menu_seq);
		System.out.println(menuSelectOne);
		return menuSelectOne;
	}

}
