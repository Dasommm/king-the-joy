package com.project.kingthejoy.principal.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PrincipalDto {
	private int member_seq;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_phone;
	private String member_email;
	private int member_role;
	private String member_delflag;
	private String member_oaddr;
	private String member_addr;
	private String member_detailaddr;
	private Date member_date;
	private String member_image;
	private String class_name;
	private int school_seq;
	private int children_seq;
	private String children_image;
}
