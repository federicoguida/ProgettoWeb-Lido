package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/view/personalarea/admin/job/", "/view/personalarea/kitchen/job/", "/view/personalarea/cashier/job/", "/view/personalarea/receptionist/job/"})
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		if(request.isUserInRole("admin")) {
			String url="/WEB-INF/view/personalarea/admin/job/";
			sc.getRequestDispatcher(url).forward(request, response);
		}else if(request.isUserInRole("cook")){
			String url="/WEB-INF/view/personalarea/kitchen/job/";	
			sc.getRequestDispatcher(url).forward(request, response);
		} else if(request.isUserInRole("cashier")){
			String url="/WEB-INF/view/personalarea/cashier/job/";	
			sc.getRequestDispatcher(url).forward(request, response);
		}else {
			String url="/WEB-INF/view/personalarea/receptionist/job/";	
			sc.getRequestDispatcher(url).forward(request, response);
		}
	}

}
