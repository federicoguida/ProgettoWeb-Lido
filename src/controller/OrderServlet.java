package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.beans.Cart;

import model.beans.Order;
import model.beans.User;
import model.EmailService;
import model.OrderDB;
import model.UserDB;

@WebServlet("/view/personalarea/user/restaurant/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("action").equals("instruction")) {
				HttpSession session = request.getSession();
				session.setAttribute("instruction", request.getParameter("ins"));
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null) {
			User user = UserDB.selectUser(request.getUserPrincipal().getName());
			Order order=new Order();
			order.setUser_id(user.getId());
			if(request.getParameter("action").equals("cart-pay"))
				order.setOrderPay(1);
			else
				order.setOrderPay(0);
			order.setOrder_id("order_"+request.getUserPrincipal().hashCode()+OrderDB.getCount());
			order.setTotal(cart.getTotalPrice());
			String instruction=(String)session.getAttribute("instruction");
			if(instruction!=null)
				order.setInstruction(instruction);
			order.setDelivered(0);
			OrderDB.insertOrder(order);
			OrderDB.insertProduct(order.getOrder_id(), cart.getItems());
			session.removeAttribute("cart");
			session.removeAttribute("instruction");
			if(request.getParameter("action").equals("cart-pay")) {
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(user.getEmail(), "Ordine effettuato!", "Caro " + user.getName() + " " + user.getSurname() + ", "
			            			+ "le informiamo che l'ordine "+order.getOrder_id()+" da lei effettuato è andato a buon fine! La sua richiesta"
			            					+ " è stata mandata correttamente in cucina attenda che il suo ordine sia pronto.",
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
				response.sendRedirect("/guidafederico/view/personalarea/user/restaurant");
			}else {
				request.setAttribute("order", order);
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(user.getEmail(), "Ordine prenotato!", "Caro " + user.getName() + " " + user.getSurname() + ", "
			            			+ "le informiamo che l'ordine "+order.getOrder_id()+" da lei effettuato è andato a buon fine! La sua richiesta"
			            					+ " è stata mandata correttamente alla cassa, effettui il pagamento per ricevere il suo ordine.",
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
				sc.getRequestDispatcher("/WEB-INF/view/personalarea/user/restaurant/prenotation.jsp").forward(request,response);
			}
		}else {
			response.sendRedirect("/guidafederico/view/personalarea/user/restaurant");
		}
	}

}
