<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "VO.memberVo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu_left</title>

<% memberVo memberVo = (memberVo)session.getAttribute("memberVo"); 
%>

</head>
<body>
<table height="100%" width="130" bgcolor="#E0E6F8" border="2">
	<tr align="center">
		<td><a href="boardList.do?page=1&boardType=all"><font style="font-family:돋움">전체 게시판</font></a></td>
	</tr>
	<tr align="center">
		<td><a href="boardList.do?page=1&boardType=${memberVo.groupType}"><font style="font-family:돋움">${memberVo.groupType} 게시판</font></a></td>
	</tr>
	<tr align="center">
		<td><a href="boardList.do?page=1&boardType=${memberVo.grade}"><font style="font-family:돋움">${memberVo.grade}학년 게시판</font></a></td>
	</tr>
	<tr align="center">
		<td><a href="boardList.do?page=1&boardType=${memberVo.classType}"><font style="font-family:돋움">${memberVo.classType}반 게시판</font></a></td>
	</tr>
	<tr align="center">
		<td><a href="boardList.do?page=1&boardType=${memberVo.club}"><font style="font-family:돋움">${memberVo.club} 게시판</font></a></td>
	</tr>
</table>
</body>
</html>