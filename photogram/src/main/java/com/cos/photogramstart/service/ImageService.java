package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	
	@Transactional(readOnly = true) //영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영) X
	public Page<Image> 이미지스토리(int principalId, Pageable pageable){
		Page<Image> images = imageRepository.mStroy(principalId, pageable);
		
		//images 에 좋아요 상태 담기
		images.forEach((image)->{
			
		});
		return images;
	}
	
	
	
	@Value("${file.path}") //application.yml 에 등록한 file -> path 업로드 경로는 다양한 곳에서 사용되므로 꼭 하드코딩은 제외하자
	private String uploadFoler;
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"-"+imageUploadDto.getFile().getOriginalFilename(); //1.jpg 오리지널 이름이 저장됨
		//System.out.println("이미지 파일 이름:"+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFoler+imageFileName);
		
		//통신, I/O가 일어날 때, 예외가 발생할 수 있다. (이런 것들은 런타임 시에만 잡을 수 있으므로 유효처리 필요!)
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); //de2c4d67-ebf1-4759-bddf-734660dcccb6-munzii.PNG
		Image imageEntity = imageRepository.save(image);
		
	}
}
