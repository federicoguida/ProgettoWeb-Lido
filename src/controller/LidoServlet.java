package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.beans.User;
import model.EmailService;
import model.JsonUtility;
import model.LidoDB;
import model.UserDB;

@WebServlet("/view/personalarea/user/lido")
public class LidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action=request.getParameter("action");
		if(action!=null) {
			if(action.equals("lido")) {
				session.removeAttribute("lidoCart");
				ResultSet rs = LidoDB.getLidoActive();
				JsonUtility.responseJson(rs, response);
			}
		}else {
			request.getServletContext().getRequestDispatcher("/WEB-INF/view/personalarea/user/lido/lido.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("action").equals("add")) {
			@SuppressWarnings("unchecked")
			ArrayList<String> items = (ArrayList<String>) session.getAttribute("lidoCart");
			if(items==null) {
				items = new ArrayList<>();
				session.setAttribute("lidoCart", items);
			}
			items.add(request.getParameter("itemID"));
			ResultSet rs = LidoDB.getLocation(request.getParameter("itemID"));
			JsonUtility.responseJson(rs, response);
		}else if(request.getParameter("action").equals("remove")) {
			@SuppressWarnings("unchecked")
			ArrayList<String> items = (ArrayList<String>) session.getAttribute("lidoCart");
			items.remove(items.indexOf(request.getParameter("itemID")));
		}else if(request.getParameter("action").equals("pay")) {
				@SuppressWarnings("unchecked")
				ArrayList<String> items = (ArrayList<String>) session.getAttribute("lidoCart");
				if(items!=null) {
					if(items.isEmpty()==false) {
						ResultSet rs = LidoDB.getLido();
						boolean flag=true;
						try {
							while(rs.next()) {
								for(String item : items) {
									if(item.equals(rs.getString("idlocation"))) {
										if(rs.getInt("Busy")==1) {
											flag=false;
										}
									}
								}
							}	
							if(flag==true) {
								PrintWriter out = response.getWriter();
								String email=request.getUserPrincipal().getName();
								ServletContext sc=request.getServletContext();
								Random random=new Random();
								int hashSum=random.nextInt();
								for(String item : items) {
									User user = UserDB.selectUser(email);
									LidoDB.insertPrenotation(item, user.getId(),"LIDOPRID"+email.hashCode()+hashSum);
								}
								session.removeAttribute("lidoCart");
								response.setContentType("plain/text");
								out.print("true");
								out.flush();
								new Thread() {
						            public void run() {
						            	EmailService.sendMail(email, "Prenotazione effettuata con successo!"
						            			, "Presenta all'entrata il tuo ID "+"LIDOPRID"+email.hashCode()+hashSum+""
						            			+ " e verrai accompagnato al tuo posto!",
												false, sc);
						            }
						        }.start();
							}else {
								session.removeAttribute("lidoCart");
								PrintWriter out = response.getWriter();
								response.setContentType("plain/text");
								out.print("false");
								out.flush();
							}
					   }catch (SQLException e) {
						   e.printStackTrace();
					   }
				   }else{ 
						session.removeAttribute("lidoCart");
						PrintWriter out = response.getWriter();
						response.setContentType("plain/text");
						out.print("cartnull");
						out.flush();
				   }
			 }else{
				session.removeAttribute("lidoCart");
				PrintWriter out = response.getWriter();
				response.setContentType("plain/text");
				out.print("cartnull");
				out.flush();
			}
		}
	}	
}
