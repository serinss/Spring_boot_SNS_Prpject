package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //web설정파일

	@Value("${file.path}")
	private String uploadFoler;
	
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			WebMvcConfigurer.super.addResourceHandlers(registry);
			
			registry
				.addResourceHandler("/upload/**") //jsp페이지에서 /upload/ 주소 패턴이 나오면 발동
				.addResourceLocations("file:///"+uploadFoler) //uploadFoler는 application.yml에 등록한 그 path
				.setCachePeriod(60*10*6) //초*분*시 = 1시간
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		}
}
