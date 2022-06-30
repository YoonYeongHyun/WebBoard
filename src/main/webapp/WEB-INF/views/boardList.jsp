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
				<a href="/memberInfo.do"><%=memberId %></a>
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
			<c:set var="cnt" value="${count}" />
			<c:set var="currentPage" value="${pageNum}" />
			<c:set var="pageSize" value="10" />
			<c:if test="${cnt > 0}">
				<fmt:parseNumber var="cntPageSize" value="${cnt / pageSize}" integerOnly="true" />		
						
				<c:set var="pageCount" value="${(cntPageSize) + (cnt%pageSize==0 ? 0 : 1)}" />
				<c:set var="pageBlock" value="10" />
					
				<c:set var="startPage" value="1" />
				<c:choose>
					<c:when test="${currentPage % 10 != 0}">
						<fmt:parseNumber var="currentPage_stage" value="${currentPage/10}" integerOnly="true" />		
						<c:set var="startPage" value="${currentPage_stage * 10 +1 }" />
					</c:when>
					<c:otherwise>
						<fmt:parseNumber var="currentPage_stage" value="${currentPage/10}" integerOnly="true" />	
						<c:set var="startPage" value="${(currentPage_stage -1) * 10 +1}" />
					</c:otherwise>
				</c:choose>
				
				<c:set var="endPage" value="${startPage + pageBlock-1}" />
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}"/>
				</c:if>
				<c:if test="${startPage > 10}">
					<a href='productManagement?pageNum=1&category=${category}&search=${search}'>
						<div id='p_box' class='p_box_b' title='첫 페이지'>≪</div>
					</a>
					<a href='productManagement?pageNum=${startPage-10}&category=${category}&search=${search}'>
						<div id='p_box' class='p_box_b'title='이전 페이지'>＜</div>
					</a>
				</c:if>
				<c:forEach begin="${startPage}" end="${endPage}" step="1" varStatus="status">
					<c:choose>
						<c:when test="${currentPage == status.count+currentPage_stage*10}">
							<div id='p_box' class='p_box_c'>
								<c:out value="${status.count+currentPage_stage*10}" />
							</div>
						</c:when>
						<c:otherwise>
							<a href='productManagement?pageNum=${status.count+currentPage_stage*10}&category=${category}&search=${search}'>
								<div id='p_box'> 
									<c:out value="${status.count+currentPage_stage*10}" />
								</div>
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${endPage <= pageCount - (pageCount % pageSize)}">
					<a href='productManagement?pageNum=${startPage+10}&category=${category}&search=${search}'>
						<div id='p_box' class='p_box_b' title='다음 페이지'>＞</div>
					</a>
					<a href='productManagement?pageNum=${pageCount}&category=${category}&search=${search}'>
						<div id='p_box' class='p_box_b' title='끝 페이지'>≫</div>
					</a>
				</c:if>
			</c:if>
		</div>
	</div>
</body>
</html>