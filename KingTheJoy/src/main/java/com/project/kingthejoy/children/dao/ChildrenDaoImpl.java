
package com.project.kingthejoy.children.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.member.dto.MemberDto;

@Repository
public class ChildrenDaoImpl implements ChildrenDao {

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public List<ChildrenDto> selectList(int school_seq, int from, int to) {

		List<ChildrenDto> list = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("school_seq", school_seq);
		map.put("from", from);
		map.put("to", to);

		try {
			list = sqlsession.selectList("children.childrenselectlist", map);
			
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
	public int totalpage(HttpSession session) {
		
		int res = 0;
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		int school_seq = memberDto.getSchool_seq();

		try {
			res = sqlsession.selectOne("children.totalpage", school_seq);

		} catch (Exception e) {
			System.out.println("error:child.totalpage");
			e.printStackTrace();
		}
		return res;
	}

}

