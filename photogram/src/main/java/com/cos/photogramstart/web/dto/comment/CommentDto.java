package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	
	@NotBlank //빈값이거나 null 체크
	private String content;
	@NotNull //빈값 체크
	private Integer imageId;
	
	//toEntity필요 없음
}
