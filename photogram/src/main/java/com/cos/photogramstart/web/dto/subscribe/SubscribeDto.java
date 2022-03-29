package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	
	private int id; //현재 페이지의 유저
	private String username;
	private String profileImageUrl;
	
	//True값을 int로 하면 못받아서 Integer로 함
	private Integer subscribeState; //구독한 상태인지 아닌지 확인
	private Integer equalUserState; //구독 리스트의 내가 동일인인지 아닌지 확인
}