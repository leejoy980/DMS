package docControl.handler;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.documentDao;
import Dao.personalDao;
import jdbc.connection.ConnectionProvider;
import mvc.command.CommandHandler;

public class returnDocHandler implements CommandHandler {
	private personalDao personalDao = new personalDao();
	private documentDao documentDao = new documentDao();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String user_id = req.getParameter("user_id");
		String noVal = req.getParameter("doc_id");
		String page = req.getParameter("page");
		String listType = req.getParameter("listType");
		String gen_user_id = req.getParameter("gen_user_id");
		int doc_id = Integer.parseInt(noVal);
		
		try(Connection conn = ConnectionProvider.getConnection()){
			personalDao.stateChange(conn, "return", gen_user_id, doc_id);
			personalDao.receiveDelete(conn, user_id, doc_id);
			personalDao.alarmP(conn, gen_user_id, documentDao.alarmD(conn, String.valueOf(doc_id)+"번 문서 반송", "admin", gen_user_id), "return");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return "docList.do?page="+page+"&listType="+listType+"&user_id="+user_id;
	}

}
