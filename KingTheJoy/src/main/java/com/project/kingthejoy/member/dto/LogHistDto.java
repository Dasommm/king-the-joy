package com.project.kingthejoy.member.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LogHistDto {
	
    String logId;         //로그아이디
    String userId;         //user_id
    String userIp;         //사용자_ip
    String requestUri;      //사용자_ip
    String objectName;      //사용자_ip
    String methodName;      //사용자_ip
	String inptUid;         //등록자_id
	String inptUnm;         //등록자
	Long  inptDt;         //등록일시

}
