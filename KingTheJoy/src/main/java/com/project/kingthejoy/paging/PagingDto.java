package com.project.kingthejoy.paging;

import lombok.Data;

@Data
public class PagingDto {
	
	private int showrows;		// 한 페이지에 몇 개의 글을 보여줄지 (10)
	private int currentpage;	// 현재 페이지 번호
	private int frompage;		// 페이지에서 시작하는 rownum 번호	(1~10, 11~20, 21~30)	
	private int topage;			// 페이지에서 끝나는 rownum 번호 (즉, frompage ~ topage)
	private int blockscale;		// 아래 페이지(스케일) 크기 (5)
	private int blockstart;		// 스케일 시작 번호 (1,6,11..)
	private int blockend;		// 스케일 끝 번호	(5,10,15..)
	private int blockgroup;		// 스케일 번호 1은 (1,2,3,4,5) 2는(6,7,8,9,10)
	private int totalpage;		// 총 페이지 수 = 마지막 페이지 번호 (글88개/10개=9페이지)
	
}