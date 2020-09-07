package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.beans.User;

public class UserDB{

	public static int insert(User user) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "INSERT INTO user (Email, Name, Surname, Age, Password, Role) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setInt(4, user.getAge());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getRole());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	
	public static User selectUser(String email) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		String query
				= "SELECT * FROM user "
				+ "WHERE Email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setName(rs.getString("Name"));
				user.setSurname(rs.getString("Surname"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAge(rs.getInt("Age"));
				user.setTime(rs.getString("Time"));
				user.setRole(rs.getString("Role"));
				user.setId(rs.getInt("id"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getUsers() {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
		= "SELECT * FROM user WHERE Role != 'user'";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			pool.freeConnection(connection);
		}
		
		
	}
	
	public static boolean emailExist(String email) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "SELECT * FROM user "
				+ "WHERE Email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
	
	public static int updateEmail(String email, int id) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "UPDATE guida.user SET Email = ? WHERE (id = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setInt(2, id);
			if(ps.execute())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
	
	public static int updatePassword(String password, int id) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "UPDATE guida.user SET Password = ? WHERE (id = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, password);
			ps.setInt(2, id);
			if(ps.execute())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}


	public static int updateUserRole(String email, String role) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "UPDATE guida.user SET Role = ? WHERE (email = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, role);
			ps.setString(2, email);
			if(ps.execute())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
		
	}


	public static int removeUser(String email) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "DELETE FROM guida.user WHERE (email = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			if(ps.execute())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
		
	}
	

}
