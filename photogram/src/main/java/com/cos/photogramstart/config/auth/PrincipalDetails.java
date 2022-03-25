package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private static final long serialVersionUID=1L;
	
	private User user;
	
	
	public PrincipalDetails(User user) {
		this.user=user;
	}

	//권한 : 한 개가 아닐 수 있음(3개 이상의 권한도 가능) -> 컬렉션 리턴
	@Override //권한 가져오기 -> 이 권한은 user가 가지고 있다
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collector = new ArrayList<>();
		//람다식으로 함수 넘겨주기
		collector.add(()->{ return user.getRole();});
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override //계정이 만료 되었는지를 확인
	public boolean isAccountNonExpired() {
		return true; //즉, 나중에는 user.getExpired 같은 것을 대신 넣어서 확인할 수 있음
	}

	@Override //계정이 잠금 되었는지를 확인
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override //이 계정의 비밀번호가 1년이 지나도록 바뀌지 않았는지를 확인
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override //이 계정이 활성화 되어 있는지를 확인
	public boolean isEnabled() {
		return true;
	}

}
