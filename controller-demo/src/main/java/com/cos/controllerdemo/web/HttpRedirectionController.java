package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HttpRedirectionController {
	
	@GetMapping("/home")
	public String home() {
		//1만줄 가정
		return "home";
	}
	
	@GetMapping("/away")
	public String away() {
		//다른 코드
		return "redirect:/home"; //리다이렉션이 된다 (@Controller일 때만 발동)
	}
}
