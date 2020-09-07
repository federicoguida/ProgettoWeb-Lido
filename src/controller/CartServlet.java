package controller;

import model.ProductDB;
import model.beans.Cart;
import model.beans.LineItem;
import model.beans.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({ "/view/personalarea/user/restaurant/add", "/view/personalarea/user/restaurant/remove" , "/view/personalarea/user/restaurant/cart", "/view/personalarea/user/restaurant/update" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		sc.getRequestDispatcher("/WEB-INF/view/personalarea/user/restaurant/cart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if(action.equals("add")) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
			try {
				int prcode = Integer.parseInt(request.getParameter("product_code"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				Product product=ProductDB.getProduct(prcode);
				LineItem lineItem = new LineItem();
				lineItem.setProduct(product);
				lineItem.setQuantity(quantity);
				cart.addItem(lineItem);
				response.setContentType("text/plain");
				out=response.getWriter();
				out.print(cart.getTotalPrice());
			}catch(NumberFormatException e) {
				getServletContext().log(e.toString());
				response.setContentType("text/plain");
				out=response.getWriter();
				out.print(cart.getTotalPrice());
			}
		}else if(action.compareTo("remove")==0) {
			try {
				int product_code=Integer.parseInt(request.getParameter("product_code"));
				Cart cart = (Cart) session.getAttribute("cart");
				cart.removeItem(product_code);
				out.print(cart.getTotalPrice());
			}catch(NumberFormatException e) {
				getServletContext().log(e.toString());
			}
		}else if(action.compareTo("update")==0) {
			try {
				int product_code=Integer.parseInt(request.getParameter("product_code"));
				int quantity=Integer.parseInt(request.getParameter("quantity"));
				Cart cart = (Cart) session.getAttribute("cart");
				if(quantity>0 && quantity<16) {
					for(LineItem li : cart.getItems()) {
						if(li.getProduct().getProduct_code()==product_code) {
							li.setQuantity(quantity);
						}
					}
				}
				out.print(cart.getTotalPrice());
			}catch(NumberFormatException e) {
				getServletContext().log(e.toString());
			}
		}
	}

}
