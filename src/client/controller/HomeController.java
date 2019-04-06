package client.controller;

import util.ConvertUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        createCustomUser(req, resp);
        req.getRequestDispatcher("/client/pages/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    public void createCustomUser(HttpServletRequest req, HttpServletResponse resp){
        Cookie[] cookies = req.getCookies();
        Boolean newP = false;

        if (cookies != null) {
            Cookie cookie = getCookie(cookies);
            if(cookie != null){
                //lần 2
                newP = true;
                String value = cookie.getValue();
                String split[] = value.split("&");
                if(!split[1].equalsIgnoreCase("seen_2")){
                    String newValue = split[0]+"&seen_2";
                    cookie.setValue(newValue);
                    resp.addCookie(cookie);
                }
            }else {
                //lần đầu
            }

        }
        if(newP == false){
            String value = "uid_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +"&seen_1";
            Cookie CustomUser = new Cookie("CustomUser", value);
            CustomUser.setMaxAge(60 * 60 * 24);
            resp.addCookie(CustomUser);
        }
        //req.setAttribute("new-product", newP);
    }
    public Cookie getCookie(Cookie[] cookies){
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if(c.getName().equalsIgnoreCase("CustomUser")){
                cookie = c;
                break;
            }
        }
        return cookie;
    }
}
