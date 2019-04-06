package admin.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.*;
import com.google.gson.Gson;

import admin.dto.BillDetailResponse;
import admin.dto.BillResponse;
import admin.dto.StatisticResponse;
import admin.model.Statistic;
import admin.service.BillService;

/**
 * Servlet implementation class BillController
 */
@WebServlet("/admin/bill")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
    private BillService billService = new BillService();
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "/admin/pages/bill.jsp";
		request.setAttribute("page","bill");
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
        resp.setContentType ("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if(action != null){
            if(action.equalsIgnoreCase("search")){
                BillResponse response = new BillResponse();
                response.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
                response.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
                Bill bill = new Bill();
                bill.setCode(req.getParameter("bill[code]"));
                if(req.getParameter("bill[create_at]")!="")
                bill.setCreateAt(new Timestamp(new Date(req.getParameter("bill[create_at]")).getTime()));
                bill.setOrderDetailId(Integer.parseInt(req.getParameter("bill[order_detail_id]")));
                
                response.setBill(bill);
                BillResponse result = billService.search(response);
                resp.getWriter().write(gson.toJson(result));
            }
            if(action.equalsIgnoreCase("getByCode")){
                String code = req.getParameter("code");
                Bill p = billService.getByCode(new Bill(code));
                resp.getWriter().write(gson.toJson(p));
            }
            if(action.equalsIgnoreCase("getAllOrderDetail")){
            	int id = Integer.parseInt(req.getParameter("id"));
            	OrderDetail orderDetail = new OrderDetail();
            	orderDetail.setId(id);
                OrderDetail p = billService.getAllOrderDetail(orderDetail);
                resp.getWriter().write(gson.toJson(p));
            }
            if(action.equalsIgnoreCase("getCustomerinf")){
            	int id = Integer.parseInt(req.getParameter("id"));
            	Customer customer = new Customer();
            	customer.setId(id);
                Customer p = billService.getCustomerinf(customer);
                resp.getWriter().write(gson.toJson(p));
            }
            if(action.equalsIgnoreCase("getProductDetail")){
            	BillDetailResponse response = new BillDetailResponse();
                BillDetail bill = new BillDetail();
                bill.setBillCode(req.getParameter("bill[bill_code]").trim());
                bill.setProductCode(req.getParameter("bill[product_code]").trim());
                bill.setQuantity(Integer.parseInt(req.getParameter("bill[quantity]").trim()));
                response.setBilldetail(bill);
                BillDetailResponse result = billService.getProductDetail(response);
                resp.getWriter().write(gson.toJson(result));
            }
            if(action.equalsIgnoreCase("getStatisticMonth")){
            	StatisticResponse response = new StatisticResponse();
                Statistic bill = new Statistic();
                bill.setMonth(req.getParameter("month"));
                bill.setSum(Float.parseFloat(req.getParameter("sum")));
                response.setStatistic(bill);
                StatisticResponse result = billService.getStatisticMonth(response);
                resp.getWriter().write(gson.toJson(result));
            }
            if(action.equalsIgnoreCase("getStatisticDate")){
            	StatisticResponse response = new StatisticResponse();
                Statistic bill = new Statistic();
                bill.setMonth(req.getParameter("month"));
                bill.setSum(Float.parseFloat(req.getParameter("sum")));
                response.setStatistic(bill);
                StatisticResponse result = billService.getStatisticDate(response);
                resp.getWriter().write(gson.toJson(result));
            }
            if(action.equalsIgnoreCase("getStatisticProductMonth")){
            	StatisticResponse response = new StatisticResponse();
                Statistic bill = new Statistic();
                bill.setMonth(req.getParameter("month"));
                bill.setProduct_code(req.getParameter("product_code"));
                bill.setSum(Float.parseFloat(req.getParameter("sum")));
                bill.setPrice(Float.parseFloat(req.getParameter("price")));
                response.setStatistic(bill);
                StatisticResponse result = billService.getStatisticProductMonth(response);
                resp.getWriter().write(gson.toJson(result));
            }
        }else{
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
	}

}
