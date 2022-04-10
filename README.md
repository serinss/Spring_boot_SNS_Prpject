# 🗳 Gather Thoughts 책 발췌 공유 SNS 플랫폼
스프링부트 인스타그램 강의 [메타코딩] 참조 <br>
![gather_main-min (1)](https://user-images.githubusercontent.com/96467897/162605893-eb1b3094-6fec-4068-b827-dd0ffaade753.gif)
<br>

## 👨‍👨‍👧 개인 프로젝트
- 김세린

<br>

## 📃 프로젝트 개요
- instagram SNS 클론코딩을 활용하여 같은 SNS 기반의 독서 경험 공유 플랫폼 형성 목적
- 좋은 글의 모음에는 좋은 사람들과 함께 해당 글에 대한 생각을 이야기할 수 있는 장으로서 활용될 수 있음
<br>

## 🛠 기술 스택
- Java, Spring boot, Spring Security
- JavaScript, HTML5, CSS3
- MariaDB, SQL
<br>

### 프로젝트 스케치

### ERD
## DB
![entity](https://user-images.githubusercontent.com/96467897/162606761-03a0b957-51a0-4cfe-919f-f751c6cfe9e2.PNG)

|Table|Column|설명|
|---|---|---|
|user|id(PK) : 아이디 <br> password : 비밀번호  <br> username(FK) : 이름 <br> phone : 핸드폰 번호 <br> email : 이메일 주소 <br> gender : 젠더 구분 <br> bio : 자기 소개 <br> createDate : 회원 가입 날짜 <br> profileImageUrl : 프로필 이미지 저장 경로  <br> role : 일반 회원/관리자 구분  <br> website : 참고 주소 |회원 정보를 저장하는 테이블|
|image|id(PK) : 아이디 <br> userId(FK) : 등록 유저 아이디 <br> caption : 사진 설명  <br> createDate : 등록 날짜  <br> postImageUrl : 이미지 저장 경로 |등록 이미지 게시물 정보를 저장하는 테이블|
|subscribe|id(PK) : 아이디 <br> createDate : 구독한 날짜  <br> fromUserId : 구독한 유저 아이디(팔로워)  <br> toUserId : 구독된 유저 아이디(팔로잉) |구독 정보를 저장하는 테이블|
|likes|id(PK) : 아이디 <br> createDate : 좋아요한 날짜  <br> imageId : 좋아요한 이미지 아이디  <br> userId : 좋아요한 유저 아이디 |좋아요 정보를 저장하는 테이블|
|comment|id(PK) : 아이디 <br> imageId : 댓글을 단 이미지 아이디  <br> userId : 댓글을 단 유저 아이디  <br> content : 댓글 내용  <br> createDate : 댓글 등록 날짜 |댓글 정보를 저장하는 테이블|
<br>


## 주요기능
- 
<br>
<br>

## 🧨 Trouble Shooting



## 💊 보완할 점
- 
