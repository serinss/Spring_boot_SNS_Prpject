# 🗳 Gather Thoughts 책 발췌 공유 SNS 플랫폼
인스타그램 참조
<br>

## 👨‍👨‍👧 개인 프로젝트
- 김세린

<br>

## 🗓 프로젝트 기간
- 2022.01.21~2022.02.08 (19일)
<br>

## 📃 프로젝트 개요
- 
<br>

## 🛠 기술 스택
- Java
- JavaScript, HTML5, CSS3
- Oracle, SQL
<br>

### 프로젝트 스케치
![프로젝트 스케치](https://user-images.githubusercontent.com/96467897/161971769-ff8e1a46-d8cc-4420-bd76-826f0ab44ddc.PNG)

### ERD
![index MVC](https://user-images.githubusercontent.com/96467897/161972758-a0dfbeaa-ce3b-4321-a358-664dbc10c4de.png)
![Auction MVC](https://user-images.githubusercontent.com/96467897/161972774-88af6fb4-6a96-4b46-a9bb-c5ee9f58b590.png)
## DB
![DB_Diagram](https://user-images.githubusercontent.com/70681797/162094059-342e3f2a-87de-4072-a10b-0bb9245d0a52.png)

|Table|Column|설명|
|---|---|---|
|FTBL_MEMBER|id : 아이디 <br> pwd : 비밀번호  <br> name : 이름 <br> phone : 핸드폰 번호 <br> email : 이메일 주소 <br> type : 회원 유형 구분 <br> warning_cnt : 누적 경고 수 |회원 정보를 저장하는 테이블|
|FTBL_PRODUCT|pd_no : 경매(제품) 번호 <br> id : 등록한 회원 아이디 <br> pd_name : 제품명 <br> hope_price : 경매 희망가 <br> start_price : 경매 시작가 <br> reg_date : 경매 등록일 <br> due_date : 경매 마감일 <br> pd_simple_info : 제품 게시글 제목 <br> pd_info : 제품 소개글 <br> c_no : 카테고리 번호 <br> view_cnt : 누적 조회수 <br> like_cnt : aa <br> sug_cnt : 누적 응찰수 <br> warn_cnt : 게시글 누적 경고수 |경매(제품) 정보를 저장하는 테이블|
|FTBL_PRODUCT_FILE|no : 이미지 고유 번호 <br> pd_no : 해당 이미지의 경매(제품) 번호 <br> file_ori_name : 오리지널 파일 이름 <br> file_save_name : 파일 저장 이름 <br> file_size : 파일 사이즈 |제품 이미지 파일 정보를 저장하는 테이블|
|FTBL_CATEGORY|c_no : 카테고리 번호 <br> category : 카테고리명 |제품 카테고리 분류를 저장하는 테이블|
|FTBL_AUCTION|a_no : 제시가 기록 고유 번호 <br> pd_no : 해당 경매(제품) 번호 <br> id : 제시한 회원 아이디 <br> sug_price : 제시가 <br> sug_date : 제시한 날짜 |경매 응찰 내용을 기록하는 테이블|
|FTBL_HEART|id : 마음 누른 회원 아이디 <br> pd_no : 마음 누른 경매(제품) |마음 누르기(위시리스트)를 기록하는 테이블|
|FTBL_QNA_BOARD|b_no : 질의응답 문의글 고유 번호 <br> id : 질의응답을 작성하는 회원 아이디 <br> pd_no : 질의응답 할 경매(제품) 번호 <br> title : 문의글 제목 <br> content : 문의글 내용 <br> reg_date : 문의글 등록일 <br> group_id : 부모글 <br> depth : 답글 깊이 <br> pos : 답글 순서 |질의응답 문의글 내용을 담는 테이블|
|FTBL_SOLD|s_no : 낙찰 정보 고유 번호 <br> pd_no : 경매(제품) 번호 <br> sug_id : 제시한 회원 아이디 <br> sug_price : 제시가 <br> sug_date : 제시한 날짜 <br> payment : 결제여부 |경매 최종 낙찰 및 결제 유무를 저장하는 테이블|
|FTBL_BLIND|pd_no : 경매(제품) 번호 <br> id : 등록한 회원 아이디 <br> pd_name : 제품명 <br> hope_price : 경매 희망가 <br> start_price : 경매 시작가 <br> reg_date : 경매 등록일 <br> due_date : 경매 마감일 <br> pd_simple_info : 제품 게시글 제목 <br> pd_info : 제품 소개글 <br> c_no : 카테고리 번호 <br> view_cnt : 누적 조회수 <br> like_cnt : aa <br> sug_cnt : 누적 응찰수 <br> warn_cnt : 게시글 누적 경고수 <br> del_date : 블라인드 처리한 날짜 |블라인드 처리한 경매(제품) 정보를 저장하는 테이블|
<br>


## 주요기능

### 경매
- 경매 등록, 수정, 검색
- 경매 참여(제시)
- 마음함(위시리스트) 추가, 제거
- 참여한 경매, 낙찰된 경매 조회
- 낙찰된 경매 결제
- QnA문의글-답글 등록
<br>

### 관리자
- 회원 정보 조회
- 부적절한 경매 블라인드 처리
- 회원(판매자) 경고 처리
<br>

## 🧨 Trouble Shooting



## 💊 보완할 점
- 관리자 페이지 기능 다양화
  - 직접 회원 신고 기능 추가 : 현재 게시물을 통해 1회 경고 가능, 다양한 방법으로 관리 기능 활성화 필요
- 낙찰 시, 낙찰자에게 알람 기능 추가
- 판매 후처리 관련
  - 직거래 vs 택배 : 경매 낙찰 이후 물품 거래 방법 모호
  - 직거래로 하는 경우, 채팅 기능을 추가하여 직거래 위치 조정
  - 택배로 하는 경우, 결제 시 배송지 입력
- QnA 게시판 ajax를 통한 비 동기식 동작 구현 시도 (현재, JSP 동기식 구현) 
