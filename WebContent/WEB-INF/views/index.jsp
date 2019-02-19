<%@page import="com.douzon.guestbook.dao.GuestbookDao"%>
<%@page import="com.douzon.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	
<html >
<head>
<%
	List<GuestbookVo> list= (List)request.getAttribute("list");%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/gb?a=add" method="post">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 등록 "></td>
		</tr>
	</table>
	</form>
	<%for(int i=0;i<list.size();i++){ 
	GuestbookVo vo=list.get(i);
	vo.setMessage(vo.getMessage().replaceAll("\n", "<br>"));
	%>
	<br>
	<table width=510 border=1>
		<tr>
			<td>[<%=i+1 %>]</td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getReg_date() %></td>
			<td><a href="<%=request.getContextPath() %>/gb?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=vo.getMessage() %></td>
		</tr>
	</table>
	<%} %>
</body>
</html>