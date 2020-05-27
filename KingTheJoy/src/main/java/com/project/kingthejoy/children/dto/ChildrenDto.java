package com.project.kingthejoy.children.dto;

import lombok.Data;

@Data
public class ChildrenDto {
	//CHILDREN TABLE
	private int children_seq;//자녀 번호
	private String children_name;//아이 이름
	private String riding_quit;//승하차 여부
	//private String children_img;
	
	//MEMBER TABLE
	private int member_seq;//회원번호 ->어느 회원의 아이가 2명일 경우

	// 변경사항 확인//
	//selectList를 뽑기 위해서
	//MEMBER TABLE
	
	//SCHOOL TABLE
	private int school_seq;
	
	//CLASS TABLE
	private int class_seq;
	private String class_name;
	
	//select children_seq member_seq children_name ... class_name
	//from 회원테이블 join 자녀테이블.... join... join 반테이블
	
	//dao에서 list dto 게시글 
	//dto = sqlSession.selectOne("")
	//list = sqlSession.selectList("")
	
    /*
	선생님
	private String member_id;
	private int school_seq;
	private STring class_name;
	
	//select member id, school seq, class_name; = *
	//from 회원테이블 join school 테이블 join class 테이블...
	//where member id teacher1
	*/
	
}
