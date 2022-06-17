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
	String memberId = (String) session.getAttribute("memberId");
	if (memberId == null) { //세션이 null인 경우
		out.print("<script>alert('로그인 하세요');location='/login.jsp'</script>");
	}
	
	//페이징(paging) 처리
	int cnt=1;
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
				<a href="../member/memberInfoForm.jsp?pageNum=<%=pageNum%>"><%=memberId %></a>
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
								<a href="boardContent.do?seq=${board.seq}&pageNum=<%=pageNum%>">${board.title }</a>
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
		<%
		
		if(cnt > 0){
			int pageCount =(cnt/pageSize) + (cnt%pageSize==0 ? 0 : 1);	
			int pageBlock = 10;
			
			//시작페이지 설정
			int startPage = 1;
			if(currentPage % 10 != 0) startPage = (currentPage/10) * 10 +1;
			else startPage = (currentPage/10 -1) * 10 +1;
			
			//끝페이지 설정
			int endPage = startPage + pageBlock - 1;
			if(endPage > pageCount) endPage = pageCount;
			
			//이전&첫 페이지
			if(startPage > 10){%>
				<a href='boardList.jsp?pageNum=<%=1 %>'><div id='p_box' class='p_box_b' title='첫 페이지'>≪</div></a>
				<a href='boardList.jsp?pageNum=<%=startPage-10 %>'><div id='p_box' class='p_box_b'title='이전 페이지'>＜</div></a>
			<%}
			//페이징블럭처리
			for (int i=startPage; i<=endPage; i++){
				if(currentPage == i){
					%><div id='p_box' class='p_box_c'><%=i %></div><%
				} else{
					%><a href='boardList.jsp?pageNum=<%=i %>'><div id='p_box'> <%=i %> </div></a><% 
				}
			}

			//다음&마지막 페이지
			if(endPage <= pageCount - (pageCount % pageSize)){%>
				<a href='boardList.jsp?pageNum=<%=startPage+10%>'><div id='p_box' class='p_box_b' title='다음 페이지'>＞</div></a>
				<a href='boardList.jsp?pageNum=<%=pageCount%>'><div id='p_box' class='p_box_b' title='끝 페이지'>≫</div></a>
			<%}
			//
		}
		%>
		</div>
	</div>
</body>
</html>