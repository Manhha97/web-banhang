package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import admin.model.User;
import admin.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/admin/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = "/admin/pages/login.jsp";
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("logout")){
			session.removeAttribute("currentUser");
			response.sendRedirect("/admin/login?action=login");
			return;
		}else {
			if(session.getAttribute("currentUser")!=null)
				response.sendRedirect("/admin/home");
			else
				request.getRequestDispatcher(page).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if(action!=null) {
	        if(action.equalsIgnoreCase("login")){
	            String email = request.getParameter("email");
	            String password= request.getParameter("password");
	            
	            
	            User p = userService.login(new User(email,password));
	            if(p!=null){
					HttpSession session= request.getSession();
					session.setAttribute("currentUser", p);
					response.getWriter().write(gson.toJson(true));
				}else
				response.getWriter().write(gson.toJson(false));
	        }
	        
        }
	}

}
