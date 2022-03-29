package com.cos.photogramstart.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
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
	private final EntityManager em; //Repository는 EntityManager를 구현해서 만들어져있는 구현체
	
	@Transactional(readOnly = true)
	public List<SubscribeDto> 구독리스트(int principalId, int pageUserId){
		
		// 쿼리 준비
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
		sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");
		sb.append("if ((?=u.id), 1, 0) equalUserState ");
		sb.append("FROM user u INNER JOIN subscribe s ");
		sb.append("ON u.id = s.toUserId ");
		sb.append("WHERE s.fromUserId = ?"); // 세미콜론 첨부하면 안됨
		
		// 1.물음표 principalId
		// 2.물음표 principalId
		// 3.물음표 pageUserId
		
		// 쿼리 완성
		Query query = em.createNativeQuery(sb.toString())
				.setParameter(1, principalId)
				.setParameter(2, principalId)
				.setParameter(3, pageUserId);
		
		// 쿼리 실행 (qlrm 라이브러리 필요 = DTO에 DB결과를 매핑하기 위해서)
		JpaResultMapper result = new JpaResultMapper();
		List<SubscribeDto> subscribeDtos =  result.list(query, SubscribeDto.class);
		
		return subscribeDtos;
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
