package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import VO.detailDocVo;
import VO.docListVo;
import jdbc.JdbcUtil;

public class personalDao {
	
	SimpleDateFormat sdfCurrent = new SimpleDateFormat ("MM-dd . HH:mm");
	private memberDao memberDao = new memberDao();
	
	
	public int receiveDelete(Connection conn,String user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("delete from personal where user_id=? and doc_id=?");
			pstmt.setString(1, user_id);
			pstmt.setInt(2, doc_id);
			
			int count = pstmt.executeUpdate();
			return count;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public int sendDelete(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("delete from personal where (listType=? or (listType=? and hold=?)) and doc_id=?");
			pstmt.setString(1, "sendList");
			pstmt.setString(2, "newList");
			pstmt.setString(3, "off");
			pstmt.setInt(4, doc_id);
			
			int count = pstmt.executeUpdate();
			return count;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public String getState(Connection conn,String user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select state from personal where user_id=? and doc_id=?");
			pstmt.setString(1, user_id);
			pstmt.setInt(2, doc_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
			return "first";
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public void listTypeChange(Connection conn,String listType,String user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("update personal set listType=? where user_id=? and doc_id=?");
			pstmt.setString(1, listType);
			pstmt.setString(2, user_id);
			pstmt.setInt(3, doc_id);
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void listhold(Connection conn,String user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("update personal set hold=? where user_id=? and doc_id=?");
			pstmt.setString(1, "on");
			pstmt.setString(2, user_id);
			pstmt.setInt(3, doc_id);
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void user_idChange(Connection conn,String Des,String state,String user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("update personal set user_id=?,listType=?,state=? where user_id=? and doc_id=?");
			pstmt.setString(1, Des);
			pstmt.setString(2, "newList");
			pstmt.setString(3, state);
			pstmt.setString(4, user_id);
			pstmt.setInt(5, doc_id);
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public void stateChange(Connection conn,String state,String gen_user_id,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("update personal set state=? where user_id=? and doc_id=?");
			pstmt.setString(1, state);
			pstmt.setString(2, gen_user_id);
			pstmt.setInt(3, doc_id);
			
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<docListVo> selectByDocList(Connection conn,String user_id,String listType,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from personal inner join document where personal.doc_id=document.doc_id and personal.user_id=? and personal.listType=? order by personal.doc_id desc limit ?,?");
			pstmt.setString(1, user_id);
			pstmt.setString(2, listType);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
		
			List<docListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new docListVo(
						rs.getString("user_id"),
						rs.getString("listType"),
						rs.getInt("doc_id"),
						rs.getString("state"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						rs.getString("doc_Type"),
						memberDao.selectByName(conn, rs.getString("gen_user_id"))));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<docListVo> selectByDoc_id(Connection conn,String user_id,String listType,int startRow,int size,int searchDoc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from personal inner join document where personal.doc_id=document.doc_id and personal.user_id=? and personal.listType=? and personal.doc_id=? order by personal.doc_id desc limit ?,?");
			pstmt.setString(1, user_id);
			pstmt.setString(2, listType);
			pstmt.setInt(3, searchDoc_id);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, size);
			rs = pstmt.executeQuery();
		
			List<docListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new docListVo(
						rs.getString("user_id"),
						rs.getString("listType"),
						rs.getInt("doc_id"),
						rs.getString("state"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						rs.getString("doc_Type"),
						memberDao.selectByName(conn, rs.getString("gen_user_id"))));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<docListVo> selectByTitle(Connection conn,String user_id,String listType,int startRow,int size,String searchTitle) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from personal inner join document where personal.doc_id=document.doc_id and personal.user_id=? and personal.listType=? and document.title LIKE ? order by personal.doc_id desc limit ?,?");
			pstmt.setString(1, user_id);
			pstmt.setString(2, listType);
			pstmt.setString(3, searchTitle);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, size);
			rs = pstmt.executeQuery();
		
			List<docListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new docListVo(
						rs.getString("user_id"),
						rs.getString("listType"),
						rs.getInt("doc_id"),
						rs.getString("state"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						rs.getString("doc_Type"),
						memberDao.selectByName(conn, rs.getString("gen_user_id"))));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public void sendinsert(Connection conn,detailDocVo detailDocVo)throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement("insert into personal" + "(user_id,listType,doc_id,state,hold)"
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, detailDocVo.getGen_user_id());
		pstmt.setString(2, "sendList");
		pstmt.setInt(3, detailDocVo.getDoc_id());
		pstmt.setString(4, detailDocVo.getState());
		pstmt.setString(5, "off");
		
		pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
			}
			
		}
	public void receiveinsert(Connection conn,detailDocVo detailDocVo)throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement("insert into personal" + "(user_id,listType,doc_id,state,hold)"
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, detailDocVo.getDes_1());
		pstmt.setString(2, "newList");
		pstmt.setInt(3, detailDocVo.getDoc_id());
		pstmt.setString(4, detailDocVo.getState());
		pstmt.setString(5, "off");
		
		pstmt.executeUpdate();
		
		}finally{
		JdbcUtil.close(pstmt);
		}
	}
	
	public void alarmP(Connection conn,String des_1,int doc_id,String state) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try{
			pstmt = conn.prepareStatement("insert into personal" + "(user_id,listType,doc_id,state,hold)"
					+ "values(?,?,?,?,?)");
			pstmt.setString(1, des_1);
			pstmt.setString(2, "newList");
			pstmt.setInt(3, doc_id);
			pstmt.setString(4, state);
			pstmt.setString(5, "off");
			
			pstmt.executeUpdate();
			

		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
	}
	
	public int totalDoc(Connection conn,String user_id,String listType) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select count(*) from personal where user_id=?and listType=?");
			pstmt.setString(1, user_id);
			pstmt.setString(2, listType);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()){
				return rs.getInt("count(*)");
			}
			
			return 0;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	

	public int totalSearchTitle(Connection conn,String user_id,String listType,String searchTitle) throws SQLException{
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try{
		pstmt = conn.prepareStatement(
				"select count(*) from personal inner join document where personal.doc_id=document.doc_id and personal.user_id=? and personal.listType=? and document.title LIKE ?");
		pstmt.setString(1, user_id);
		pstmt.setString(2, listType);
		pstmt.setString(3, searchTitle);
		
		rs = pstmt.executeQuery();
	
		if(rs.next()){
			return rs.getInt("count(*)");
		}
		
		return 0;
	}finally{
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);
	}
}

	
}
