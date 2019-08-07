package docControl.service;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.documentDao;
import Dao.personalDao;
import jdbc.connection.ConnectionProvider;

public class appDocService {
		private documentDao documentDao = new documentDao();
		private personalDao personalDao = new personalDao();	
		
	
	public int approvalDoc(int doc_id,String user_id,String state,String gen_user_id){
		try(Connection conn = ConnectionProvider.getConnection()){
			if(state.equals("first")){
				personalDao.user_idChange(conn, documentDao.findDes_2(conn, doc_id),"second", user_id, doc_id);
				personalDao.stateChange(conn, "second", gen_user_id, doc_id);
				personalDao.alarmP(conn, gen_user_id, documentDao.alarmD(conn, String.valueOf(doc_id)+"번 문서 1차 승인 완료", "admin", gen_user_id), "second");
				return 1;
			}else if(state.equals("second")){
				personalDao.user_idChange(conn, documentDao.findDes_3(conn, doc_id),"last", user_id, doc_id);
				personalDao.stateChange(conn, "last", gen_user_id, doc_id);
				personalDao.alarmP(conn, gen_user_id, documentDao.alarmD(conn, String.valueOf(doc_id)+"번 문서 2차 승인 완료", "admin", gen_user_id), "last");
				return 1;
			}else if(state.equals("last")){
				personalDao.listTypeChange(conn, "receiveList", user_id, doc_id);
				personalDao.stateChange(conn, "complete", documentDao.findDes_3(conn, doc_id), doc_id);
				personalDao.stateChange(conn, "complete", gen_user_id, doc_id);
				personalDao.alarmP(conn, gen_user_id, documentDao.alarmD(conn, String.valueOf(doc_id)+"번 문서 승인 완료", "admin", gen_user_id), "complete");
				return 1;
			}
			return 1;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	
	
}
