<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedOutputStream"%>



    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String f = (String)request.getParameter("filename");
	String savePath = request.getSession().getServletContext().getRealPath("/file");
	String filename = f;
	String orgfilename = f;
	
	File file = null;
    BufferedInputStream fin = null;
    BufferedOutputStream outs = null;
 	out.clear();
    //jsp에서 output을 쓸 때 내장객체에 선언되어 있는 것이 있어서 오류가 난다 out.clear()를 해주면 오류 해결
    
   try{
        file = new File(savePath, filename); // 파일경로에 파일 만들기
        response.reset();
 
        response.setHeader("Content-Type","application/pdf");
        response.setHeader("Content-Disposition","attachment;filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1")); // 다운로드창 호출
 
        if(file != null){
            fin = new BufferedInputStream(new FileInputStream(file)); // 파일 읽기
            outs = new BufferedOutputStream(response.getOutputStream()); // 파일 쓰기
 
            int read = 0;
 
            while((read = fin.read()) != -1 ){
                outs.write(read); 
            }
        }
 
    }catch(Exception e){
        response.setContentType("text/html;charset=euc-kr");
        out.println("<script type='text/javascript'>");
        out.println("alert('파일 오픈 중 오류가 발생하였습니다.');");
        out.println("</script>");
    }finally{
 
        if(outs != null) fin.close();
        if(fin != null) outs.close();
 
    }
	%>
</body>
</html>