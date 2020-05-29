//package com.project.kingthejoy.common.aop;
//
//import java.io.IOException;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.apache.http.HttpHost;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.kingthejoy.member.dto.LogHistDto;
//import com.project.kingthejoy.member.dto.MemberDto;
//
//@Aspect
//@Configuration
//@EnableAspectJAutoProxy
//public class CommonAop {
//	public final static String elasticProtocol = "http";
//	public final static String elasticHost = "0.0.0.0";
//	public final static Integer elasticPort = 9200;
//
//	@Around("execution(* com.project.kingthejoy.*.controller.*Controller.*(..))")
//	public Object logHistAop(ProceedingJoinPoint pjp) throws Throwable {
//
//		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
//				.getSession();
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//				.getRequest();
//
//		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
//
//		//String elasticHost = InetAddress.getLocalHost().getHostAddress();
//
//
//		System.out.println("AOP is Working");
//		Object ret = null;
//
//		ret = pjp.proceed();
//		RestHighLevelClient client = new RestHighLevelClient(
//				RestClient.builder(new HttpHost(elasticHost, elasticPort, elasticProtocol)));
//
//		// Biz
//		LogHistDto dto = new LogHistDto();
//		Long nowDate = System.currentTimeMillis();
//
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		if (memberDto != null) {
//			dto.setLogId(nowDate.toString());
//			dto.setUserId(session.getId());
//			dto.setUserIp(request.getServerName());
//			dto.setRequestUri(request.getRequestURI());
//			dto.setMethodName(pjp.getSignature().getName());
//			dto.setObjectName(pjp.getSignature().toLongString());
//			dto.setInptUid(memberDto.getMember_id());
//			dto.setInptUnm(memberDto.getMember_name());
//			dto.setInptDt(nowDate);
//		} else {
//			dto.setLogId(nowDate.toString());
//			dto.setUserId("비회원");
//			dto.setUserIp(request.getServerName());
//			dto.setRequestUri(request.getRequestURI());
//			dto.setMethodName(pjp.getClass().getName());
//			dto.setObjectName(pjp.getClass().getTypeName());
//			dto.setInptUid("비회원");
//			dto.setInptUnm("비회원");
//			dto.setInptDt(nowDate);
//		}
//		ObjectMapper obj = new ObjectMapper();
//
//		dataMap = obj.convertValue(dto, Map.class);
//
//		IndexRequest indexRequest = new IndexRequest("userhistory", "history", nowDate.toString()).source(dataMap,
//				XContentType.JSON);
//
//		IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
//		System.out.println(response.getResult());
//		client.close();
//
//		return ret;
//	}
//}