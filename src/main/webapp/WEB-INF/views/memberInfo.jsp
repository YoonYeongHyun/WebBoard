<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 확인</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Do+Hyeon&family=Nanum+Gothic:wght@700&display=swap');
	#container {width:700px; margin:0 auto;}
 	a {text-decoration : none; color:black}
	.m_title {font-family: 'Bebas Neue', cursive; text-align: center; font-size: 5em;}
	.m_title a{color:#DC8538}
	.s_title {font-family: 'Nanum Gothic', sans-serif; font-size:1.5em; text-align: center;}
	table{width:100%; border-top:2px solid black; border-bottom: 2px solid black; }
	th, td{ border-bottom: 1px solid lightgray;}
	td{width:550px}
	tr {height:50px;}
	input[type=text], input[type=password]{background:#eee; border:none; height:25px; width:200px}
	input[type=text]:focus, input[type=password]:focus {background:white;}
	#btn_addr{ height:25px; width:80px; background: #e9967a; color:white; font-weight:bold; 
				border:none; border-radius: 5px; cursor:pointer;}
	.addr_row{height:100px}
	.addr_row #addr2 { margin-top: 10px}
	.btns{text-align:center; margin:30px 0 0 10px;}
	.btns input[type=button]{width:120px; height:40px}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function(){
    let form = document.infoForm;
    let id = form.id;
    let password = form.password;
    let password2 = form.password2;
    let name = form.name;
    let email = form.email;
    let tel = form.tel;
    let addrNum = form.addrNum;
    let addr1 = form.addr1;
    let addr2 = form.addr2;
    
  	//비밀번호의 유효성검사
    let chk_pwd = document.getElementById("chk_pwd");
    password.addEventListener("keyup", function (){
    	if(password.value.length < 4){
    		chk_pwd.style.color = "red";
    		chk_pwd.innerText = "비밀번호가 짧습니다.";
    	}else {
    		chk_pwd.style.color = "green";
    		chk_pwd.innerText = "사용가능한 비밀번호입니다.";
    	}
    });
	
  	//비밀번호확인 검사
    let chk_pwd2 = document.getElementById("chk_pwd2");
    password2.addEventListener("keyup", function (){
    	if(password.value == password2.value){
    		chk_pwd2.innerText = "";
    	}else {
    		chk_pwd2.style.color = "red";
    		chk_pwd2.innerText = "비밀번호와 다릅니다.";
    	}
    });
    
    //이메일 주소의 유효성검사
    let chk_email = document.getElementById("chk_email");
    email.addEventListener("keyup", function (){
    	let isEmail = (value) =>{
            return (value.indexOf("@") > 1) && (value.split("@")[1].indexOf(".") > 1);
        }
            
        if(isEmail(email.value)){ // 이메일 형식일 
      		chk_pwd.style.color = "green";
        	chk_email.innerText = "이메일 형식입니다."
     	 } else {// 이메일 형식이 아닐 때
    		chk_pwd.style.color = "red";
    		chk_email.innerText = "이메일 형식이 아닙니다."
       	}
    });
    
  	//주소입력칸을 클릭하면
    document.getElementById("btn_addr").addEventListener("click", function (){
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	addrNum.value = data.zonecode; // 주소 넣기
                addr1.value = data.address; // 주소 넣기
                document.querySelector("input[name=addr2]").focus(); //상세입력 포커싱
            }
        }).open();
    });
  	
  	//회원수정버튼
  	let btn_update = document.getElementById("btn_update");
  	btn_update.addEventListener("click", function (){
	
		if(password.value.length == 0){
			alert('비밀번호를 입력하시오');
			password.focus();
			return;
		}
		if(password2.value.length == 0){
			alert('비밀번호 확인을 입력하시오');
			password2.focus();
			return;
		}
		if(password2.value != password.value){
			alert('비밀번호확인을 제대로 입력하시오');
			return;
		}
		if(name.value.length == 0){
			alert('이름을 입력하시오');
			name.focus();
			return;
		}
		if(email.value.length == 0){
			alert('이메일을 입력하시오');
			email.focus();
			return;
		}
		if(tel.value.length == 0){
			alert('연락처를 입력하시오');
			tel.focus();
			return;
		}
		if(addrNum.value.length == 0){
			alert('주소를 입력하시오');
			addrNum.focus();
			return;
		}
		form.submit();
  	});
	
  	//회원탈퇴버튼
  	let btn_delete = document.getElementById("btn_delete");
  	btn_delete.addEventListener("click", function (){

		if(password.value.length == 0){
			alert('비밀번호를 입력하시오');
			password.focus();
			return;
		}
		if(password2.value.length == 0){
			alert('비밀번호 확인을 입력하시오');
			password2.focus();
			return;
		}
		
		location="/memberDelete.do"
  	});
  	
});

</script>
</head>
<body>
<div id="container" >
	<div class="m_title"><a href="#">coder</a></div>
	<div class="s_title">회원정보</div>
	
	<form action="memberUpdate.do" method="post" name="infoForm">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="id" id="id" class="c_id" value="${member.id}" readonly>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="password" id="password" maxlength='16'  value="">
					<span id="chk_pwd"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호확인</th>
				<td>
					<input type="password" name="password2" id="password2" maxlength='16'>
					<span id="chk_pwd2"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" id="name" maxlength='8' value="${member.name}"> </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" name="email" id="email" maxlength='30' value="${member.email}">
					<span id="chk_email"> </span>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" id="tel" maxlength='11' value="${member.tel}"> </td>
			</tr>
			<tr class="addr_row">
				<th>주소</th>
				<td>
					<input type="text" name="addrNum" id="addrNum" value="${member.addrNum}"> 
					<input type="button" value="주소찾기" id="btn_addr"><br>
					<input type="text" name="addr1" id="addr1" value="${member.addr1}">&nbsp;
					<input type="text" name="addr2" id="addr2" value="${member.addr2}"> 
				</td>
			</tr>
			<tr>
				<th>가입 일자</th>
				<td>${member.regdate}</td>
			</tr>
		</table>
		<div class="btns">
			<input type="button" value="회원 정보 수정" id="btn_update">
			<input type="button" value="회원 탈퇴" id="btn_delete">
		</div>
	</form>
</div>

</body>
</html>