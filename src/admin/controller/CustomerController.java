package admin.controller;

import admin.model.Bill;
import admin.model.Customer;
import admin.model.OrderDetail;
import admin.service.CustomerService;
import admin.service.OrderService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    Gson gson = new Gson();
    private CustomerService customerService = new CustomerService();
    private OrderService orderService = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if(action!=null) {
            Customer customer = (Customer) session.getAttribute("currentCustomer");
            if (action.equalsIgnoreCase("login")) {
                if(customer!=null){
                    resp.sendRedirect("/home");
                }else{
                    String next_page = req.getParameter("next_page");
                    if (next_page!=null&&!next_page.equalsIgnoreCase(""))
                        req.setAttribute("next_page","/product?code="+next_page);
                    req.getRequestDispatcher("/client/pages/login.jsp").forward(req, resp);
                }

            }
            if (action.equalsIgnoreCase("edit-info")){
                if(customer != null)
                    req.getRequestDispatcher("/client/pages/customer-info.jsp").forward(req, resp);
                else resp.sendRedirect("/customer?action=login");
            }
            if (action.equalsIgnoreCase("my-order")){
                if(customer != null){
                    OrderDetail orderDetail = orderService.getAddressOrder(customer);
                    List<Bill> bills = orderService.getListBill(orderDetail.getId());
                    req.setAttribute("bills", bills);
                    req.getRequestDispatcher("/client/pages/customer-info.jsp").forward(req, resp);
                }

                else resp.sendRedirect("/customer?action=login");
            }
            if (action.equalsIgnoreCase("logout")){
                session.removeAttribute("currentCustomer");
                resp.sendRedirect("/customer?action=login");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        if(action!=null){
            if (action.equalsIgnoreCase("login")){
                String un = req.getParameter("email");
                String pw = req.getParameter("password");
                Customer customer = new Customer(un, pw);
                Customer check_customer = customerService.checkLogin(customer);
                if(check_customer!=null){
                    session.setAttribute("currentCustomer", check_customer);
                    resp.getWriter().write(gson.toJson(true));
                }else
                    resp.getWriter().write(gson.toJson(false));
            }
            if (action.equalsIgnoreCase("register")){
                String un = req.getParameter("email");
                String pw = req.getParameter("password");
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                Customer customer = new Customer(name, phone, un, pw);
                if(customerService.getCustomer(customer)!=null){
                    resp.getWriter().write(gson.toJson(false));
                    return;
                }
                if(customerService.register(customer)){
                    Customer check_customer = customerService.checkLogin(customer);
                    if(check_customer!=null){
                        session.setAttribute("currentCustomer", check_customer);
                        resp.getWriter().write(gson.toJson(true));
                    }
                }else
                    resp.getWriter().write(gson.toJson(false));

            }
            if (action.equalsIgnoreCase("edit-info")){
                Customer customer1= new Customer();
                Customer customer = (Customer) session.getAttribute("currentCustomer");
                String name = req.getParameter("name");
                customer1.setId(customer.getId());
                customer1.setName(name);
                customer1.setPhone(customer.getPhone());
                customer1.setEmail(customer.getEmail());
                customer1.setPassword(customer.getPassword());
                String check = req.getParameter("is_change_pass");
                if(check!=null && check.equalsIgnoreCase("checked")){
                    String old=req.getParameter("old_password");
                    if(old.equalsIgnoreCase(customer.getPassword())){
                        customer1.setPassword(req.getParameter("new_password"));
                    }else {
                        resp.getWriter().write(gson.toJson(false));
                    }
                }
                if(customerService.edit(customer1)){
                    session.setAttribute("currentCustomer", customer1);
                    resp.getWriter().write(gson.toJson(true));
                }
                else resp.getWriter().write(gson.toJson(false));
            }
        }
    }
}
