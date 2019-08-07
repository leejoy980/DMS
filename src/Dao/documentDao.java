package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import VO.detailDocVo;
import jdbc.JdbcUtil;

public class documentDao {
	
	private int doc_id;
	private String des;
	
	public int modify(Connection conn,detailDocVo detailDocVo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("update document set title=?,content=?,attached=? where doc_id=?");
			pstmt.setString(1, detailDocVo.getTitle());
			pstmt.setString(2, detailDocVo.getContent());
			pstmt.setString(3, detailDocVo.getAttached());
			pstmt.setInt(4, detailDocVo.getDoc_id());
			
			return pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public int alarmD(Connection conn,String title,String gen_user_id,String des_1) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement stmt = null;

		
		try{
			pstmt = conn.prepareStatement("insert into document" + "(doc_Type,title,created_time,gen_user_id,des_1)"
					+ "values(?,?,?,?,?)");
			pstmt.setString(1, "oneDoc");
			pstmt.setString(2, title);
			pstmt.setTimestamp(3, toTimestamp());
			pstmt.setString(4, gen_user_id);
			pstmt.setString(5, des_1);
			
			pstmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select last_insert_id() from document");
			if(rs.next()){
				doc_id=rs.getInt(1);
			}
			return doc_id;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
		}
		
		
	}
	
	public int insert(Connection conn,detailDocVo detailDocVo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement stmt = null;

		
		try{
			pstmt = conn.prepareStatement("insert into document" + "(doc_Type,title,content,created_time,attached,gen_user_id,des_1,des_2,des_3)"
					+ "values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, detailDocVo.getDoc_Type());
			pstmt.setString(2, detailDocVo.getTitle());
			pstmt.setString(3, detailDocVo.getContent());
			pstmt.setTimestamp(4, toTimestamp());
			pstmt.setString(5, detailDocVo.getAttached());
			pstmt.setString(6, detailDocVo.getGen_user_id());
			pstmt.setString(7, detailDocVo.getDes_1());
			pstmt.setString(8, detailDocVo.getDes_2());
			pstmt.setString(9, detailDocVo.getDes_3());
			
			pstmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select last_insert_id() from document");
			if(rs.next()){
				doc_id=rs.getInt(1);
			}
			return doc_id;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
		}
		
		
	}
	
	public String findDes_1(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = conn.prepareStatement("select des_1 from document where doc_id=?");
			pstmt.setInt(1,doc_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				des=rs.getString(1);
			}
			return des;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public String findDes_2(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = conn.prepareStatement("select des_2 from document where doc_id=?");
			pstmt.setInt(1,doc_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				des=rs.getString(1);
			}
			return des;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public String findDes_3(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = conn.prepareStatement("select des_3 from document where doc_id=?");
			pstmt.setInt(1,doc_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				des=rs.getString(1);
			}
			return des;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public detailDocVo read(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement(
					"select * from document where doc_id = ?");
			pstmt.setInt(1,doc_id);
			rs = pstmt.executeQuery();
			
			detailDocVo detailDocVo = null;
			
			if(rs.next()){
				detailDocVo = new detailDocVo(rs.getString("doc_Type"),rs.getString("title"),rs.getString("content"),rs.getString("attached"),
						rs.getString("gen_user_id"),rs.getString("des_1"),rs.getString("des_2"),rs.getString("des_3"));
			}
			return detailDocVo;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(){
		
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
		
		return ts;

	}

}
