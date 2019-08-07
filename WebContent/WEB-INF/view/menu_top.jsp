<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "VO.memberVo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu_top</title>

<% memberVo memberVo = (memberVo)session.getAttribute("memberVo"); 
%>

</head>
<body>
<table border="2" width="100%" bgcolor="#E0E6F8">
	<tr align="center">
		<td bgcolor="#FAFAFA">${memberVo.user_name}님 반갑습니다<p><p>
		<a href="logout.do">로그아웃</a></td>
		<td><a href="docList.do?page=1&listType=newList&user_id=${memberVo.user_id}"> <font style="font-family:돋움">[새 문서 목록함]</font></a></td>
		<td><a href="docList.do?page=1&listType=receiveList&user_id=${memberVo.user_id}"> <font style="font-family:돋움">[수신 문서 목록함]</font></a></td>
		<td><a href="docList.do?page=1&listType=sendList&user_id=${memberVo.user_id}"><font style="font-family:돋움">[발신 문서 목록함]</font></a></td>
		<td>문서 쓰기<p>
		<a href="movPage.do?doc_Type=oneDoc"><font style="font-family:돋움">개인문서</font></a> <a href="movPage.do?doc_Type=groupDoc"><font style="font-family:돋움">그룹문서</font></a><p>
		<a href="movPage.do?doc_Type=stepDoc"><font style="font-family:돋움">단계문서</font></a> <a href="movPage.do?doc_Type=allDoc"><font style="font-family:돋움">전체문서</font></a></td>
	</tr>
</table>
</body>
</html>