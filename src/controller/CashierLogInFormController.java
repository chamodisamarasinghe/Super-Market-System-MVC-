package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CashierLogInFormController {
    public AnchorPane cashierLogInFormContext;




    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }


    public void openManageOrdersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageOrdersForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void OpenMakeACustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/MakeCustomerOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cashierLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


}
