package com.cos.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto { //우선 update할 때, 이름과 이메일은 변경 불가로 설정
	@NotBlank
	private String name; //필수
	@NotBlank
	private String password; //필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	//조금 위험한. 코드 수정 필요 -> 필수가 아닌 값들을 다 엔티티로 만들기 때문
	public User toEntity() {
		return User.builder()
				.name(name) //이름을 기재 안했으면 문제!! Validation 체크 필요
				.password(password) //패스워드를 기재 안했으면 문제!! Validation 체크 필요함(무조건
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
