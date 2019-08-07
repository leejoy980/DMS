<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>그룹 문서 수정</title>
</head>
<body bgcolor="#F2F2F2">
<form action="docModify.do" method="post" enctype="multipart/form-data">
	<table border="1" width="100%" height="600">
	<tr height="50">
		<td colspan="7" align="center" bgcolor="#EFF2FB"><h3>문서 관리 시스템</h3></td>
	</tr>
	<tr height="100">
		<td colspan="3">
		<jsp:include page="/WEB-INF/view/menu_top.jsp" flush="false"></jsp:include>
		</td>
	</tr>
	<tr>
		<td rowspan="5" width="130">
		<jsp:include page="/WEB-INF/view/menu_left.jsp" flush="false"></jsp:include>
		</td>
	</tr>
	<tr>
		<td width="100">
		제목   :  
		</td>
		<td>
		<input type="text" name="title" value="${param.title}">
		</td>
	</tr>
	<tr>
		<td width="100">
		첨부 : 
		</td>
		<td>
		<input type="file" name="attached" value="${param.attached}">
		</td>
	</tr>
	<tr height="500">
		<td width="100">
		내용:
		</td>
		<td>
		<textarea name="content" rows="30" cols="200">${param.content}</textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
	<input type=hidden name="doc_Type" value="modifyDoc">
	<input type=hidden name="doc_id" value="${param.doc_id}">
	<input type="submit" value="수정">
	<input type="button" value="취소" onClick="history.go(-1)">
	</td>
	</tr>
	</table>
	
	</form>
</body>
</html>