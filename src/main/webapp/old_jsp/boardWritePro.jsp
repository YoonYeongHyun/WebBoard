<%@page import="com.yyh.web.board.impl.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%request.setCharacterEncoding("utf-8");
	String writer = (String)session.getAttribute("memberId");
	%>
	<jsp:useBean id="board" class="com.yyh.web.board.BoardDTO"/>
	<jsp:setProperty property="*" name="board"/>

	<%
	BoardDAO boardDAO = new BoardDAO();
	boardDAO.insertBoard(board);
	response.sendRedirect("boardList.jsp");
	%>
</body>
</html>