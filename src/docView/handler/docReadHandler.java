package docView.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VO.detailDocVo;
import VO.memberVo;
import docView.service.docReadService;
import mvc.command.CommandHandler;

public class docReadHandler implements CommandHandler {
	
	private docReadService docReadService = new docReadService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		String noVal = req.getParameter("doc_id");
		String page = req.getParameter("page");
		String listType = req.getParameter("listType");
		String board = req.getParameter("board");
		String boardType = req.getParameter("boardType");
		String answer = req.getParameter("answer");
		memberVo memberVo = (memberVo)req.getSession(false).getAttribute("memberVo");
		
		int doc_id = Integer.parseInt(noVal);
		
			detailDocVo detailDocVo = docReadService.getDoc(doc_id,listType,memberVo.getUser_id());
			req.setAttribute("detailDocVo", detailDocVo);
			req.setAttribute("page", page);
			req.setAttribute("listType", listType);
			req.setAttribute("board", board);
			req.setAttribute("boardType", boardType);
			req.setAttribute("answer", answer);
		
			if(detailDocVo.getDoc_Type().equals("oneDoc")){
				return "/WEB-INF/view/oneRead.jsp";
			} else if(detailDocVo.getDoc_Type().equals("stepDoc")){
				return "/WEB-INF/view/stepRead.jsp";
			} else if(detailDocVo.getDoc_Type().equals("groupDoc")){
				return "/WEB-INF/view/groupRead.jsp";
			} else if(detailDocVo.getDoc_Type().equals("answerDoc")){
				return "/WEB-INF/view/groupRead.jsp";
			}
		return "/WEB-INF/view/oneRead.jsp";
		
		
	}

}
