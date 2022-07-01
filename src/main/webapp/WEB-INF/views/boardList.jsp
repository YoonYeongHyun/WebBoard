<%@page import="com.yyh.web.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.yyh.web.board.impl.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
	@import url('https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Do+Hyeon&family=Nanum+Gothic:wght@700&display=swap');
	#container {width:1000px; margin:0 auto;}
 	a {text-decoration : none; color:black}
 	#cnt{float:left; font-weight: bold}
	.m_title {font-family: 'Bebas Neue', cursive; text-align: center; font-size: 5em;}
	.s_title {font-family: 'Nanum Gothic', sans-serif; font-size:1.5em; text-align: center;}
	.top_info {clear: both; text-align: right;}
	.s_id{font-weight: bold}
	
	/* 게시글 목록*/
	
	table{width:100%; border-bottom: 2px solid black;border-top: 2px solid black; border-collapse: collapse;}
	tr{height:40px;}
	td{border-bottom:1px solid #edd;}
	th{background: #eee;}
	
	#search_box{text-align: center; padding: 20px}
	
	#paging{text-align: center; margin-top: 20px}
	#p_box{display: inline-block; width:25px; height:25px; border-radius: 10px; padding:5px; margin:5px }
	#p_box:hover{background: black; color:white;}
	.p_box_c{background: black; color:white; }
	.p_box_b{font-weight: bold}
</style>
</head>
<%
	//날짜형식 클래스
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	SimpleDateFormat sdf_today = new SimpleDateFormat("hh:mm");
	List<BoardDTO> boardList = (List<BoardDTO>)session.getAttribute("boardList");
	System.out.println(boardList);
	%>
<body>
	
	<div class="m_title">
		<a href="#">coder</a>
	</div>
	<div class="s_title">전체 게시판</div>
	<br>

	<div id="container">
		<div class="top_info">
			<span class="s_id">
				<a href="/memberInfo.do">${memberId}</a>
			</span>&emsp; 
			<a href="logout.do">로그아웃</a>&emsp; 
			<a href="boardWrite.do">글등록</a>
		</div>
		<br>
		<table>
			<tr>
				<th width="10%">번호</th>
				<th width="50%">제목</th>
				<th width="10%">작성자</th>
				<th width="15%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(boardList) eq 0}">
					<tr>
						<td colspan="5" style="text-align: center;">등록된 글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td style="text-align: right; margin-right: 20px">${board.seq}&ensp;&ensp;</td>
							<td>
								<a href="boardContent.do?seq=${board.seq}&pageNum=${pageNum}">${board.title }</a>
							</td>
							<td style="text-align: center">${board.writer}</td>
							<td style="text-align: center">${board.regDate}</td>
							<td style="text-align: center">${board.readCount}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>	
		<div id="search_box">
			<form action="boardList.do" method="post">
				<select name="search_condition">
					<c:forEach var="option" items="${conditionMap}" >
						<option value="${option.value}">${option.key}</option>
					</c:forEach>
				</select>
				<input type="text" name="search_keyword"> 
				<input type="submit" value="검색">
			</form>
		</div>
		<div id="paging">
		<c:if test="${count >0 }">
			<c:if test="${paging.startPage > 10}">
				<a href='boardList.do?pageNum=1&search_condition=${paging.search_condition}&search_keyword=${paging.search_keyword}'>
					<div id='p_box' class='p_box_b' title='첫 페이지'>≪</div>
				</a>
				<a href='boardList.do?pageNum=${paging.startPage-1}&search_condition=${paging.search_condition}&search_keyword=${paging.search_keyword}'>
					<div id='p_box' class='p_box_b'title='이전 페이지'>＜</div>
				</a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
				<c:choose>
					<c:when test="${p == paging.pageNum }">
						<div id='p_box' class='p_box_c'>${p}</div>
					</c:when>
					<c:when test="${p != paging.pageNum }">
						<a href='boardList.do?pageNum=${p}&search_condition=${paging.search_condition}&search_keyword=${paging.search_keyword}'>
							<div id='p_box'>${p}</div>
						</a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage <= paging.lastPage - (paging.lastPage % paging.pageScale) }">
				<a href='boardList.do=${paging.startPage + 10}&search_condition=${paging.search_condition}&search_keyword=${paging.search_keyword}'>
					<div id='p_box' class='p_box_b' title='다음 페이지'>＞</div>
				</a>
				<a href='boardList.do=${paging.lastPage}&search_condition=${paging.search_condition}&search_keyword=${paging.search_keyword}'>
					<div id='p_box' class='p_box_b' title='끝 페이지'>≫</div>
				</a>
			</c:if>
		</c:if>
		</div>
	</div>
</body>
</html>