package com.project.kingthejoy.children.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.kingthejoy.children.dao.ChildrenDao;
import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.children.dto.ChildrenPagingDto;

@Service
public class ChildrenBizImpl implements ChildrenBiz {

	@Autowired
	private ChildrenDao dao;

	@Override
	public List<ChildrenDto> selectList(int school_seq) {
		
	
		return dao.selectList(school_seq);
	}

	@Override
	public int insert(ChildrenDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int children_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
