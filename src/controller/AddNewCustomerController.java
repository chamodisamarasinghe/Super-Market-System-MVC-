package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Item;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AddNewCustomerController extends CustomerController{
    public AnchorPane addNewCustomerContext;
    public TextField txtCustomerId;
    public TextField txtCustomerTitle;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerCity;
    public TextField txtCustomerProvince;
    public TextField txtCustomerPostalCode;


    public void backToCashier(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) addNewCustomerContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c = new Customer(
               txtCustomerId.getText(),txtCustomerTitle.getText(),txtCustomerName.getText(),
               txtCustomerAddress.getText(),txtCustomerCity.getText(),txtCustomerAddress.getText(), txtCustomerPostalCode.getText()
        );

        if(new CustomerController() {

        }.saveCustomer(c))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }




    public void modifyCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer c = new Customer(
                txtCustomerId.getText(),txtCustomerTitle.getText(),txtCustomerName.getText(),
                txtCustomerAddress.getText(),txtCustomerCity.getText(),txtCustomerAddress.getText(), txtCustomerPostalCode.getText()
        );

        if(new CustomerController() {

        }.updateCustomer(c))
            new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }



    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(txtCustomerId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }





    public void moveToName(ActionEvent actionEvent) {
        txtCustomerName.requestFocus();
    }

    public void moveToAddress(ActionEvent actionEvent) {
        txtCustomerAddress.requestFocus();
    }

    public void moveToCity(ActionEvent actionEvent) {
        txtCustomerCity.requestFocus();
    }

    public void moveToProvince(ActionEvent actionEvent) {
        txtCustomerProvince.requestFocus();
    }


    public void moveToPostalCode(ActionEvent actionEvent) {
        txtCustomerPostalCode.requestFocus();
    }

    public void moveToNewTitle(ActionEvent actionEvent) {
        txtCustomerTitle.requestFocus();
    }

    public void openMakeCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/MakeCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) addNewCustomerContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
