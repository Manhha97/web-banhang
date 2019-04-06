package client.controller;

import admin.dto.ProductDetail;
import admin.model.Comment;
import admin.model.Customer;
import admin.model.Product;
import admin.service.CommentService;
import admin.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    ProductService productService = new ProductService();
    CommentService commentService = new CommentService();
    Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType ("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if(action != null){
            if(action.equalsIgnoreCase("wish-list")){
                req.getRequestDispatcher("/client/pages/wish-list.jsp").forward(req, resp);
            }

        }else{
            String code = req.getParameter("code");
            ProductDetail product = productService.detail(new Product(code));
            //Customer customer = (Customer) session.getAttribute("currentCustomer");
            Comment comment = new Comment();
            Product p = new Product();
            p.setId(product.getId());
            comment.setProduct(p);
            List<Comment> listComment = commentService.getAll(comment);
            req.setAttribute("comments", listComment);
            req.setAttribute("product", product);
            req.getRequestDispatcher("/client/pages/product-detail.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType ("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("get-new-product")){
            Cookie cookie = getCookie(req.getCookies());

            if (cookie != null) {

                String value = cookie.getValue();
                String split[] = value.split("&");
                String dateJoin = split[0].split("_")[1];
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = (Date)formatter.parse(dateJoin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Integer seen = Integer.parseInt(split[1].split("_")[1]);
                List<Product> list = productService.getProductNew(new java.sql.Date(date.getTime()));
                if (list.size() == 0){
                    resp.getWriter().write(gson.toJson(null));
                    return;
                }

                if(seen == 2){
                    resp.getWriter().write(gson.toJson(list));
                }else
                    resp.getWriter().write(gson.toJson(null));
            }

        }
        if (action.equalsIgnoreCase("get-notification")){
            Cookie cookie = getCookie(req.getCookies());

            if (cookie != null) {

                String value = cookie.getValue();
                String split[] = value.split("&");
                Integer seen = Integer.parseInt(split[1].split("_")[1]);
                if (seen == 1){
                    resp.getWriter().write(gson.toJson(false));
                    return;
                }
                String dateJoin = split[0].split("_")[1];
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = (Date) formatter.parse(dateJoin);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Product> list = productService.getProductNew(new java.sql.Date(date.getTime()));
                String arr = req.getParameter("arr_code");
                if (arr != null && !arr.equalsIgnoreCase("")) {
                    List<String> codes = new ArrayList<String>(Arrays.asList(arr.split(",")));

                    if (list.size() < codes.size()) {
                        resp.getWriter().write(gson.toJson(null));
                        return;
                    }
                    if (codes.size() > 0) {
                        for (Product p : list) {
                            if (codes.indexOf(p.getCode()) == -1) {
                                resp.getWriter().write(gson.toJson(true));
                                return;
                            }
                        }
                    }
                }else {
                    if(list.size()>0)
                        resp.getWriter().write(gson.toJson(true));
                }

            }else
                resp.getWriter().write(gson.toJson(false));
        }
        if(action.equalsIgnoreCase("add-comment")){
            String comment = req.getParameter("comment");
            Integer starVote = Integer.parseInt(req.getParameter("star-vote"));
            Integer pid = Integer.parseInt(req.getParameter("productId"));
            Customer customer = (Customer) session.getAttribute("currentCustomer");
            //
            Customer customer1 = new Customer();
            customer1.setName(customer.getName());
            customer1.setId(customer.getId());
            //
            Product product = new Product();
            product.setId(pid);
            //
            Comment comment1 = new Comment();
            comment1.setStarVote(starVote);
            comment1.setProduct(product);
            comment1.setCustomer(customer1);
            comment1.setComment(comment);
            comment1.setCreateAt(new Timestamp(new Date().getTime()));
            if(commentService.add(comment1)){
                resp.getWriter().write(gson.toJson(comment1));
            }else resp.getWriter().write(gson.toJson(false));
        }
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
