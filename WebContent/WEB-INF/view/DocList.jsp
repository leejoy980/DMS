<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "VO.memberVo" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문서 목록함</title>
<% memberVo memberVo = (memberVo)session.getAttribute("memberVo"); %>

</head>
<body bgcolor="#E6E6E6">
<b>
	<table border="1" width="100%" height="600" bgcolor="#FAFAFA">
	<tr height="50">
		<td colspan="7" align="center" bgcolor="#EFF2FB"><h3>문서 관리 시스템</h3></td>
	</tr>
	<tr height="100">
		<td colspan="7"><jsp:include page="/WEB-INF/view/menu_top.jsp" flush="false"></jsp:include></td>
	</tr>
	<tr align="center" height="50">
		<td rowspan="19" width="130" bgcolor="#A9F5E1">
		<jsp:include page="/WEB-INF/view/menu_left.jsp" flush="false"></jsp:include>
		</td>
		<td colspan="7">
		
		<c:if test="${listType == 'newList'}">
		새 문서 목록함
		</c:if>
		<c:if test="${listType == 'receiveList'}">
		수신 문서 목록함
		</c:if>
		<c:if test="${listType == 'sendList'}">
		발신 문서 목록함
		</c:if>	
		</td>
	</tr>
	<tr align="center" height="50">
		<td>문서번호</td>
		<td width="400">제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>분류</td>
		<td>상태</td>
	</tr>
	
	<c:forEach var="docListContent" items="${docList.content}">
		<tr align="center" height="30">
			<td width="10%">${docListContent.doc_id}</td>
			<td width="50%" align="left"><a href="docRead.do?doc_id=${docListContent.doc_id}&listType=${listType}&page=${docList.currentPage}">${docListContent.title}</a></td>
			<td width="10%">${docListContent.gen_user_name}</td>
			<td width="10%">${docListContent.created_time}</td>
			<td width="10%">${docListContent.doc_Type}</td>
			<td width="10%">${docListContent.state}</td>
	</c:forEach>
	<c:if test="${docList.hasDoc()}">
		<tr align="center" height="50">
			<td colspan="7">
				<c:if test="${docList.startPage > 5}">
					<a href="docList.do?page=${docList.startPage - 5}&listType=${listType}&user_id=${memberVo.user_id}">[이전]</a>
				</c:if>
				<c:forEach var="pNo"
					begin="${docList.startPage}"
					end="${docList.endPage}">
					<a href="docList.do?page=${pNo}&listType=${listType}&user_id=${memberVo.user_id}">[${pNo}]</a>
				</c:forEach>
				<c:if test="${docList.endPage < docList.totalPages}">
				<a href="docList.do?page=${docList.startPage + 5}&listType=${listType}&user_id=${memberVo.user_id}">[다음]</a>
				</c:if>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="7" align="center">
			<form action="docList.do" method="post">
				<select name="searchType">
				<option value="doc_idSearch">문서번호</option>
				<option value="titleSearch">제목</option>
				</select>
				<input type="text" name="search">
				<input type=hidden name="page" value="1">
				<input type=hidden name="user_id" value="${memberVo.user_id}">
				<input type=hidden name="listType" value="${listType}">
				<input type="submit" value="검색">
			</form>
		</td>
	</tr>
	</table>
</b>
</body>
</html>