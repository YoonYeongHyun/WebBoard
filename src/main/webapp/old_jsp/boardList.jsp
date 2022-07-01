<%@page import="com.yyh.web.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.yyh.web.board.impl.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	//페이징(paging) 처리
	int cnt=100;
	int pageSize = 10;
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null) pageNum = "1";

	int currentPage = Integer.parseInt(pageNum);    //현재페이지
 	int startRow = (currentPage -1) * pageSize + 1; //현재페이지의 첫행
	int endRow = currentPage * pageSize;            //현재페이지의 마지막행
	
	//날짜형식 클래스
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	SimpleDateFormat sdf_today = new SimpleDateFormat("hh:mm");
	List<BoardDTO> boardList = (List<BoardDTO>)session.getAttribute("boardList");
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
				<a href="../member/memberInfoForm.jsp?pageNum=<%=pageNum%>"> ${memberId} </a>
			</span>&emsp; 
			<a href="logout.jsp">로그아웃</a>&emsp; 
			<a href="boardWriteForm.jsp?pageNum=<%=pageNum%>">글등록</a>
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
			<%
			if (boardList.size() == 0) {
			%>
			<tr>
				<td colspan="5">등록된 글이 없습니다.</td>
			</tr>
			<%
			} else {
				for (BoardDTO board : boardList) {
					int num = board.getSeq();
			%>
			<tr>
				<td style="text-align: right; margin-right: 20px"><%=num%>&ensp;&ensp;</td>
				<td>
					<a href="boardContent.jsp?seq=<%=board.getSeq()%>&pageNum=<%=pageNum%>"><%=board.getTitle()%></a>
				</td>
				<td style="text-align: center"><%=board.getWriter()%></td>
				<td style="text-align: center"><%=sdf.format(board.getRegDate())%></td>
				<td style="text-align: center"><%=board.getReadCount()%></td>
			</tr>
				<%}%>
			<%}%>

		</table>	
		<div id="search_box">
			<form action="boardList.jsp" method="post">
				<select>
					<option>제목</option>
					<option>내용</option>
					<option>작성자</option>
				</select>
				<input type="text"> 
				<input type="submit" value="검색">
			</form>
		</div>
		<div id="paging">
		<c:if test="${count >0 }">
			<c:if test="${paging.startPage > 10}">
				<a href='boardList.do?pageNum=1'><div id='p_box' class='p_box_b' title='첫 페이지'>≪</div></a>
				<a href='boardList.do?pageNum=${paging.startPage-1}'><div id='p_box' class='p_box_b'title='이전 페이지'>＜</div></a>
			</c:if>
			<c:forEach begin="${paging.startPage }" end="2" var="p">
				<c:choose>
					<c:when test="${p == paging.pageNum }">
						<div id='p_box' class='p_box_c'>${p}</div>
					</c:when>
					<c:when test="${p != paging.pageNum }">
						<a href='boardList.do?pageNum=${paging.pageNum}'><div id='p_box'>${p}</div></a>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:if test="${paging.endPage <= paging.lastPage - (paging.lastPage % paging.pageScale) }">
				<a href='boardList.jsp.do=${paging.startPage + 10}'><div id='p_box' class='p_box_b' title='다음 페이지'>＞</div></a>
				<a href='boardList.jsp.do=${paging.lastPage}'><div id='p_box' class='p_box_b' title='끝 페이지'>≫</div></a>
			</c:if>
		</c:if>
		</div>
	</div>
</body>
</html>