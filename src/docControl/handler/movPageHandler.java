package docControl.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class movPageHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		String doc_Type = req.getParameter("doc_Type");
		String boardType = req.getParameter("boardType");
		String OrDoc_id = req.getParameter("doc_id");
		String answer = req.getParameter("answer");
		String modify = req.getParameter("modify");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String attached = req.getParameter("attached");
		String page = req.getParameter("page");
		
		
		if(doc_Type.equals("oneDoc")){
			req.setAttribute("modify",modify);
			req.setAttribute("doc_id", OrDoc_id);
			req.setAttribute("title", title);
			req.setAttribute("content", content);
			req.setAttribute("attached", attached);
			return "/WEB-INF/view/oneDocWrite.jsp";
		} else if(doc_Type.equals("stepDoc")){
			return "/WEB-INF/view/stepDocWrite.jsp";
		} else if(doc_Type.equals("groupDoc")){
			return "/WEB-INF/view/groupDocWrite.jsp";
		} else if(doc_Type.equals("allDoc")){
			return "/WEB-INF/view/allDocWrite.jsp";
		} else if(doc_Type.equals("answerDoc")){
			req.setAttribute("boardType", boardType);
			req.setAttribute("OrDoc_id", OrDoc_id);
			req.setAttribute("answer", answer);
			req.setAttribute("page", page);
			return "/WEB-INF/view/answerDocWrite.jsp";
		} else if(doc_Type.equals("modifyDoc")){
			req.setAttribute("title", title);
			req.setAttribute("content", content);
			req.setAttribute("attached", attached);
			return "/WEB-INF/view/groupModify.jsp";
		}
		return "/WEB-INF/view/DocList.jsp";
	}

}
