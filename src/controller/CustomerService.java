package controller;

import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService {
    //public boolean saveCustomer(Item i) throws SQLException, ClassNotFoundException;
   // public boolean updateCustomer(Item i) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String code) throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException;
}
