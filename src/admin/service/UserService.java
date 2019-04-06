package admin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import admin.dao.UserDAO;
import admin.dto.ObjectResponse;
import admin.dto.UserResponse;
import admin.model.Product;
import admin.model.User;
import util.ConnectionUtil;
import util.ConvertUtil;

public class UserService {
	private UserDAO userDAO= new UserDAO();
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	public Boolean delete(User user){
        return userDAO.delete(user);
    }
	public Boolean update(User user){
        if(user.getCode()==null){
            try {
                Connection connection = ConnectionUtil.open();
                PreparedStatement statement = connection.prepareStatement("SELECT max(id) FROM user");
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    Integer id = rs.getInt(1);
                    Integer role=user.getRole_id();
                    user.setCode(ConvertUtil.createUsercode(role,id));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userDAO.insert(user);
        }else {
            if(user.getProfile().equalsIgnoreCase("") || user.getProfile() == null){
                User p = userDAO.getByCode(user);
                user.setProfile(p.getProfile());
            }
            return userDAO.update(user);
        }
    }
	public UserResponse search(UserResponse response, int id){
        UserResponse objectResponse = new UserResponse();
        objectResponse.setList(userDAO.search(response, id));
        objectResponse.setTotal(userDAO.getTotal(response, id));
        return objectResponse;
    }
	public User getByCode(User user){
        User u = userDAO.getByCode(user);
        return  u;
    }
    public List getRole(){
        return userDAO.getAllRole();
    }
    public User login(User user){
	    return userDAO.login(user);
    }
}
