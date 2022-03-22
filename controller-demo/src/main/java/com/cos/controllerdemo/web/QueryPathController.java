package com.cos.controllerdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryPathController {
	
	@GetMapping("/chicken")
	public String chickenQuery(String type) { //변수값으로 전달 받음
		return type + " 배달갑니다.(쿼리스트링)";
		//http://localhost:8080/chicken?type=양념
		//양념 배달갑니다
	}
	
	@GetMapping("/chicken/{type}") //{}로 전달 받을 수 있음
	public String chickenPath(@PathVariable String type) {
		return type + " 배달갑니다.(주소변수매핑)";		
		//http://localhost:8080/chicken/양념 --> 훨씬 가독성이 좋다 따라서 스프링에서는 쿼리스트링은 거의 쓰지 않음
		//양념 배달갑니다
	}
}
