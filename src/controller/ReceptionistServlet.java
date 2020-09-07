package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.JsonUtility;
import model.LidoDB;
import model.UserDB;
import model.beans.User;


@WebServlet("/view/personalarea/receptionist/")
public class ReceptionistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null) {
			if(action.equals("userData")) {
				User logged = UserDB.selectUser(request.getUserPrincipal().getName());
				JSONObject obj = new JSONObject();
				obj.append("email", logged.getEmail());
				obj.append("name", logged.getName());
				obj.append("surname", logged.getSurname());
				obj.append("age", logged.getAge());
				obj.append("time", logged.getTime());
				JsonUtility.responseJson(obj, response);
			}else if(action.equals("getPrenotation")) {
				ResultSet rs = LidoDB.getPrenotations();
				JsonUtility.responseJson(rs, response);
			}else {
				ResultSet prenotation = LidoDB.getPrenotations();
				ResultSet busy = LidoDB.getLocationBusy();
				JSONObject jsonObj = new JSONObject();
				ArrayList<String> locP = new ArrayList<>();
				try {
					while(prenotation.next()) {
						locP.add(prenotation.getString("location_idlocation"));
					}
					while(busy.next()) {
						String idlocation = busy.getString("idlocation");
						if(locP.contains(idlocation)==false) {
							jsonObj.append("location", idlocation);
						}
					}
					JsonUtility.responseJson(jsonObj, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/view/personalarea/receptionist/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("done")) {
			String idPrenotation = request.getParameter("idprenotation");
			String idLocation = request.getParameter("idlocation");
			LidoDB.setPrenotationOut(idPrenotation, idLocation);
			LidoDB.setLocationFree(idLocation);
		}else if(action.equals("out")) {
			String idLocation = request.getParameter("idlocation");
			LidoDB.setLocationFree(idLocation);
		}
	}

}
