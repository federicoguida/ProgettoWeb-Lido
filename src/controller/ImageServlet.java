package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageRestaurant/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("image/jpeg");
			String path = getServletContext().getInitParameter("dir");
			String string = request.getPathInfo();
			Path filename = Paths.get(string);
			File f = new File(path + filename);
			BufferedImage bi = ImageIO.read(f);
			OutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.close();
		}catch(IOException e) {
			getServletContext().log(e.toString());
		}
	}

}
