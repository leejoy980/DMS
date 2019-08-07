package docControl.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import docControl.service.appDocService;
import mvc.command.CommandHandler;

public class appDocHandler implements CommandHandler {
	
	private appDocService appDocService = new appDocService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		String user_id = req.getParameter("user_id");
		String noVal = req.getParameter("doc_id");
		String page = req.getParameter("page");
		String listType = req.getParameter("listType");
		String state = req.getParameter("state");
		String gen_user_id = req.getParameter("gen_user_id");
		int doc_id = Integer.parseInt(noVal);
		
		appDocService.approvalDoc(doc_id, user_id, state, gen_user_id);
		
		return "docList.do?page="+page+"&listType="+listType+"&user_id="+user_id;
	}

}
