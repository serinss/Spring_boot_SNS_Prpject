package com.cos.photogramstart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//해당 페이지의 주인 아이디를 받아야 함
	@Transactional(readOnly = true)
	public UserProfileDto 회원프로필(int pageUserId, int principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		// SELECT * FROM image WHERE userId =:userId; 이 쿼리를 JPA형태로 작성
		User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다."); //유효성 검사 아니므로 CustomException 생성
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId==principalId);
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
		int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
		
		dto.setSubscribeState(subscribeState==1);
		dto.setSubscribeCount(subscribeCount);
		
		return dto;
	}

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
