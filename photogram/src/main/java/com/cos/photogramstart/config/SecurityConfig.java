package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //IoC     
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder endcode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화 된다.
		// 인증이 되지 않는 사용자는 모두 로그인 페이지로 이동하도록
		http.csrf().disable(); //CSRF 토큰 검사 비활성화
		http.authorizeRequests()
			.antMatchers("/","/user/**","image/**","/subscribe/**","/comment/**","/api.**").authenticated() //해당 url은 인증을 받아야 하고
			.anyRequest().permitAll() //나머지 요청은 모두 허용한다
			.and()
			.formLogin()
			.loginPage("/auth/signin") // 인증되지 않는 사용자가 위의 url에 접근하면 해당 페이지로 이동하도록 하자 (GET)
			.loginProcessingUrl("/auth/signin") //인증된 사용자는 POST 방식으로  스프링 시큐리티가 로그인 프로세스 진행
			.defaultSuccessUrl("/") //로그인이 정상적으로 처리되면 /로 이동
			.and()
			.oauth2Login() //form 로그인도 하는데, oauth2 로그인도 할 것이다
			.userInfoEndpoint() //oauth2로그인을 하면 최종 응답을 회원 정보로 바로 받을 수 있다
			.userService(oAuth2DetailsService); //OAuth2DetailsService 등록
	}
}
