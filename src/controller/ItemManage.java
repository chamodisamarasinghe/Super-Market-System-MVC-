package controller;

import model.Customer;
import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemManage {
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    public Item getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;
}
