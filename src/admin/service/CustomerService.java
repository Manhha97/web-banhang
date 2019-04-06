package admin.service;

import admin.dao.CustomerDAO;
import admin.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    public CustomerService() {
    }

    public Customer checkLogin(Customer customer){
        return customerDAO.checkLogin(customer);
    }
    public Boolean edit(Customer customer){
        return customerDAO.edit(customer);
    }
    public Boolean register(Customer customer){
        return customerDAO.register(customer);
    }
    public Customer getCustomer(Customer customer){
        return customerDAO.getCustomer(customer);
    }
}
