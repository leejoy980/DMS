package docView.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Dao.personalDao;
import VO.docListVo;
import jdbc.connection.ConnectionProvider;

public class docListService {
		
	private personalDao personalDao = new personalDao();
	private int size = 15;
	
	public docListPageService getDocListPage(int pageNum,String user_id,String listType){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = personalDao.totalDoc(conn, user_id, listType);
			
			List<docListVo> content = personalDao.selectByDocList(conn, user_id, listType, (pageNum-1)*size,size);
			
			return new docListPageService(total,pageNum,size,content);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public docListPageService getDoc_idSearchPage(int pageNum,String user_id,String listType,int searchDoc_id){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = 1;
			
			List<docListVo> content = personalDao.selectByDoc_id(conn, user_id, listType, (pageNum-1)*size,size,searchDoc_id);
			return new docListPageService(total,pageNum,size,content);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public docListPageService getTitleSearchPage(int pageNum,String user_id,String listType,String searchTitle){
		try(Connection conn = ConnectionProvider.getConnection()){
			searchTitle = "%"+searchTitle +"%";
			int total = personalDao.totalSearchTitle(conn, user_id, listType,searchTitle);
			
			List<docListVo> content = personalDao.selectByTitle(conn, user_id, listType, (pageNum-1)*size,size,searchTitle);
			
			return new docListPageService(total,pageNum,size,content);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}		
}
