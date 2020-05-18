package com.project.kingthejoy.common.aop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.kingthejoy.member.dto.LogHistDto;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class CommonAop {
	
	public final static String elasticProtocol = "http"; 
	public final static String elasticHost     = "192.168.0.17";
	public final static Integer elasticPort    = 9200;
	
	@Around("execution(* com.project.kingthejoy..*Controller.*(..))")
	public Object logHistAop(ProceedingJoinPoint pjp){ 
		
		System.out.println("AOP is Workiung");
		Object ret = null;
		try { 
			ret = pjp.proceed();
			RestHighLevelClient client = new RestHighLevelClient(
					RestClient.builder(new HttpHost(elasticHost,elasticPort,elasticProtocol)));
			try {
				// Biz
				LogHistDto dto = new LogHistDto();
				Long nowDate = System.currentTimeMillis();
				
				Map<String,Object> dataMap = new HashMap<String, Object>();
				
				SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
				
				dto.setLogId(nowDate.toString());
				dto.setUserId("test");
				dto.setUserIp("0.0.0.0");
				dto.setRequestUri("/test");
				dto.setMethodName("test");
				dto.setObjectName("String");
				dto.setInptUid("test");
				dto.setInptUnm("test");
				dto.setInptDt(nowDate);
				
				ObjectMapper obj = new ObjectMapper();
				
				dataMap = obj.convertValue(dto, Map.class);
				
				IndexRequest request = new IndexRequest(
						"userhistory"
						,"history"
						,nowDate.toString()
						).source(dataMap,XContentType.JSON);

				IndexResponse response = client.index(request, RequestOptions.DEFAULT);
				System.out.println(response.getResult());
				
			} catch (java.lang.Exception e) {
				e.getStackTrace();
			} finally {
				if (client != null) {
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return ret; 
		
	}
}
