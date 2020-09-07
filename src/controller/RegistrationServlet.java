package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.User;
import model.EmailService;
import model.UserDB;

@WebServlet("/view/home/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRemoteUser()==null) {
			ServletContext sc = getServletContext();
			String url="/WEB-INF/view/home/registration.jsp";
			sc.getRequestDispatcher(url).forward(request,response);
		}else {
			String url=request.getContextPath()+"/view/personalarea/";
			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("Email");
			String name=request.getParameter("Name");
			String surname=request.getParameter("Surname");
			int age=Integer.parseInt(request.getParameter("Age"));
			String password=request.getParameter("Password");
			String role="user";
			ServletContext sc = getServletContext();
			if(UserDB.emailExist(email)) {
				String message="Account già registrato!";
				sc.setAttribute("message", message);
			}else {
				User user = new User();
				user.setEmail(email);
				user.setName(name);
				user.setSurname(surname);
				user.setAge(age);
				user.setPassword(password);
				user.setRole(role);
				UserDB.insert(user);
				String subject= "Benvenuto nel sito LIDO VETRANA!";
				String body="<p><strong>Siamo felici di averti nella nostra famiglia.</strong><br>"
					+ "<a href=\"http://localhost:8080/guidafederico/view/personalarea/\" >Clicca qui</a> per effettuare subito il login per usufruire dei nostri servizi!</p>";
				new Thread() {
		            public void run() {
		            	EmailService.sendMail(email, subject, body, true, sc);
		            }
		        }.start();
		        String message="Account registrato con successo! Effettua ora il login!";
				sc.setAttribute("message", message);
				sc.setAttribute("email", user.getEmail());
			}
		}catch(NumberFormatException e) {
			getServletContext().log(e.toString());
		}finally{
			response.sendRedirect(request.getContextPath()+"/view/personalarea/");
		}
	}
}
