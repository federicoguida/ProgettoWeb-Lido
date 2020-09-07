package controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDB;

@WebServlet("/view/personalarea/user/restaurant")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("products", ProductDB.getProducts());
		ServletContext sc = getServletContext();
		String url="/WEB-INF/view/personalarea/user/restaurant/restaurant.jsp";
		sc.getRequestDispatcher(url).forward(request, response);
	}

}
