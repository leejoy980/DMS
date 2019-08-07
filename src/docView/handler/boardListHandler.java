package docView.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import docView.service.boardListPageService;
import docView.service.boardListService;
import mvc.command.CommandHandler;

public class boardListHandler implements CommandHandler {

	private boardListService listService = new boardListService();
	String a = "xxx";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		String pageNoVal = req.getParameter("page");
		String boardType = req.getParameter("boardType");
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
				boardListPageService boardListPageService = listService.getDoc_idSearchPage(pageNo,boardType,searchDoc_id);
				req.setAttribute("boardList", boardListPageService);
			} else if(searchType.equals("titleSearch")){
				boardListPageService boardListPageService = listService.getTitleSearchPage(pageNo,boardType,search);
				req.setAttribute("boardList", boardListPageService);
			}
		} else{
			boardListPageService boardListPageService = listService.getBoardListPage(pageNo,boardType);
			req.setAttribute("boardList", boardListPageService);
		}
		req.setAttribute("boardType", boardType);
		
		return "/WEB-INF/view/BoardList.jsp";
	}

}
