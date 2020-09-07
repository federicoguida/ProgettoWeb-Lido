package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.beans.User;
import model.JsonUtility;
import model.LidoDB;
import model.OrderDB;
import model.UserDB;

@WebServlet({"/view/personalarea/user/management"})
public class UserAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("userData")) {
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
		String action = request.getParameter("action");
		if(action.equals("prenotation")) {
			User logged = UserDB.selectUser(request.getUserPrincipal().getName());
			ResultSet rsPrenotation = LidoDB.getPrenotationUser(logged.getId());
			JSONArray jsonArray = new JSONArray();
			try {
				while (rsPrenotation.next()) {
				    int columns = rsPrenotation.getMetaData().getColumnCount();
				    JSONObject obj = new JSONObject();
				    for (int i = 0; i < columns; i++)
				        obj.put(rsPrenotation.getMetaData().getColumnLabel(i + 1).toLowerCase(), rsPrenotation.getObject(i + 1));
				    ResultSet rsLocation = LidoDB.getLocationUser(rsPrenotation.getString("idPrenotation"));
			        while(rsLocation.next()) {
				        obj.append(rsLocation.getMetaData().getColumnLabel(1).toLowerCase(), rsLocation.getObject(1));
			        }
				    jsonArray.put(obj);
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JsonUtility.responseJson(jsonArray, response);
		}else if(action.equals("order")) {
			User logged = UserDB.selectUser(request.getUserPrincipal().getName());
			ResultSet rsOrder = OrderDB.getOrderUser(logged.getId());
			JsonUtility.responseJson(rsOrder, response);
		}else if(action.equals("info")) {
			ResultSet rsInfo = OrderDB.getProduct(request.getParameter("order_id"));
			JsonUtility.responseJson(rsInfo, response);
		}
	}
}

