<%@page import="com.yyh.web.board.BoardDTO"%>
<%@page import="com.yyh.web.board.impl.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Do+Hyeon&family=Nanum+Gothic:wght@700&display=swap');	
.m_title {font-family: 'Bebas Neue', cursive; text-align: center; font-size: 5em;}
a {text-decoration : none; color:black}
.s_title {font-family: 'Nanum Gothic', sans-serif; font-size:1.5em; text-align: center;}
#container{width:500px; margin: 50px auto; padding:30px;background: #eee; border:1px solid gray;
          border-radius: 10px;}
input:focus, textarea:focus {outline: none;}
textarea {padding:5px;margin-top: 10px; width:100%;box-sizing: border-box;}
#title {padding:5px;margin-top: 10px; width:100%;box-sizing: border-box;} 
#writer {float: right;}
.btns{text-align: center}
.btns input[type="button"] {border:none; border-radius:10px; width:80px; height:30px; background:#FFE0B2; font-weight: bold; }
</style>

</head>
<body>

<%

String pageNum = request.getParameter("pageNum");
if (pageNum == null) pageNum = "1";

String memberId = (String)session.getAttribute("memberId");
if(memberId == null){
	out.print("<script>location('login.jsp);</script>");
}

int seq = Integer.parseInt(request.getParameter("seq"));
BoardDAO boardDAO = new BoardDAO();
BoardDTO dto = new BoardDTO();
dto.setSeq(seq);
BoardDTO board = boardDAO.getBoard(dto);

%>

<script>
document.addEventListener("DOMContentLoaded", function(){
	let form = document.updateForm;
	
	let btn_update = document.getElementById("btn_update");
	btn_update.addEventListener("click", function(){

		if(!form.title.value){
			alert("제목을 입력하시오");
			form.subject.focus();
			return
		}
		if(!form.content.value){
			alert("내용을 입력하시오");
			form.subject.focus();
			return
		}
		form.submit();	
	});
	let btn_boardList = document.getElementById("btn_boardList");
	btn_boardList.addEventListener("click", function(){
		location = "boardList.jsp?pageNum=<%= pageNum%>" 
	});
});
</script>
<div class="m_title"><a href="#">coder</a></div>
<div class="s_title">글 수정</div>
<div id="container"> 
	<form action="boardUpdate.do" method="post" name="updateForm">
		<input type="hidden" name="seq" value="<%=seq%>">
		<input type="hidden" name="pageNum" value="<%=pageNum %>">
			<input type="text" name="title" id="title" value="<%=board.getTitle() %>">
			<textarea rows="20" cols="60" name="content" id="content" style="resize: none;"><%=board.getContent() %></textarea> <br>
			<input type="text" name="writer" id="writer" value="<%=memberId%>"> <br>
			<br>
		<div class="btns">
			<input type="button" value="글 수정" id="btn_update">&emsp;&emsp;
			<input type="button" value="취소" id="btn_boardList">	
		</div>
	</form>
</div>
</body>
</html>