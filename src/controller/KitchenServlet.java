package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.JsonUtility;
import model.OrderDB;
import model.UserDB;
import model.beans.User;

import org.json.JSONObject;

@WebServlet("/view/personalarea/kitchen/Order")
public class KitchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("Order")) {
			ResultSet rs = OrderDB.getOrderPay();
			JsonUtility.responseJson(rs, response);
			try {
				DBUtil.closePreparedStatement(rs.getStatement());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.closeResultSet(rs);
		}else if(request.getParameter("action").equals("products")) {
			ResultSet rs = OrderDB.getProduct(request.getParameter("order_id"));
			JsonUtility.responseJson(rs, response);
		}else if(request.getParameter("action").equals("userData")) {
			User logged = UserDB.selectUser(request.getUserPrincipal().getName());
			JSONObject obj = new JSONObject();
			obj.append("email", logged.getEmail());
			obj.append("name", logged.getName());
			obj.append("surname", logged.getSurname());
			obj.append("age", logged.getAge());
			obj.append("time", logged.getTime());
			JsonUtility.responseJson(obj, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("delivered")) {
			OrderDB.setDelivered(request.getParameter("order_id"));
		}
	}

}
