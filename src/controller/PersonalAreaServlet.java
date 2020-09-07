package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmailService;
import model.UserDB;
import model.beans.User;

@WebServlet({"/view/personalarea/", "/view/personalarea/data"})
public class PersonalAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		if(request.isUserInRole("admin")) {
			String url="/WEB-INF/view/personalarea/admin/";
			sc.getRequestDispatcher(url).forward(request, response);
		}else if(request.isUserInRole("user")) {
			String url="/WEB-INF/view/personalarea/user/";	
			sc.getRequestDispatcher(url).forward(request, response);
		}else if(request.isUserInRole("cook")){
			String url="/WEB-INF/view/personalarea/kitchen/";	
			sc.getRequestDispatcher(url).forward(request, response);
		} else if(request.isUserInRole("cashier")){
			String url="/WEB-INF/view/personalarea/cashier/";	
			sc.getRequestDispatcher(url).forward(request, response);
		}else {
			String url="/WEB-INF/view/personalarea/receptionist/";	
			sc.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null) {
			if(action.equals("password")) {
				String email = request.getParameter("email");
				ServletContext sc = request.getServletContext();
				sc.setAttribute("message", "Se l'utente è presente nei nostri sistemi sarà inviata un email!");
				User user = UserDB.selectUser(email);
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(email, "Password smarrita"
			            			, "In seguito alla sua richiesta le ricordiamo la sua password! Password:  "+user.getPassword(),
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
			}
		}else {
			doGet(request,response);
		}
	}

}
