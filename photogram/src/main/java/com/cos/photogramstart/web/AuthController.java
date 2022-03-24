package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //final이 걸려 있는 모든 변수의 생성자 생성 = Final필드를 DI할 때 사용
@Controller //1. IoC 등록 완료 2. File 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	
	private final AuthService authService;
	
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	} -> 생성자 코드를 매번 작성하려니 힘듦 -> final을 붙여주고 @RequiredArgsConstructor 붙여주자!
	

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	//회원 가입 버튼 -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { //회원 가입 진행 key=value(x-www-form-urlencoded타입)
		//Valid 통과 못하면 bindingResult에 다 모아준다
		//@ResponseBody 파일이 아닌 데이터를 응답
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: bindingResult.getFieldErrors()) { 
				errorMap.put(error.getField(), error.getDefaultMessage()); //저장되어 있던 error를 Map에 담아보기
			}
			throw new CustomValidationException("유효성 검사 실패 ", errorMap); //강제로 발생시키기
			
		}else {
			//log.info(signupDto.toString());
			//User <- SignupDto
			User user = signupDto.toEntity();
			//log.info(user.toString());
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin";
		}
		
		
		
	}
	
}
