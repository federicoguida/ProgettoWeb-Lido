package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.beans.User;
import model.EmailService;
import model.JsonUtility;
import model.UserDB;

@WebServlet("/view/personalarea/update")
public class UpdateUserServlet extends HttpServlet {
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
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("email")) {
			PrintWriter out = response.getWriter();
			response.setContentType("plain/text");
			response.setCharacterEncoding("UTF-8");
			String email = request.getParameter("email");
			if(request.getUserPrincipal().getName().equals(email)) {
				out.print("false");
				out.flush();
			}else if(UserDB.emailExist(email)) {
			    out.print("false");
				out.flush();
			}else {
				User user = UserDB.selectUser(request.getUserPrincipal().getName());
				UserDB.updateEmail(email, user.getId());
				out.print("true");
				ServletContext sc = request.getServletContext();
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(email, "Email aggiornata con successo!", "Ti informiamo che la tua email è stata cambiata con successo!"
			            			+ " Adesso riceverai le notifiche su questa nuova email!",
									false, sc);
			            	EmailService.sendMail(user.getEmail(), "Email aggiornata con successo!", "Ti informiamo che la tua email è stata cambiata con successo!",
									false, sc);
		            	}catch(NullPointerException e) {
		            		sc.log(
		            				"Unable to send email. \n"
		            			    + "Here is the email you tried to send: \n"
		            				+ "=======================================\n"
		            			    + "TO: " + email + "\n"
		            			    + "FROM: " + sc.getInitParameter("user") + "\n");
		            	}
		            }
		        }.start();
				HttpSession session=request.getSession();
				session.invalidate();
				out.flush();
			}
		}else if(action.equals("password")) {
			String password=request.getParameter("password");
			User user = UserDB.selectUser(request.getUserPrincipal().getName());
			PrintWriter out = response.getWriter();
			response.setContentType("plain/text");
			response.setCharacterEncoding("UTF-8");
			if(user.getPassword().equals(password)) {
				out.print("false");
				out.flush();
			}
			else {
				UserDB.updatePassword(password, user.getId());
				ServletContext sc = request.getServletContext();
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(user.getEmail(), "Password aggiornata con successo!", "Ti informiamo che la tua password è stata cambiata con successo!",
									false, sc);
		            	}catch(NullPointerException e) {
		            		sc.log(
		            				"Unable to send email. \n"
		            			    + "Here is the email you tried to send: \n"
		            				+ "=======================================\n"
		            			    + "TO: " + user.getEmail() + "\n"
		            			    + "FROM: " + sc.getInitParameter("user") + "\n");
		            	}
		            }
		        }.start();
				out.print("true");
				HttpSession session=request.getSession();
				session.invalidate();
				out.flush();
			}
		}
		
	}

}
