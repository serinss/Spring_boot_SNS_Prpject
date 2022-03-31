package com.cos.photogramstart.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//userRequest 안에 정보가 있다
		OAuth2User oauth2User = super.loadUser(userRequest);
		//System.out.println(oauth2User.getAttributes());
		
		Map<String, Object> userInfo = oauth2User.getAttributes();
		String username = "facebook_"+(String)userInfo.get("id"); 
		String name = (String)userInfo.get("name");
		String email = (String)userInfo.get("email");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()); //암호화 필요
		
		User userEntity = userRepository.findByUsername(username);
		if(userEntity==null) {
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.role("ROLE_USER")
					.build();
			
			return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes());
		}else {
			return new PrincipalDetails(userEntity, oauth2User.getAttributes());
		}
		
	}
}
