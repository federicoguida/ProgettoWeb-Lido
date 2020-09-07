package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.EmailService;
import model.JsonUtility;
import model.LidoDB;
import model.ProductDB;
import model.UserDB;
import model.beans.Product;
import model.beans.User;

@MultipartConfig
@WebServlet("/view/personalarea/admin/management")
public class AdminManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("getUsers")) {
			ResultSet rs = UserDB.getUsers();
			JsonUtility.responseJson(rs, response);
		}else if(action.equals("getLido")) {
			ResultSet rs = LidoDB.getLido();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
		    out.print(JsonUtility.ResultSetToJson(rs));
			out.flush();
		}else if(action.equals("getProducts")){
			ArrayList<Product> rs = ProductDB.getProducts();
			JSONArray jsonArray = new JSONArray();
			try {
				for(int i = 0 ; i<rs.size() ; i++) {
				    JSONObject obj = new JSONObject();
				    Product pr = rs.get(i);
				    obj.put("code",pr.getProduct_code());
				    obj.put("name", pr.getName());
				    obj.put("description", pr.getDescription());
				    obj.put("price", pr.getPrice());
				    obj.put("category", pr.getCategory());
				    obj.put("imgurl", pr.getImgURL());
				    jsonArray.put(obj);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			JsonUtility.responseJson(jsonArray, response);
		}else if(action.equals("userData")) {
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
		if(action.equals("addUser")) {
			String email = request.getParameter("email");
			if(UserDB.emailExist(email)) {
				response.setContentType("plain/text");
				PrintWriter out = response.getWriter();
				out.write("false");
				out.flush();
			}else {
				User user = new User();
				user.setName(request.getParameter("name"));
				user.setEmail(email);
				user.setSurname(request.getParameter("surname"));
				user.setPassword(request.getParameter("password"));
				user.setRole(request.getParameter("rolelist"));
				try {
					user.setAge(Integer.parseInt(request.getParameter("age")));
				}catch(NumberFormatException e) {
					getServletContext().log(e.toString());
					user.setAge(0);
				}
				UserDB.insert(user);
				ServletContext sc = request.getServletContext();
				new Thread() {
		            public void run() {
		            	try {
			            	EmailService.sendMail(email, "Benvenuto dipendente!", "Caro " + user.getName() + " " + user.getSurname() + ", "
			            			+ "le informiamo che i servizi a lei dedicati sono ora disponibili",
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
		        response.setContentType("plain/text");
				PrintWriter out = response.getWriter();
				out.write("true");
				out.flush();
			}
		}else if(action.equals("moduser")) {
			UserDB.updateUserRole(request.getParameter("email"), request.getParameter("role"));
		}else if(action.equals("rmuser")) {
			UserDB.removeUser(request.getParameter("email"));
		}else if(action.equals("disable")) {
			LidoDB.setLocationStatus(request.getParameter("idlocation"), "1");
		}else if(action.equals("active")) {
			LidoDB.setLocationStatus(request.getParameter("idlocation"), "0");
		}else if(action.equals("addProduct")) {
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setDescription(request.getParameter("description"));
			product.setCategory(request.getParameter("categorylist"));
			try {
				product.setPrice(Double.parseDouble((request.getParameter("price"))));
			}catch(NumberFormatException e) {
				getServletContext().log(e.toString());
				product.setPrice(0);
			}
			Part filePart = request.getPart("customFile");
		    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		    String path = getServletContext().getInitParameter("dir");
		    File uploads = new File(path);
		    File file = new File(uploads, fileName);
		    try (InputStream fileContent = filePart.getInputStream();) {
		        Files.copy(fileContent, file.toPath());
		        product.setImgURL("http://localhost:8080/guidafederico/imageRestaurant/"+fileName);
			    ProductDB.setProduct(product);
		    }catch(IOException e) {
		    	getServletContext().log(e.toString());
		    }
		}else if(action.equals("removeProduct")){
			String product_code = request.getParameter("product_code");
			try {
				Product pr = ProductDB.getProduct(Integer.parseInt(product_code));
				ProductDB.removeProduct(product_code);
				String[] parts = pr.getImgURL().split("http://localhost:8080/guidafederico/imageRestaurant/");
				String path = getServletContext().getInitParameter("dir");
				File file = new File(path+"\\"+parts[1]);
				file.delete();
			}catch(NumberFormatException e) {
				getServletContext().log(e.toString());
			}
		}
	}
}
