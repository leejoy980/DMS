package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import VO.memberVo;
import jdbc.JdbcUtil;

public class memberDao {
	
	
	public List<String> groupMember(Connection conn,String groupType) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select user_id from member where grade=? or classType=? or groupType=? or club=?");
			pstmt.setString(1, groupType);
			pstmt.setString(2, groupType);
			pstmt.setString(3, groupType);
			pstmt.setString(4, groupType);
			
			rs = pstmt.executeQuery();
		
			List<String> groupMemberId = new ArrayList<>();
			while(rs.next()){
				groupMemberId.add(rs.getString(1));
			}
			return groupMemberId;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public List<String> allMember(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select user_id from member");
			rs = pstmt.executeQuery();
		
			List<String> groupMemberId = new ArrayList<>();
			while(rs.next()){
				groupMemberId.add(rs.getString(1));
			}
			return groupMemberId;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		
		
		
	}
	
	
	
	public memberVo selectById(Connection conn,String user_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select * from member where user_id=?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			memberVo memberVo = null;
			if(rs.next()){
				memberVo = new memberVo(
						rs.getString("user_id"),
						rs.getString("pwd"),
						rs.getString("groupType"),
						rs.getString("pos"),
						rs.getString("classType"),
						rs.getString("club"),
						rs.getString("grade"),
						rs.getString("user_name"));
			}
			return memberVo;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	public String selectByName(Connection conn,String user_id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement(
					"select user_name from member where user_id=?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString("user_name");
			}
			return "x";
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
