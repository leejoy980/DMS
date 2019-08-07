package docControl.handler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.personalDao;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class holdDocHandler implements CommandHandler {
	private personalDao personalDao = new personalDao();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String user_id = req.getParameter("user_id");
		String noVal = req.getParameter("doc_id");
		String page = req.getParameter("page");
		String listType = req.getParameter("listType");
		int doc_id = Integer.parseInt(noVal);
		
		try(Connection conn = ConnectionProvider.getConnection()){
			personalDao.listTypeChange(conn, "newList", user_id, doc_id);
			personalDao.listhold(conn, user_id, doc_id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return "docList.do?page="+page+"&listType="+listType+"&user_id="+user_id;
	}

}
