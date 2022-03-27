package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException{

	//객체를 구분(중요X)
	private static final long serialVersionUID = 1L;

	
	public CustomException(String message) {
		super(message);
	}
}
