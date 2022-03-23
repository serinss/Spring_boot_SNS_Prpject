package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //file을 리턴할 것이기 때문에
public class HttpRespController {
	
	@GetMapping("/txt")
	 public String txt() {
		 return "a.txt"; 
		 //프레임워크를 사용 중 (틀이 이미 정해져 있음) - 일반 정적 파일은 resources/static 내부가 디폴트 경로이다.
		 //여기에만 둬야 함!
	 }
	
	@GetMapping("/mus")
	public String mus() {
		return "b"; 
		//머스태치 템플릿 엔진 라이브러리 등록 완료 - templates 폴더 안에 .mustache를 두면 확장자 없이 파일 명만 적으면
		//자동으로 찾아감
	}
	
	@GetMapping("/jsp")
	public String jsp() {
		return "c"; //jsp 엔진 사용 : src/main/webapp 폴더가 디폴트 경로!!
		//WEB-INF부터는 직접 만든 파일임
		// prefix와 suffix를 붙여서 /WEB-INF/views/c.jsp 해당 경로를 찾는다! (ViewResolver가 발동)
	}
}
