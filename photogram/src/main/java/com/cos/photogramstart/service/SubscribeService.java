package com.cos.photogramstart.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {

	private final SubscribeRepository subscribeRerpository;
	
	@Transactional(readOnly=true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		
		
		return null;
	}
	
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		//해당 정보는 오브젝트이므로 등록하기 복잡함.. 레파지토리에 네이티브 쿼리를 만들어 사용하자
		try {
			subscribeRerpository.mSubscribe(fromUserId, toUserId);
			
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다.");
		}
	}
	
	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) { //이건 오류가 날 일이 없음
		subscribeRerpository.mUnSubscribe(fromUserId, toUserId);
	}
}
