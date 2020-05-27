package com.project.kingthejoy.children.dto;

import lombok.Data;

@Data
public class ChildrenPagingDto {

	private int rows;
	private int page;
	private int totalpage;
	private int startpage;
	private int endpage;
	private int pagescale;
}
