package admin.service;

import admin.dto.ObjectResponse;
import admin.dao.ProductDAO;
import admin.dto.ProductDetail;
import admin.model.Product;
import util.ConnectionUtil;
import util.ConvertUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public ProductService() {

    }
    public ArrayList<Product> getProductNew(Date date){
        return productDAO.getProductNew(date);
    }
    public Boolean update(Product product){
        if(product.getCode()==null){
            try {
                Connection connection = ConnectionUtil.open();
                PreparedStatement statement = connection.prepareStatement("SELECT max(id) FROM products");
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    Integer id = rs.getInt(1);
                    product.setCode(ConvertUtil.createProductCode(id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return productDAO.insert(product);
        }else {
            if(product.getImage().equalsIgnoreCase("")){
                Product p = productDAO.getByCode(product);
                product.setImage(p.getImage());
            }
            return productDAO.update(product);
        }
    }

    public Boolean delete(Product product){
        return productDAO.delete(product);
    }

    public ObjectResponse search(ObjectResponse response){
        ObjectResponse objectResponse = new ObjectResponse();
        objectResponse.setList(productDAO.search(response));
        objectResponse.setTotal(productDAO.getTotal(response));
        return objectResponse;
    }
    public Product getByCode(Product product){
        Product product1 = productDAO.getByCode(product);
        return  product1;
    }
    public ProductDetail detail(Product product){
        ProductDetail product1 = productDAO.detail(product);
        return  product1;
    }
    public List getAuthor(){
        return productDAO.getAllAuthor();
    }
    public List getNxb(){
        return  productDAO.getAllNxb();
    }
    public List getType(){
        return  productDAO.getAllType();
    }
}
