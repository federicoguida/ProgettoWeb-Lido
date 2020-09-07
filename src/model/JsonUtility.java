package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtility {

	public static JSONArray ResultSetToJson(ResultSet rs) {
		JSONArray jsonArray = new JSONArray();
		try {
			while (rs.next()) {
			    int columns = rs.getMetaData().getColumnCount();
			    JSONObject obj = new JSONObject();
			    for (int i = 0; i < columns; i++)
			        obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
			    jsonArray.put(obj);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	public static void responseJson(ResultSet rs, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    out.print(JsonUtility.ResultSetToJson(rs));
		out.flush();
	}
	
	public static void responseJson(JSONArray json , HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    out.print(json);
		out.flush();
	}
	
	public static void responseJson(JSONObject json , HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    out.print(json);
		out.flush();
	}
}
