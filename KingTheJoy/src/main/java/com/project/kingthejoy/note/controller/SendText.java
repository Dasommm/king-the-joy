package com.project.kingthejoy.note.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */

public class SendText {
	
	public static void main(String[] args) {
	    String api_key = "NCSVCJVNJVT88XHA";
	    String api_secret = "GDDB4HEPH53AVA3MEDYWJCUJI9YWAQ80";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "01000000000");	//수신자 : 학부모 번호 
	    params.put("from", "01000000000");	//발신자 : 선생님 번호
	    params.put("type", "LMS");
	    params.put("text", "Coolsms Testing Message!");
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	  }

}
