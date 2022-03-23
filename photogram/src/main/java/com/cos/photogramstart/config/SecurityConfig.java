package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration //IoC     
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
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
			.antMatchers("/","/user/**","image/**","/subscribe/**","/comment/**").authenticated() //해당 url은 인증을 받아야 하고
			.anyRequest().permitAll() //나머지 요청은 모두 허용한다
			.and()
			.formLogin()
			.loginPage("/auth/signin") // 인증되지 않는 사용자가 위의 url에 접근하면 해당 페이지로 이동하도록 하자 
			.defaultSuccessUrl("/"); //로그인이 정상적으로 처리되면 /로 이동
	}
}
