package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service //1.IoC 2.트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional //Write(Insert, Update, Delete) 할때에는 걸어주자
	public User 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); //암호화 된 패스워드
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); //관리자는 ROLE_ADMIN
		
		User userEntity = userRepository.save(user); //외부 데이터가 DB에 들어간 뒤 응답받기
		return userEntity;
	}
}
