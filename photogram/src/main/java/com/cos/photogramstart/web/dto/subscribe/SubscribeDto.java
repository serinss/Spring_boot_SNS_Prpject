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
	
	private int userId; //현재 페이지의 유저
	private String username;
	private String profileImageUrl;
	
	private Integer subscriveState; //구독한 상태인지 아닌지 확인
	private Integer equalUserState; //구독 리스트의 내가 동일인인지 아닌지 확인
}
