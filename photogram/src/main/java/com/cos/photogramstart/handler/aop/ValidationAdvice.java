package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@Component
@Aspect
public class ValidationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		System.out.println("web api 컨트롤러==========================");
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg:args) {
			if(arg instanceof BindingResult) {
				System.out.println("유효성 검사를 하는 함수입니다.");
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error: bindingResult.getFieldErrors()) { 
						errorMap.put(error.getField(), error.getDefaultMessage()); //저장되어 있던 error를 Map에 담아보기
					}
					throw new CustomValidationApiException("유효성 검사 실패 ", errorMap); //강제로 발생시키기
					//이 때, 우리는 기존에 쓰던 Exception을 그대로 발동시키느 것이 아니라
					//새롭게 ApiException 클래스를 만들어서 발동시켜야 함
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		System.out.println("web 컨트롤러=============================");
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg:args) {
			if(arg instanceof BindingResult) {
				System.out.println("유효성 검사를 하는 함수입니다.");
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error: bindingResult.getFieldErrors()) { 
						errorMap.put(error.getField(), error.getDefaultMessage()); //저장되어 있던 error를 Map에 담아보기
					}
					throw new CustomValidationException("유효성 검사 실패 ", errorMap); //강제로 발생시키기
					
				}
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
