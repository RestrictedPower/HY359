package csd4149;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class UserManagement
 */
public class UserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException {
		System.out.println("--put--");
		JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
		String name = ja.get("name").getAsString();
		System.out.println(name);
	}

}
