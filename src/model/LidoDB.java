package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LidoDB {

	public static ResultSet getLido(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM location";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getLidoActive(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM location WHERE Disabled=0";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getLocation(String idLocation){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM location WHERE idlocation=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idLocation);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	
	
	public static ResultSet getLocationFree(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM location WHERE Busy=0";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getLocationBusy(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM location WHERE Busy=1";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static int setLocationBusy(String idLocation){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE location SET Busy = 1 WHERE (idlocation = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idLocation);
			ps.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static int setLocationFree(String idLocation){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE location SET Busy = 0 WHERE (idlocation = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idLocation);
			ps.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static int setLocationStatus(String idLocation, String status) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE location SET Disabled = ? WHERE (idlocation = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, status);
			ps.setString(2, idLocation);
			ps.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static int getPrice(String idLocation){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query
				= "SELECT Price FROM location WHERE idlocation=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idLocation);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt("Price");
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static int insertPrenotation(String idLocation,int user_id, String idPrenotation) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE location SET Busy = ? WHERE (idlocation = ?);"
				+ " INSERT INTO prenotation (user_id, location_idlocation,idPrenotation)"
				+ " VALUES (?,?,?)"
				;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setString(2, idLocation);
			ps.setInt(3, user_id);
			ps.setString(4, idLocation);
			ps.setString(5, idPrenotation);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static int setPrenotationOut(String idPrenotation , String idLocation) {
			ConnectionPool pool = ConnectionPool.getIstance();
			Connection connection = pool.getConnection();
			PreparedStatement ps = null;
			String query
					= "SET SQL_SAFE_UPDATES = 0;"
					+ "UPDATE guida.prenotation SET prenotation.Out = 1 WHERE (idPrenotation = ? and location_idlocation = ?);"
					+ "SET SQL_SAFE_UPDATES = 1;";
			try {
				ps = connection.prepareStatement(query);
				ps.setString(1, idPrenotation);
				ps.setString(2, idLocation);
				ps.execute();
				return 1;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				pool.freeConnection(connection);
			}	
	}
	
	public static ResultSet getPrenotationUser(int user_id) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT idPrenotation, time, prenotation.Out FROM guida.prenotation where user_id=? group by idPrenotation";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, user_id);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getPrenotations() {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM guida.prenotation WHERE prenotation.Out = '0' order by idPrenotation;";
		try {
			ps = connection.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getLocationUser(String idPrenotation) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT location_idlocation FROM guida.prenotation where idPrenotation=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idPrenotation);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}

	}
}
