package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.JsonUtility;
import model.LidoDB;
import model.OrderDB;
import model.UserDB;
import model.beans.User;


@WebServlet("/view/personalarea/cashier")
public class CashierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null) {
			if(action.equals("data")) {
				ResultSet rs = OrderDB.getOrderPrenotation();
				JsonUtility.responseJson(rs, response);
			}else if(action.equals("lido")) {
				ResultSet rs = LidoDB.getLocationFree();
				JsonUtility.responseJson(rs, response);
			}else if(action.equals("userData")) {
				User logged = UserDB.selectUser(request.getUserPrincipal().getName());
				JSONObject obj = new JSONObject();
				obj.append("email", logged.getEmail());
				obj.append("name", logged.getName());
				obj.append("surname", logged.getSurname());
				obj.append("age", logged.getAge());
				obj.append("time", logged.getTime());
				JsonUtility.responseJson(obj, response);
			}
		}else {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/personalarea/cashier/index.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("pay")) {
			OrderDB.setPay(request.getParameter("order_id"));
		}else if(action.equals("prenotation")) {
			LidoDB.setLocationBusy(request.getParameter("idlocation"));	
		}
	}

}
