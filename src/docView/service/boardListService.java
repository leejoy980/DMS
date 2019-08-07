package docView.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Dao.boardDao;
import VO.boardListVo;
import jdbc.connection.ConnectionProvider;

public class boardListService {

	private boardDao boardDao = new boardDao();
	private int size = 15;
	
	public boardListPageService getBoardListPage(int pageNum,String boardType){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = boardDao.totalDoc(conn, boardType);
			
			List<boardListVo> content = boardDao.selectByBoardList(conn, boardType, (pageNum-1)*size,size);
			
			return new boardListPageService(total,pageNum,size,content);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public boardListPageService getDoc_idSearchPage(int pageNum,String boardType,int searchDoc_id){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = 1;
			
			List<boardListVo> content = boardDao.selectByDoc_id(conn, boardType, (pageNum-1)*size,size,searchDoc_id);
			return new boardListPageService(total,pageNum,size,content);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public boardListPageService getTitleSearchPage(int pageNum,String boardType,String searchTitle){
		try(Connection conn = ConnectionProvider.getConnection()){
			searchTitle = "%"+searchTitle +"%";
			int total = boardDao.totalSearchTitle(conn,boardType,searchTitle);
			
			List<boardListVo> content = boardDao.selectByTitle(conn, boardType, (pageNum-1)*size,size,searchTitle);
			
			return new boardListPageService(total,pageNum,size,content);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}		
		
		
		
}
