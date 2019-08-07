package docWrite.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import Dao.boardDao;
import Dao.documentDao;
import Dao.memberDao;
import Dao.personalDao;
import VO.detailDocVo;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class docWriteService {
	private documentDao documentDao = new documentDao();
	private personalDao personalDao = new personalDao();
	private memberDao memberDao = new memberDao();
	private boardDao boardDao = new boardDao();
	private List<String> groupMember;
	
	public void write(detailDocVo detailDocVo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			detailDocVo.setDoc_id(documentDao.insert(conn,detailDocVo));
			personalDao.receiveinsert(conn, detailDocVo);
			personalDao.sendinsert(conn, detailDocVo);
			
			conn.commit();
			
		
		}catch (SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public void groupWrite(detailDocVo detailDocVo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			detailDocVo.setDoc_id(documentDao.insert(conn,detailDocVo));
			personalDao.sendinsert(conn, detailDocVo);
			boardDao.insert(conn, detailDocVo);
			groupMember = memberDao.groupMember(conn, detailDocVo.getDes_1());
			
			
			Iterator<String> iterator = groupMember.iterator();
			while(iterator.hasNext()){
				detailDocVo.setDes_1((String) iterator.next());
				if(!detailDocVo.getDes_1().equals(detailDocVo.getGen_user_id())){
					personalDao.receiveinsert(conn, detailDocVo);
				}
			}
			
			
			conn.commit();
			
		
		}catch (SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public void answerWrite(detailDocVo detailDocVo,int OrDoc_id){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			String answer = boardDao.answerState(conn, OrDoc_id);
			
			if(answer.equals("Or")){
				String str = "-->";
				detailDocVo.setTitle(str.concat(detailDocVo.getTitle()));
			}
			if(answer.equals("Re")){
				String str = "---->";
				detailDocVo.setTitle(str.concat(detailDocVo.getTitle()));
			}
			detailDocVo.setDoc_id(documentDao.insert(conn,detailDocVo));
			personalDao.sendinsert(conn, detailDocVo);
			if(answer.equals("Or")){
				boardDao.Reinsert(conn, detailDocVo,OrDoc_id);
			}
			if(answer.equals("Re")){
				boardDao.ReReinsert(conn, detailDocVo,OrDoc_id);
			}
			groupMember = memberDao.groupMember(conn, detailDocVo.getDes_1());
			
			
			Iterator<String> iterator = groupMember.iterator();
			while(iterator.hasNext()){
				detailDocVo.setDes_1((String) iterator.next());
				if(!detailDocVo.getDes_1().equals(detailDocVo.getGen_user_id())){
					personalDao.receiveinsert(conn, detailDocVo);
				}
			}
			
			
			conn.commit();
			
		
		}catch (SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public void allWrite(detailDocVo detailDocVo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			detailDocVo.setDoc_id(documentDao.insert(conn,detailDocVo));
			personalDao.sendinsert(conn, detailDocVo);
			boardDao.insert(conn, detailDocVo);
			groupMember = memberDao.allMember(conn);
			
			
			Iterator<String> iterator = groupMember.iterator();
			while(iterator.hasNext()){
				detailDocVo.setDes_1((String) iterator.next());
				if(!detailDocVo.getDes_1().equals(detailDocVo.getGen_user_id())){
					personalDao.receiveinsert(conn, detailDocVo);
				}
			}
			
			
			conn.commit();
			
		
		}catch (SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch (RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
}
