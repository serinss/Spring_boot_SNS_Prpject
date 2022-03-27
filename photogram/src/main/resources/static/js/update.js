// (1) 회원정보 수정
function update(userId, event) {
	//alert("update");
	event.preventDefault(); //폼태그 액션 막기! 해당 js가 가로채서 ajax실행되도록 함
	let data = $("#profileUpdate").serialize(); //해당 폼의 모든 값을 저장한다
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json" //json을 javascript로 파싱해서 받을 것이므로 res는 js
	}).done(res=>{ //HttpStatue 상태코드 200번대
		//console.log("update 성공", res);
		location.href=`/user/${userId}` //메인 페이지로 이동
		
	}).fail(error=>{ //HttpStatue 상태코드 200번대가 아닐 때
		if(error.data == null){
			alert(error.responseJSON.message);
		}else{
			alert(JSON.stringify(error.responseJSON.message));
		}
		//console.log("update 실패",error.responseJSON.data.name);
	});
}