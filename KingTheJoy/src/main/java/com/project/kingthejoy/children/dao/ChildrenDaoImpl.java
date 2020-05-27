package com.project.kingthejoy.children.dao;



import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.children.dto.ChildrenDto;


@Repository
public class ChildrenDaoImpl implements ChildrenDao {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<ChildrenDto> selectList(int school_seq) {

		List<ChildrenDto> list = null;
		
		try {
			list = sqlsession.selectList("children.childrenselectlist",school_seq);
		} catch (Exception e) {
			System.out.println("error:selectlist");
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public int insert(ChildrenDto dto) {
		
		int res = 0;
		try {
			res = sqlsession.insert("child.insert", dto);
		} catch (Exception e) {
			System.out.println("error:childinsert");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int delete(int children_seq) {
		
		int res = 0;
		res = sqlsession.delete("child.childrendelete");
		return res;
	}

	

}
