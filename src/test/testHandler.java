package test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VO.detailDocVo;
import VO.memberVo;
import docWrite.service.docWriteService;
import mvc.command.CommandHandler;

public class testHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/oneDocWrite.jsp";
	private docWriteService wirteService = new docWriteService();

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
		detailDocVo detailDocVo = createDetailDocVo(memberVo,req);
		detailDocVo.validate(errors);
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		
			req.setAttribute("test", detailDocVo);
		
		return "/WEB-INF/view/testView.jsp";
	}
	private detailDocVo createDetailDocVo(memberVo memberVo,HttpServletRequest req){
		return new detailDocVo
	(req.getParameter("doc_Type"),req.getParameter("title"),req.getParameter("content"),req.getParameter("attached"),memberVo.getUser_id(),req.getParameter("des_1"));
	}
}
