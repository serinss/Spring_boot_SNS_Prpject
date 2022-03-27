package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	//CMRespDto, Script 비교
	//1. 클라이언트에게 응답할 때는 Script 좋음
	//2. Ajax 통신 시, CMRespDto
	//3. Android 통신, CMRespDto
	
	// 잘 구분해서 만들어놓자
	
	
	//1. 클라이언트에게 응답할 때는 JavaScript 리턴
	@ExceptionHandler(CustomValidationException.class) //RuntimeException이 발생하는 모든 클래스를 핸들
	public String validationException(CustomValidationException e) {

		return Script.back(e.getErrorMap().toString());
	}
	
	//2. Ajax 통신 시, CMRespDto 리턴
	@ExceptionHandler(CustomValidationApiException.class) //RuntimeException이 발생하는 모든 클래스를 핸들
	public ResponseEntity<?> validationException(CustomValidationApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class) //RuntimeException이 발생하는 모든 클래스를 핸들
	public ResponseEntity<?> apiException(CustomApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
}
