package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.LineItem;
import model.beans.Order;

public class OrderDB {

	public static int insertOrder(Order order) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "INSERT INTO guida.order (order_id , user_id, OrderPay, Instruction, Total, Delivered) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, order.getOrder_id());
			ps.setInt(2, order.getUser_id());
			ps.setInt(3, order.getOrderPay());
			ps.setString(4, order.getInstruction());
			ps.setDouble(5, order.getTotal());
			ps.setInt(6, order.getDelivered());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		
	}
	
	public static int insertProduct(String orderID, ArrayList<LineItem> li) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "INSERT INTO order_product (product_Code, order_order_id, Quantity) "
				+ "VALUES (?, ?, ?)";
		try {
			for(LineItem item : li) {
				ps = connection.prepareStatement(query);
				ps.setInt(1, item.getProduct().getProduct_code());
				ps.setString(2, orderID);
				ps.setInt(3, item.getQuantity());
				ps.executeUpdate();
			}
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static int getCount() {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs=null;
		String query
				= "SELECT count('order_id') FROM guida.order";
		try {
				ps = connection.prepareStatement(query);
				rs = ps.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
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
	
	public static ResultSet getOrderPay(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM guida.order WHERE OrderPay=1 and Delivered=0 ORDER BY time ASC";
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
	
	public static ResultSet getOrderPrenotation(){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM guida.order WHERE OrderPay=0 ORDER BY time ASC";
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
	
	public static int setDelivered(String idOrder) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE guida.order SET Delivered = '1' WHERE order_id = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idOrder);
			ps.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static int setPay(String idOrder) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "UPDATE guida.order SET OrderPay = '1' WHERE order_id = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idOrder);
			ps.execute();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}
	
	public static ResultSet getProduct(String idOrder){
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM order_product, product, guida.order WHERE product_Code=Code and order_order_id=? and order_id=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idOrder);
			ps.setString(2, idOrder);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
	public static boolean removeOrder(String idOrder) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SET FOREIGN_KEY_CHECKS=0;"+
				" DELETE order_product, guida.order FROM order_product INNER JOIN guida.order ON guida.order.order_id=order_product.order_order_id WHERE order_product.order_order_id=?;"+
				" SET FOREIGN_KEY_CHECKS=1;";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, idOrder);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		
	}
	
	public static ResultSet getOrderUser(int idUser) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query
				= "SELECT * FROM guida.order WHERE user_id=?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, idUser);
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			pool.freeConnection(connection);
		}
	}
	
}
