package docWrite.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.documentDao;
import VO.detailDocVo;
import VO.memberVo;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class docModifyHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/oneDocWrite.jsp";
	private documentDao documentDao = new documentDao();

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
	private String processSubmit(HttpServletRequest req,HttpServletResponse res){
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors",errors);
		
		memberVo memberVo = (memberVo)req.getSession(false).getAttribute("memberVo");
		
			detailDocVo detailDocVo = createDetailDocVo(req);
			detailDocVo.validate(errors);
			String str = "[수정]";
			detailDocVo.setTitle(str.concat(detailDocVo.getTitle()));
			
			try(Connection conn = ConnectionProvider.getConnection()){
			documentDao.modify(conn, detailDocVo);
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		return "docList.do?page=1&listType=sendList&user_id="+memberVo.getUser_id();
	}
	private detailDocVo createDetailDocVo(HttpServletRequest req){
		return new detailDocVo(Integer.parseInt(req.getParameter("doc_id")),req.getParameter("title"),req.getParameter("content"),
			req.getParameter("attached"));
	}
}
