
package com.project.kingthejoy.children.biz;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.kingthejoy.children.dto.ChildrenDto;
import com.project.kingthejoy.children.dto.ChildrenPagingDto;
import com.project.kingthejoy.pill.dto.PillPagingDto;

public interface ChildrenBiz {
	
	public List<ChildrenDto> selectList(int school_seq,PillPagingDto pdto);
	
	public int totalpage (int rows,HttpSession session);
	
	public int insert(ChildrenDto dto);
	
    
	

}

