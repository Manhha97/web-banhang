package client.controller;

import admin.dao.OrderDAO;
import admin.model.Bill;
import admin.model.BillDetail;
import admin.model.Customer;
import admin.model.OrderDetail;
import admin.service.OrderService;
import com.google.gson.Gson;
import util.CONSTANT;
import util.ConvertUtil;
import util.SendMail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private OrderService orderService = new OrderService();
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String page = "";
        String action = req.getParameter("action");
        if(action != null){
            Customer customer = (Customer) session.getAttribute("currentCustomer");
            if(action.equalsIgnoreCase("checkout")){

                if(customer!=null){
                    OrderDetail addressDetail = orderService.getAddressOrder(customer);
                    req.setAttribute("addressDetail", addressDetail);
                    page = "/client/pages/checkout.jsp";
                }
                else{
                    req.setAttribute("next_page","/order?action=checkout");
                    page = "/customer?action=login";
                }
            }

            if(action.equalsIgnoreCase("cart"))
                page = "/client/pages/cart.jsp";
            if(action.equalsIgnoreCase("complete")){
                if(customer!=null) {
                    String code = req.getParameter("code");
                    OrderDetail orderDetail = orderService.getAddressOrder(customer);
                    List<BillDetail> billDetailList = orderService.getBillDetail(code);
                    Bill bill = orderService.getBill(orderDetail.getId(), code);
                    req.setAttribute("orderDetail", orderDetail);
                    req.setAttribute("billDetailList", billDetailList);
                    req.setAttribute("bill", bill);
                    //System.out.println(new SendMail().sendMail("anhtran99xx@gmail.com",bill,orderDetail,billDetailList));
                    page = "/client/pages/order-complete.jsp";
                }else{
                    req.setAttribute("next_page","/order?action=complete");
                    page = "/customer?action=login";
                }

            }
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("currentCustomer");
        String action = req.getParameter("action");
        if(action!=null){
            if (action.equalsIgnoreCase("insert-order-detail") && customer != null){
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String address = req.getParameter("destination");
                Double distance = Double.parseDouble(req.getParameter("distance"));
                Integer deliveryId = Integer.parseInt(req.getParameter("deliveryId"));
                Integer customerId = customer.getId();
                OrderDetail detailOrder = new OrderDetail(name, phone, address, deliveryId, customerId, distance);
                if (orderService.insert(detailOrder)){
                    resp.getWriter().write(gson.toJson(true));
                }else resp.getWriter().write(gson.toJson(false));
            }
            if (action.equalsIgnoreCase("edit-order-detail")){
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String address = req.getParameter("destination");
                Double distance = Double.parseDouble(req.getParameter("distance"));
                Integer deliveryId = Integer.parseInt(req.getParameter("deliveryId"));
                Integer customerId = customer.getId();
                OrderDetail detailOrder = new OrderDetail(name, phone, address, deliveryId, customerId, distance);
                if (orderService.edit(detailOrder)){
                    resp.getWriter().write(gson.toJson(true));
                }else resp.getWriter().write(gson.toJson(false));
            }
            if(action.equalsIgnoreCase("save-order") && customer != null){
                Map<String, String[]> map = req.getParameterMap();
                String [] codes = req.getParameterValues("p-code");
                String [] quantitys = req.getParameterValues("p-quantity");
                OrderDetail orderDetail = orderService.getAddressOrder(customer);
                Bill bill = new Bill();
                String bill_code = "HD_"+ ConvertUtil.randomDateNow();
                bill.setCode(bill_code);
                bill.setCreateAt(new Timestamp(new Date().getTime()));
                bill.setOrderDetailId(orderDetail.getId());
                if (orderService.insertBill(bill)){
                    List<BillDetail> list = new ArrayList<BillDetail>();
                    for (int i=0; i< codes.length; i++){
                        BillDetail billDetail = new BillDetail();
                        billDetail.setBillCode(bill_code);
                        billDetail.setProductCode(codes[i]);
                        billDetail.setQuantity(Integer.parseInt(quantitys[i]));
                        list.add(billDetail);
                    }
                    if(orderService.insertBillDetail(list)){
                        resp.getWriter().write(bill_code);
                    }
                }
            }
            if(action.equalsIgnoreCase("send-order-mail")){
                String code = "HD_0398";
                OrderDetail orderDetail = orderService.getAddressOrder(customer);
                List<BillDetail> billDetailList = orderService.getBillDetail(code);
                Bill bill = orderService.getBill(orderDetail.getId(), code);
                SendMail sendMail = new SendMail();
                if(sendMail.send("anhtran99xx@gmail.com",bill,orderDetail,billDetailList))
                    resp.getWriter().write(gson.toJson(true));
                else resp.getWriter().write(gson.toJson(false));

            }
        }
    }

}
