package docWrite.handler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Dao.personalDao;
import VO.detailDocVo;
import VO.memberVo;
import docWrite.service.docWriteService;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class docWriteHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/oneDocWrite.jsp";
	private docWriteService wirteService = new docWriteService();
	private personalDao personalDao = new personalDao();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req,res);
		} else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
		
	private String processForm(HttpServletRequest req,HttpServletResponse res){
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String path = "D:/Tomcat 8.0/webapps/Docmanager2/WebContent/file";
		
		MultipartRequest multi = new MultipartRequest(req,path,10 * 1024 * 1024,"UTF-8",new DefaultFileRenamePolicy());

		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors",errors);
		
		
		
		memberVo memberVo = (memberVo)req.getSession(false).getAttribute("memberVo");
		if(multi.getParameter("doc_Type").equals("oneDoc")){
			detailDocVo detailDocVo = createDetailDocVo(memberVo,multi);
			detailDocVo.validate(errors);
			if(multi.getParameter("modify").equals("modify")){
				try(Connection conn = ConnectionProvider.getConnection()){
				personalDao.sendDelete(conn,Integer.parseInt(multi.getParameter("doc_id")));
				}catch(SQLException e){
					throw new RuntimeException(e);
				}
			}
			wirteService.write(detailDocVo);

		} else if(multi.getParameter("doc_Type").equals("stepDoc")){
			detailDocVo detailDocVo = createStepDetailDocVo(memberVo,multi);
			detailDocVo.validate(errors);
			wirteService.write(detailDocVo);
		} else if(multi.getParameter("doc_Type").equals("groupDoc")){
			detailDocVo detailDocVo = createDetailDocVo(memberVo,multi);
			detailDocVo.validate(errors);
			wirteService.groupWrite(detailDocVo);
		} else if(multi.getParameter("doc_Type").equals("answerDoc")){
			detailDocVo detailDocVo = createDetailDocVo(memberVo,multi);
			detailDocVo.setDoc_Type("groupDoc");
			detailDocVo.validate(errors);
			wirteService.answerWrite(detailDocVo,Integer.parseInt(multi.getParameter("OrDoc_id")));
			
			return "boardList.do?page="+multi.getParameter("page")+"&boardType="+multi.getParameter("des_1");
		} else if(multi.getParameter("doc_Type").equals("allDoc")){
			detailDocVo detailDocVo = createDetailDocVo(memberVo,multi);
			detailDocVo.setDoc_Type("groupDoc");
			detailDocVo.validate(errors);
			wirteService.allWrite(detailDocVo);
		}
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		return "docList.do?page=1&listType=sendList&user_id="+memberVo.getUser_id();
	}
	private detailDocVo createDetailDocVo(memberVo memberVo,MultipartRequest multi){
		return new detailDocVo(multi.getParameter("doc_Type"),multi.getParameter("title"),multi.getParameter("content"),
				multi.getFilesystemName("attached"),memberVo.getUser_id(),multi.getParameter("des_1"));
	}
	private detailDocVo createStepDetailDocVo(memberVo memberVo,MultipartRequest multi){
		return new detailDocVo(multi.getParameter("doc_Type"),multi.getParameter("title"),multi.getParameter("content"),
				multi.getFilesystemName("attached"),memberVo.getUser_id(),multi.getParameter("des_1"),multi.getParameter("des_2"),multi.getParameter("des_3"));
	}
}
