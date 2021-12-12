package csd4149;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import csd4149.database.EditSimpleUserTable;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login check request");
        HttpSession session=request.getSession();
        if(session.getAttribute("loggedIn")!=null){
           response.setStatus(200);
           
//           Person p=Resources.registeredUsers.get(session.getAttribute("loggedIn").toString());
           response.getWriter().write("username");
        }
        else{
            response.setStatus(403);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
	        HttpSession session=request.getSession();
	        String us = ja.get("username").getAsString();
	        String pw = ja.get("password").getAsString();
	        EditSimpleUserTable edt = new EditSimpleUserTable();
   	   		JsonObject jo = new JsonObject();
   	   		System.out.println(request.getSession().getId());
   	   		if(edt.userExists(us, pw)) {
				jo.addProperty("success", "Your login was successfull.");
	    	   	System.out.println("good login");
	    	   	session.setAttribute("loggedIn", "true");
	    	   	response.setStatus(200);
   	   		}else {

				jo.addProperty("error", "Wrong credentials.");
	    	   	System.out.println("bad login");
				response.setStatus(403);
	       }
			response.getWriter().write(jo.toString());
	}

}
/*
Login extends HttpServlet {
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
       
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       HttpSession session=request.getSession();
       System.out.println("1");
       if(session.getAttribute("loggedIn")!=null){
          response.setStatus(200);
          
//          Person p=Resources.registeredUsers.get(session.getAttribute("loggedIn").toString());
//          response.getWriter().write(p.getUsername());
       }
       else{
           response.setStatus(403);
       }
   }
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
	   System.out.println("2");
       String username=request.getParameter("username");
       String password=request.getParameter("password");      
       HttpSession session=request.getSession();
       System.out.println(username+" "+password);
   }
}
*/