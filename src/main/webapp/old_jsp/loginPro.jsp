<%@page import="com.yyh.web.member.MemberDTO"%>
<%@page import="com.yyh.web.member.impl.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String password = request.getParameter("password");

MemberDTO dto = new MemberDTO();
dto.setId(id);
dto.setPassword(password);

MemberDAO memberDAO = new MemberDAO();
int cnt = memberDAO.login(dto);	%>
<script>
<% if(cnt == 1){
	session.setAttribute("memberId", id);   
	response.sendRedirect("boardList.jsp");
%>
<%}else if (cnt == 0){%>
	alert('비밀번호가 틀립니다.');
	history.back();
<%}else if (cnt == 2){%>
	alert('존재하지않는 회원입니다.');
	history.back();
<%}else if (cnt == -1){%>
	alert('오류가 발생하였습니다.');
	history.back();
<%}%>
</script>


</body>
</html>