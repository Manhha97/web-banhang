package admin.controller;

import admin.dao.ProductDAO;
import admin.dto.ObjectResponse;
import admin.model.Product;
import admin.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.ConvertUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/admin/product")
public class ProductController extends HttpServlet {
    static final String UPLOAD_DIRECTORY = "D:/upload";
    private Gson gson = new Gson();
    private ProductService productService = new ProductService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String page = "/error.jsp";
        String code = null;
        if(session.getAttribute("currentUser")!=null) {
            if (action != null) {
                if (action.equalsIgnoreCase("insert")) {
                    page = "/admin/pages/product-update.jsp";
                } else if (action.equalsIgnoreCase("update")) {
                    code = request.getParameter("code");
                    if (code != null && !code.equalsIgnoreCase("")) {
                        page = "/admin/pages/product-update.jsp";
                    }
                }
            } else {
                page = "/admin/pages/product-crud.jsp";
            }
            request.setAttribute("code", code);
            request.setAttribute("page", "product");
            request.getRequestDispatcher(page).forward(request, response);
        }else {
            response.sendRedirect("/admin/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType ("text/html;charset=utf-8");
        String action = req.getParameter("action");
        if(action != null){
            if(action.equalsIgnoreCase("search")){
                ObjectResponse response = new ObjectResponse();
                response.setCurrentPage(Integer.parseInt(req.getParameter("currentPage")));
                response.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
                Product product = new Product();
                product.setCode(req.getParameter("product[code]"));
                product.setName(req.getParameter("product[name]"));
                product.setSale(Integer.parseInt(req.getParameter("product[sale]")));
                product.setTypeId(Integer.parseInt(req.getParameter("product[typeId]")));
                product.setNxbId(Integer.parseInt(req.getParameter("product[nxbId]")));
                product.setAuthorId(Integer.parseInt(req.getParameter("product[authorId]")));
                product.setNameSort(req.getParameter("product[name_sort]"));
                product.setPriceSort(req.getParameter("product[price_sort]"));
                response.setProduct(product);
                ObjectResponse result = productService.search(response);
                resp.getWriter().write(gson.toJson(result));
            }
            if(action.equalsIgnoreCase("getByCode")){
                String code = req.getParameter("code");
                Product p = productService.getByCode(new Product(code));
                resp.getWriter().write(gson.toJson(p));
            }
            if(action.equalsIgnoreCase("getAuthor")){
                resp.getWriter().write(gson.toJson(productService.getAuthor()));
            }
            if(action.equalsIgnoreCase("getNxb")){
                resp.getWriter().write(gson.toJson(productService.getNxb()));
            }
            if(action.equalsIgnoreCase("getType")){
                resp.getWriter().write(gson.toJson(productService.getType()));
            }
            if(action.equalsIgnoreCase("update")){
                Boolean check = insertWithFile(req, resp);
                resp.getWriter().write(gson.toJson(check));
            }
            if(action.equalsIgnoreCase("delete")){
                String code = req.getParameter("code");
                resp.getWriter().write(gson.toJson(productService.delete(new Product(code))));
            }
        }else{
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
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
                Product product = gson.fromJson(je, Product.class);
                product.setCreateAt(new Date(new java.util.Date().getTime()));
                return productService.update(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}