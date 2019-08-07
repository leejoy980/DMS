package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import VO.boardListVo;
import VO.detailDocVo;
import jdbc.JdbcUtil;

public class boardDao {
	
	SimpleDateFormat sdfCurrent = new SimpleDateFormat ("MM-dd . HH:mm");
	private memberDao memberDao = new memberDao();
	
	
	public List<boardListVo> selectByDoc_id(Connection conn,String boardType,int startRow,int size,int searchDoc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from board inner join document where board.doc_id=document.doc_id and boardType=? and board.doc_id=? order by board.boardseq_id desc limit ?,?");
			pstmt.setString(1, boardType);
			pstmt.setInt(2, searchDoc_id);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
		
			List<boardListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new boardListVo(
						rs.getString("boardType"),
						rs.getInt("doc_id"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						memberDao.selectByName(conn, rs.getString("gen_user_id")),
						rs.getString("answer")));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<boardListVo> selectByTitle(Connection conn,String boardType,int startRow,int size,String searchTitle) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from board inner join document where board.doc_id=document.doc_id and boardType=? and document.title LIKE ? order by board.boardseq_id desc limit ?,?");
			pstmt.setString(1, boardType);
			pstmt.setString(2, searchTitle);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
		
			List<boardListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new boardListVo(
						rs.getString("boardType"),
						rs.getInt("doc_id"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						memberDao.selectByName(conn, rs.getString("gen_user_id")),
						rs.getString("answer")));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public int boardseqState(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select boardseq_id from board where doc_id=?");
			pstmt.setInt(1, doc_id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				return rs.getInt(1);
			}
			
			return 999999999;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	public String answerState(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select answer from board where doc_id=?");
			pstmt.setInt(1, doc_id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()){
				return rs.getString(1);
			}
			
			return "Or";
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	public int delete(Connection conn,int doc_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("delete from board where doc_id=?");
			pstmt.setInt(1, doc_id);
			
			int count = pstmt.executeUpdate();
			return count;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
	public void insert(Connection conn,detailDocVo detailDocVo)throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement("insert into board" + "(boardType,boardseq_id,doc_id,read_count,answer)"
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, detailDocVo.getDes_1());
		pstmt.setInt(2, (detailDocVo.getDoc_id()*1000000)+999999);
		pstmt.setInt(3, detailDocVo.getDoc_id());
		pstmt.setInt(4, 0);
		pstmt.setString(5, "Or");
		
		pstmt.executeUpdate();
		
		}finally{
		JdbcUtil.close(pstmt);
		}
		
	}
	
	public void Reinsert(Connection conn,detailDocVo detailDocVo,int OrDoc_id)throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement("insert into board" + "(boardType,boardseq_id,doc_id,read_count,answer)"
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, detailDocVo.getDes_1());
		pstmt.setInt(2, (OrDoc_id*1000000)+((999-detailDocVo.getDoc_id())*1000)+999);
		pstmt.setInt(3, detailDocVo.getDoc_id());
		pstmt.setInt(4, 0);
		pstmt.setString(5, "Re");
		
		pstmt.executeUpdate();
		
		}finally{
		JdbcUtil.close(pstmt);
		}
		
	}
	
	public void ReReinsert(Connection conn,detailDocVo detailDocVo,int OrDoc_id)throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
		pstmt = conn.prepareStatement("insert into board" + "(boardType,boardseq_id,doc_id,read_count,answer)"
				+ "values(?,?,?,?,?)");
		pstmt.setString(1, detailDocVo.getDes_1());
		pstmt.setInt(2, boardseqState(conn,OrDoc_id)-detailDocVo.getDoc_id());
		pstmt.setInt(3, detailDocVo.getDoc_id());
		pstmt.setInt(4, 0);
		pstmt.setString(5, "ReRe");
		
		pstmt.executeUpdate();
		
		}finally{
		JdbcUtil.close(pstmt);
		}
		
	}
	
	public int totalDoc(Connection conn,String boardType) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select count(*) from board where boardType=?");
			pstmt.setString(1, boardType);
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
	
public int totalSearchTitle(Connection conn,String boardType,String searchTitle) throws SQLException{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select count(*) from board inner join document where board.doc_id=document.doc_id and board.boardType=? and document.title=?");
			pstmt.setString(1, boardType);
			pstmt.setString(2, searchTitle);
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
	
	public List<boardListVo> selectByBoardList(Connection conn,String boardType,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from board inner join document where board.doc_id=document.doc_id and boardType=? order by board.boardseq_id desc limit ?,?");
			pstmt.setString(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
		
			List<boardListVo> result = new ArrayList<>();
			while(rs.next()){
				result.add(new boardListVo(
						rs.getString("boardType"),
						rs.getInt("doc_id"),
						rs.getString("title"),
						rs.getString("gen_user_id"),
						sdfCurrent.format(rs.getTimestamp("created_time")),
						memberDao.selectByName(conn, rs.getString("gen_user_id")),
						rs.getString("answer")));
			}
			return result;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
}
