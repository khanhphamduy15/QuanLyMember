package sms.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.MemberObject;
import util.ConnectionPool;
import util.ConnectionPoolImpl;

public class MemberFunctionImpl implements MemberFunction {
	
	//doi tuong ket noi de lam viec
	private Connection con;
	public MemberFunctionImpl(ConnectionPool cp) {
		if(cp == null) {
			cp = new ConnectionPoolImpl();
		}
		//xin ket noi
		try {
			this.con = cp.getConnection("user");
			//kiem tra che do thuc thi tu dong
			if(this.con.getAutoCommit()) {
				this.con.setAutoCommit(false);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean exe(PreparedStatement pre) {
		//pre  - đã được biên dịch và truyền giá trị đầy đủ cho các tham số
		if(pre!=null) {
			try {
				int results = pre.executeUpdate();
				if(results==0) {
					this.con.rollback();
					return false;
				}
				
				//xác nhận thực thi sau cùng 
				this.con.commit();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				
				try {
					this.con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}finally{
				pre = null;
			}
		}
		return false;
	}
		
	@Override
	public boolean addMember(MemberObject item) {
		
//		if(this.isExisting(item)) {
//			return false;
//		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO members (");
		sql.append("member_id, member_name, member_address, member_phone,  member_email) ");
		sql.append("VALUES (?, ?, ?, ?, ?);");


		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1,item.getMember_id());
			pre.setString(2,item.getMember_name());
			pre.setString(3,item.getMember_address());
			pre.setString(4,item.getMember_phone());
			pre.setString(5,item.getMember_email());

			
			return this.exe(pre);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	private boolean isExisting(MemberObject item) {
		boolean flag= false;
		
		String sql = "SELECT member_id FROM members WHERE member_name=?;";
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getMember_name());
			
			ResultSet rs = pre.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					flag = true;
				}
				rs.close();
			}
		} catch (SQLException e) {		
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return flag;
	}
	@Override
	public boolean editMember(MemberObject item) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE members SET ");
		sql.append("member_name=? ,member_address=?, member_phone=?, member_email=? ");
		sql.append("WHERE member_id = ?;");

		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setString(1,item.getMember_name());
			pre.setString(2,item.getMember_address());
			pre.setString(3,item.getMember_phone());
			pre.setString(4,item.getMember_email());
			pre.setInt(5,item.getMember_id());

			
			return this.exe(pre);
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delMember(MemberObject item) {
		
//		if(this.isEmpty(item)) {
//			return false;
//		}
		
		String sql = "DELETE FROM members WHERE member_id=?;";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setInt(1, item.getMember_id());
		return this.exe(pre);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	private boolean isEmpty(MemberObject item) {
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT member_name FROM members WHERE member_id = ?;");


		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			pre.setInt(1, item.getMember_id());
			pre.setString(2, item.getMember_name());
			pre.setString(3, item.getMember_address());
			pre.setString(4, item.getMember_phone());
			pre.setString(5, item.getMember_email());
			
			boolean results = pre.execute();
			do {
				if(results) {
					ResultSet rs = pre.getResultSet();
					if((rs!=null) && (rs.next())) {
						flag=false;
						break;
					}
					results = pre.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT);
				}
			} while(results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public MemberObject getMember(int id) {
		String sql = "SELECT * FROM members WHERE member_id=?";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		
		pre.setInt(1, id);
		
		MemberObject item = null;
		ResultSet rs = pre.executeQuery();
		if(rs != null) {
			if(rs.next()) {
				item = new MemberObject();
				item.setMember_id(rs.getInt("member_id"));
				item.setMember_name(rs.getString("member_name"));

			}
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberObject getMember(String membername, String memberpass) {
		String sql = "SELECT * FROM members WHERE (member_name=?) AND (member_pass=md5(?))";
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		pre.setString(1,membername);
		pre.setString(2, memberpass);
		
		MemberObject item = null;
		ResultSet rs = pre.executeQuery();
		if(rs != null) {
			if(rs.next()) {
				item = new MemberObject();
				item.setMember_id(rs.getInt("member_id"));
				item.setMember_name(rs.getString("member_name"));
				item.setMember_address(rs.getString("member_address"));
				item.setMember_phone(rs.getString("member_phone"));
				item.setMember_email(rs.getString("member_email"));
			}
		}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<MemberObject> getMembers(MemberObject similar, int at, byte total) {
		String sql = "SELECT * FROM members ";
		sql += "";
		sql += "ORDER BY member_id DESC ";
		sql += "LIMIT "+at+","+total;
		//bien dich cau lenh
		ArrayList<MemberObject> list = new ArrayList<>();
		MemberObject item = null;
		try {
		PreparedStatement pre = this.con.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				item = new MemberObject();
				item.setMember_id(rs.getInt("member_id"));
				item.setMember_name(rs.getString("member_name"));
				item.setMember_address(rs.getString("member_address"));
				item.setMember_phone(rs.getString("member_phone"));
				item.setMember_email(rs.getString("member_email"));
				list.add(item);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			this.con.rollback();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
