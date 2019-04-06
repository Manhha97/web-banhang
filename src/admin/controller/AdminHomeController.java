package admin.controller;

import admin.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/home")
public class AdminHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser!=null) {
            req.setAttribute("page","home");
            String page = "/admin/pages/statistics.jsp";
            req.getRequestDispatcher(page).forward(req,resp);
        }else {
            String page = "/admin/pages/login.jsp";
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
