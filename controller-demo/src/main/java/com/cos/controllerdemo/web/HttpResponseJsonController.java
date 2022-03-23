package com.cos.controllerdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.controllerdemo.domain.User;

@RestController
public class HttpResponseJsonController {
	
	@GetMapping("/resp/json")
	public String respJson() {
		return "{\"username\":\"cos\"}"; //쌍따옴표 역슬래쉬 붙여야 하므로 노트패드에 적고 옮기기!
	}
	
	@GetMapping("/resp/json/object")
	public String respJsonObject() {
		User user = new User();
		user.setUsername("홍길동");
		
		String data = "{\"username\":\""+user.getUsername()+"\"}"; //미친짓
		return data;
	}
	
	@GetMapping("/resp/json/javaobject")
	public User respJsonJavaObject() {
		User user = new User();
		user.setUsername("홍길동");
		
		return user; 
		//1. MessageConverter가 자동으로 JavaObject를 Json(구:xml)로 변경해서 통신을 통해 응답해준다.
		//2. @RestController 일때만 MessageConverter가 작동한다
	}
	
}
