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
	<table border="1" width="99%" height="600" bgcolor="#FAFAFA">
	<tr height="50">
		<td colspan="7" align="center" bgcolor="#EFF2FB"><h3>문서 관리 시스템</h3></td>
	</tr>
	<tr height="100">
		<td colspan="7"><jsp:include page="/WEB-INF/view/menu_top.jsp" flush="false"></jsp:include></td>
	</tr>
	<tr align="center" height="50">
		<td rowspan="19" width="130">
		<jsp:include page="/WEB-INF/view/menu_left.jsp" flush="false"></jsp:include>
		</td>
		<td colspan="7">
		<c:if test="${boardType != 'all'}">
			${boardType}
			<c:if test="${boardType == '1' || boardType == '2' || boardType == '3' || boardType == '4'}">
			학년
			</c:if>
		</c:if>
		<c:if test="${boardType == 'all'}">
		전체 
		</c:if>
		게시판
		</td>
	</tr>
	<tr align="center" height="50">
		<td>문서번호</td>
		<td width="400">제목</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	
	<c:forEach var="boardListContent" items="${boardList.content}">
		<tr align="center" height="30">
			<td width="10%">${boardListContent.doc_id}</td>
			<td width="70%" align="left"><a href="docRead.do?answer=${boardListContent.answer}&boardType=${boardType}&listType=receiveList&board=board&doc_id=${boardListContent.doc_id}&page=${boardList.currentPage}">${boardListContent.title}</a></td>
			<td width="10%">${boardListContent.gen_user_name}</td>
			<td width="10%">${boardListContent.created_time}</td>
	</c:forEach>
	<c:if test="${boardList.hasDoc()}">
		<tr align="center" height="50">
			<td colspan="7">
				<c:if test="${boardList.startPage > 5}">
					<a href="boardList.do?page=${boardList.startPage - 5}&boardType=${boardType}&">[이전]</a>
				</c:if>
				<c:forEach var="pNo"
					begin="${boardList.startPage}"
					end="${boardList.endPage}">
					<a href="boardList.do?page=${pNo}&boardType=${boardType}">[${pNo}]</a>
				</c:forEach>
				<c:if test="${boardList.endPage < boardList.totalPages}">
				<a href="boardList.do?page=${boardList.startPage + 5}&boardType=${boardType}">[다음]</a>
				</c:if>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="7" align="center">
			<form action="boardList.do" method="post">
				<select name="searchType">
				<option value="doc_idSearch">문서번호</option>
				<option value="titleSearch">제목</option>
				</select>
				<input type="text" name="search">
				<input type=hidden name="page" value="1">
				<input type=hidden name="user_id" value="${memberVo.user_id}">
				<input type=hidden name="boardType" value="${boardType}">
				<input type="submit" value="검색">
			</form>
		</td>
	</tr>
	</table>
</b>
</body>
</html>