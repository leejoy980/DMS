package docView.service;

import java.sql.Connection;
import java.sql.SQLException;

import Dao.documentDao;
import Dao.memberDao;
import Dao.personalDao;
import VO.detailDocVo;
import jdbc.connection.ConnectionProvider;

public class docReadService {
	
	private documentDao documentDao = new documentDao();
	private memberDao memberDao = new memberDao();
	private personalDao personalDao = new personalDao();
	
	
	
	public detailDocVo getDoc(int doc_id,String listType,String user_id){
		try(Connection conn = ConnectionProvider.getConnection()){
		detailDocVo detailDocVo = documentDao.read(conn, doc_id);
		detailDocVo.setDoc_id(doc_id);
		detailDocVo.setGen_user_name(memberDao.selectByName(conn, detailDocVo.getGen_user_id()));
		detailDocVo.setDes_1_name(memberDao.selectByName(conn, detailDocVo.getDes_1()));
		detailDocVo.setState(personalDao.getState(conn, user_id, doc_id));
		if(listType.equals("newList")){
			personalDao.listTypeChange(conn, "receiveList", user_id, doc_id);
		}
		
		return detailDocVo;
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
