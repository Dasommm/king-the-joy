package com.project.kingthejoy.paging;

import org.springframework.stereotype.Repository;

@Repository
public class Paging {

	public PagingDto pagingMethod(int currentpage, int totalrows) {
		PagingDto paging = new PagingDto();
		paging.setCurrentpage(currentpage); // 현재 페이지 입력
		paging.setShowrows(10);				// 한 페이지 당 보여줄 글 수 
		paging.setBlockscale(5);				// 아래 페이지들 크기
		//총페이지(마지막 페이지)
		paging.setTotalpage((int)Math.ceil((double)totalrows/paging.getShowrows()));
		//Rownum의 시작~끝 세팅
		paging.setFrompage(paging.getShowrows() * (currentpage-1) +1);	
		paging.setTopage(paging.getShowrows() * currentpage);	
		//Blockgroup( 1:(1,2,3,4,5) 2:(6,7,8,9,10) ...)
		paging.setBlockgroup((int)Math.ceil((double)currentpage/paging.getBlockscale()));
		//block의 시작~끝 세팅
		paging.setBlockstart(paging.getBlockscale()*(paging.getBlockgroup()-1) +1);
		paging.setBlockend(paging.getBlockscale() * paging.getBlockgroup());
		return paging;
	}
	
}