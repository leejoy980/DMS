<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>로그인폼</title></head>
<body bgcolor="#F2F2F2">
<table height="300">
<tr>
	<td></td>
</tr>
</table>
<form action="<%= request.getContextPath() %>/loginProc.jsp"
      method="post">
<table align="center" bgcolor="#E6E6E6">
	<tr>
		<td colspan="3" align="center"><h2>문서 관리 시스템</h2></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><input type="text" name="id" size="10"></td>
		<td rowspan="2"><input type="submit" value="로그인" style="height:50"></td>
	</tr>
	<tr>
		<td>암호 </td>
		<td><input type="password" name="password" size="10"></td>
	</tr>
	<tr>
		<td colsapn="3"></td>
	</tr>
</table>
</form>

</body>
</html>
