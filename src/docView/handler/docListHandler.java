package docView.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import docView.service.docListPageService;
import docView.service.docListService;
import mvc.command.CommandHandler;

public class docListHandler implements CommandHandler {
	
	private docListService listService = new docListService();
	String a = "xxx";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		String pageNoVal = req.getParameter("page");
		String listType = req.getParameter("listType");
		String user_id = req.getParameter("user_id");
		String searchType = req.getParameter("searchType");
		String search = req.getParameter("search");

	
		int pageNo = 1;
		int searchDoc_id=1;
		if(pageNoVal!=null){
			pageNo=Integer.parseInt(pageNoVal);
		}
		
		
		if(searchType!=null){
			if(searchType.equals("doc_idSearch")){
				searchDoc_id=Integer.parseInt(search);
				docListPageService docListPageService = listService.getDoc_idSearchPage(pageNo,user_id,listType,searchDoc_id);
				req.setAttribute("docList", docListPageService);
			} else if(searchType.equals("titleSearch")){
				docListPageService docListPageService = listService.getTitleSearchPage(pageNo,user_id,listType,search);
				req.setAttribute("docList", docListPageService);
			}
		} else{
			docListPageService docListPageService = listService.getDocListPage(pageNo,user_id,listType);
			req.setAttribute("docList", docListPageService);
		}
		
		req.setAttribute("listType", listType);
		
		return "/WEB-INF/view/DocList.jsp";
	}

}
