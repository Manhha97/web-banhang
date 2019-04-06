package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import admin.dto.ObjectResponse;
import admin.dto.UserResponse;
import admin.model.Product;
import admin.model.User;
import admin.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/admin/user")
public class UserController extends HttpServlet {
	static final String UPLOAD_DIRECTORY = "D:/upload";
	private Gson gson = new Gson();
    private UserService userService = new UserService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession();
        if(session.getAttribute("currentUser")!=null) {
            String action = request.getParameter("action");
            String page = "/admin/pages/user-crud.jsp";
            String code = null;
            if (action != null) {
                if (action.equalsIgnoreCase("insert")) {
                    page = "/admin/pages/user-update.jsp";
                } else if (action.equalsIgnoreCase("update")) {
                    code = request.getParameter("code");
                    if (code != null && !code.equalsIgnoreCase("")) {
                        page = "/admin/pages/user-update.jsp";
                    }
                }
            }
            request.setAttribute("code", code);
            request.setAttribute("page", "user");
            request.getRequestDispatcher(page).forward(request, response);
        }else {
            response.sendRedirect("/admin/home");
        }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User user1 = (User) session.getAttribute("currentUser");
		if(user1!=null) {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=utf-8");
            String action = req.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("search")) {
                    UserResponse response = new UserResponse();
                    response.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
                    response.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
                    User user = new User();
                    user.setCode(req.getParameter("user[code]"));
                    user.setRole_id(Integer.parseInt(req.getParameter("user[role_id]")));
                    user.setName(req.getParameter("user[name]"));
                    response.setUser(user);
                    UserResponse result = userService.search(response, user1.getRole_id());
                    resp.getWriter().write(gson.toJson(result));
                }
                if (action.equalsIgnoreCase("getByCode")) {
                    String code = req.getParameter("code");
                    User p = userService.getByCode(new User(code));
                    resp.getWriter().write(gson.toJson(p));
                }
                if (action.equalsIgnoreCase("getRole")) {
                    resp.getWriter().write(gson.toJson(userService.getRole()));
                }
                if (user1.getRole_id()==3){
                    if (action.equalsIgnoreCase("update")) {
                        Boolean check = insertWithFile(req, resp);
                        resp.getWriter().write(gson.toJson(check));
                    }
                    if (action.equalsIgnoreCase("delete")) {
                        String code = req.getParameter("code");
                        resp.getWriter().write(gson.toJson(userService.delete(new User(code))));
                    }
                }
            } else {
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }else resp.sendRedirect("/admin/home");
	}
	
	public Boolean insertWithFile(HttpServletRequest req, HttpServletResponse response){
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List<FileItem> fileItems = upload.parseRequest(req);
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (FileItem item : fileItems) {
                    String key = item.getFieldName();
                    if (!item.isFormField()) {
                        String filename = item.getName();
                        if(!filename.equalsIgnoreCase("")){
                            String pathFile = UPLOAD_DIRECTORY + File.separator + filename;
                            item.write(new File(pathFile));
                        }
                        map.put(key, filename);
                    }else{
                        String value = item.getString("UTF-8");
                        map.put(key, value);
                    }
                }
                JsonElement je = gson.toJsonTree(map);
                User user = gson.fromJson(je, User.class);
                return userService.update(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
