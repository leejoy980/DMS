<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Dao.memberDao" %>
<%@ page import = "VO.memberVo" %>
<%@ page import = "jdbc.connection.ConnectionProvider" %>
<%@ page import = "java.sql.Connection" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String user_id = request.getParameter("id");
	String pwd = request.getParameter("password");
	String uri = request.getContextPath();
	
	memberDao memberDao = new memberDao();
	Connection conn = ConnectionProvider.getConnection();
	memberVo memberVo = memberDao.selectById(conn,user_id);
	
	if(memberVo == null){
		response.sendRedirect(uri+"/index.jsp");
	}
	if(!memberVo.getPwd().equals(pwd)){
		response.sendRedirect(uri+"/index.jsp");
	}
	
	session.setAttribute("memberVo",memberVo);
	response.sendRedirect(uri+"/docList.do?page=1&listType=newList&user_id="+user_id);
	
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
환영합니다
</body>
</html>