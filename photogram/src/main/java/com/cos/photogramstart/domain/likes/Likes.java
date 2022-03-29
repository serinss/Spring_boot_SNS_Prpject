package com.cos.photogramstart.domain.likes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.subscribe.Subscribe;
import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",
						columnNames= {"imageId","userId"}
				)
		}
)
public class Likes { //N
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name="imageId")
	@ManyToOne
	private Image iamge; //1
	
	// 둘 다 무한참조가 일어나므로 오류가 날 것임!
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; //1
	
	
	private LocalDateTime createDate;
	
	@PrePersist //DB에 INSERT되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
