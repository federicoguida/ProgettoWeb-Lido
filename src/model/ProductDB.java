package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.beans.Product;

public class ProductDB {

	public static ArrayList<Product> getProducts() {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Product> pr=new ArrayList<>();
		String query
				= "SELECT * FROM product order by Category";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setName(rs.getString("Name"));
				product.setDescription(rs.getString("Description"));
				product.setProduct_code(rs.getInt("Code"));
				product.setPrice(rs.getDouble("Price"));
				product.setCategory(rs.getString("Category"));
				product.setImgURL(rs.getString("imgURL"));
				pr.add(product);
			}
			return pr;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}


	public static Product getProduct(int code) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product pr=new Product();
		String query
		= "SELECT * FROM product "
		+ "WHERE Code = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1,String.valueOf(code));
			rs=ps.executeQuery();
			rs.next();
			pr.setProduct_code(rs.getInt("Code"));
			pr.setName(rs.getString("Name"));
			pr.setDescription(rs.getString("Description"));
			pr.setCategory(rs.getString("Category"));
			pr.setPrice(rs.getDouble("Price"));
			pr.setImgURL(rs.getString("imgURL"));
			return pr;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
	
	
	public static int setProduct(Product product) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "INSERT INTO product (Name, Description, Price, Category, imgURL) "
				+ "VALUES (?, ?, ?, ?, ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getCategory());
			ps.setString(5, product.getImgURL());
			ps.execute();
			return 1;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
	
	public static int removeProduct(String product_code) {
		ConnectionPool pool = ConnectionPool.getIstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query
				= "DELETE FROM product WHERE (Code = ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1,product_code);
			ps.execute();
			return 1;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}
	}
}
