package com.cos.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.IoC 2.트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository;
	
	@Transactional //Write(Insert, Update, Delete) 할때엔느 걸어주자
	public User 회원가입(User user) {
		//회원가입 진행
		User userEntity = userRepository.save(user); //외부 데이터가 DB에 들어간 뒤 응답받기
		return userEntity;
	}
}
