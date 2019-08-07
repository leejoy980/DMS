<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>단계 문서 상세보기</title>
</head>
<body bgcolor="#F2F2F2">
	<table border="1" width="100%" height="400">
	<tr height="50">
		<td colspan="7" align="center" bgcolor="#EFF2FB"><h3>문서 관리 시스템</h3></td>
	</tr>
	<tr height="100">
		<td colspan="7"><jsp:include page="/WEB-INF/view/menu_top.jsp" flush="false"></jsp:include></td>
	</tr>
	<tr align="center" height="50">
		<td rowspan="14" width="130">
		<jsp:include page="/WEB-INF/view/menu_left.jsp" flush="false"></jsp:include>
		</td>
	</tr>
	<tr>
		<td width="100" align="center">문서번호</td>
		<td >${detailDocVo.doc_id}</td>
	</tr>
	<tr>
		<c:if test="${listType != 'sendList'}">
			<td width="100" align="center">작성자ID</td>
		</c:if>
		<c:if test="${listType != 'sendList'}">
			<td>${detailDocVo.gen_user_id}</td>
		</c:if>
		<c:if test="${listType == 'sendList'}">
			<td width="100" align="center">수신자ID</td>
		</c:if>
		<c:if test="${listType == 'sendList'}">
			<td>${detailDocVo.des_1}</td>
		</c:if>
	</tr>
	<tr>
		<c:if test="${listType != 'sendList'}">
			<td width="100" align="center">작성자NAME</td>
		</c:if>
		<c:if test="${listType != 'sendList'}">
			<td>${detailDocVo.gen_user_name}</td>
		</c:if>
		<c:if test="${listType == 'sendList'}">
			<td width="100" align="center">수신자NAME</td>
		</c:if>
		<c:if test="${listType == 'sendList'}">
			<td>${detailDocVo.des_1_name}</td>
		</c:if>
	</tr>
	<tr>
		<td width="100" align="center">상태</td>
		<td>${detailDocVo.state}</td>
	</tr>
	<tr>
		<td width="100" align="center">제목</td>
		<td>${detailDocVo.title}</td>
	</tr>
	<tr>
		<td width="100" align="center">첨부파일</td>
		<td><a href="download.jsp?filename=${detailDocVo.attached}">${detailDocVo.attached}</a></td>
	</tr>
	<tr height="400">
		<td align="center">내용</td>
		<td>${detailDocVo.content}</td>
	</tr>
	<tr height="50">
		<td colspan="2" align="center">
		<c:if test="${listType == 'sendList'}">
		<a href="docList.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}">[확인]</a>
			<c:if test="${detailDocVo.state == 'first' || detailDocVo.state == 'complete'}">
				<a href="sendDocDelete.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}&doc_id=${detailDocVo.doc_id}">[삭제]</a>
			</c:if>
		</c:if>	
		<c:if test="${listType != 'sendList'}">
		<a href="docApp.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}&doc_id=${detailDocVo.doc_id}&gen_user_id=${detailDocVo.gen_user_id}&state=${detailDocVo.state}">[승인]</a>
		<a href="docReturn.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}&doc_id=${detailDocVo.doc_id}&gen_user_id=${detailDocVo.gen_user_id}">[반송]</a>
		<a href="docList.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}">[대기]</a>
		<a href="docHold.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}&doc_id=${detailDocVo.doc_id}">[재게시]</a>
			<c:if test="${detailDocVo.state == 'complite'}">
				<a href="receiveDocDelete.do?page=${page}&listType=${listType}&user_id=${memberVo.user_id}&doc_id=${detailDocVo.doc_id}">[삭제]</a>
			</c:if>
		</c:if>	
		</td>
	</tr>
	</table>
</body>
</html>