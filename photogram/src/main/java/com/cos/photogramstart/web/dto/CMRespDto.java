package com.cos.photogramstart.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> { //전역적으로 사용 될 것이기 때문에 <T>선언
	
	private int code; //1(성공), -1(실패)
	private String message;
	private T data;
	
}