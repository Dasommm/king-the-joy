package com.project.kingthejoy.child.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.child.dto.ChildDto;

@Repository
public class ChildDaoImpl implements ChildDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ChildDto> selectList() {

		List<ChildDto> list = new ArrayList<ChildDto>();

		try {
			list = sqlSession.selectList(NAMESPACE + "selectList");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error(selectList):3,4");
		}
		return list;
	}

	@Override
	public ChildDto selectOne(int childno) {

		return null;
	}

	@Override
	public int insert(ChildDto dto) {

		return 0;
	}

	@Override
	public int update(ChildDto dto) {

		return 0;
	}

	@Override
	public int delete(int childno) {

		return 0;
	}

}
