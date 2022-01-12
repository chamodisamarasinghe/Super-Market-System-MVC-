package controller;

import db.DbConnection;
import model.Item;
import model.Order;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId < 9) {
                return "O-00" + tempId;
            } else if (tempId < 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "O-001";
        }
    }


    public boolean placeOrder(Order order) {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Order` VALUES(?,?,?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getCustomerId());
            stm.setObject(3, order.getOrderDate());
            stm.setObject(4, order.getOrderTime());
            stm.setObject(5, order.getCost());


            if (stm.executeUpdate() > 0) {
                if (saveOrderDetail(order.getOrderId(), order.getItems())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;

    }

    private boolean saveOrderDetail(String detailOrderId, ArrayList<Item> items) throws SQLException, ClassNotFoundException {
        for (Item temp : items
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `order detail` VALUES(?,?,?,?,?)");
            stm.setObject(1, temp.getItemCode());
            stm.setObject(2, detailOrderId);
            stm.setObject(3, temp.getQtyOnHand());
            stm.setObject(4, temp.getUnitPrice());
            stm.setObject(5, 12.4);
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getItemCode(), temp.getQtyOnHand())) {

                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE ITEM SET qtyOnHand=(qtyOnHand-" + qty
                                + ") WHERE itemCode='" + itemCode + "'");
        return stm.executeUpdate() > 0;
    }
}