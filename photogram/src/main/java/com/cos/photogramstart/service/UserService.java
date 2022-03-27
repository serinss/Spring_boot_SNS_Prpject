package com.cos.photogramstart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public User 회원수정(int id, User user) {
		//1.영속화 
		User userEntity = userRepository.findById(id).orElseThrow(()->{return new CustomValidationApiException("찾을 수 없는 id입니다.");}); //찾아서 userEntity에 담음
			//초반에 .get()을 붙여주는 이유? = 1번 유저가 만약 없을 경우 찾아줌
				//1.무조건 찾았다 걱정마
				//2.못찾았어 익섹션 발동시킬게 orElseThrow() --> 뒷단에서 유효성 검사
		
		//2.영속화된 오브젝트 수정 - 더티체킹(업데이트 완료)
		userEntity.setName(user.getName());
		
		//패스워드는 암호화 필수
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
				
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
		//더티 체킹이 일어나서 업데이트가 완료됨
	}
}
