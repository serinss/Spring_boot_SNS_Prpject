package com.cos.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {
	
	private final UserService userService;

	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id, 
			@Valid UserUpdateDto userUpdateDto, 
			BindingResult bindingResult, //위치 중요!! 꼭 @Valid 다음 파라미터에 적어야 함
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: bindingResult.getFieldErrors()) { 
				errorMap.put(error.getField(), error.getDefaultMessage()); //저장되어 있던 error를 Map에 담아보기
			}
			throw new CustomValidationApiException("유효성 검사 실패 ", errorMap); //강제로 발생시키기
			//이 때, 우리는 기존에 쓰던 Exception을 그대로 발동시키느 것이 아니라
			//새롭게 ApiException 클래스를 만들어서 발동시켜야 함
			
		}else {
			//System.out.println(userUpdateDto);
			User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
			principalDetails.setUser(userEntity); //세션값도 수정된 값으로 변경해놔야 화면에 반영됨 -> 패스워드 암호화 주의!!!
			return new CMRespDto<>(1, "회원수정완료", userEntity);
		}
		
	}
}
