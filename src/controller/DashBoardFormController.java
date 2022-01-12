package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class
DashBoardFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassWord;
    public AnchorPane dashBoardFormContext;


    public void logInManagerOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Manager")&& txtPassWord.getText().equalsIgnoreCase("123")) {
            Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/ManagerLogInForm.fxml"))));
        }else{
            new Alert(Alert.AlertType.WARNING,"Incorrect UserName,PassWord And Try Again",ButtonType.CLOSE).show();
        }
    }

    public void logInCashierOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Cashier")&& txtPassWord.getText().equalsIgnoreCase("456")) {
            Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/CashierLogInForm.fxml"))));
        }else{
            new Alert(Alert.AlertType.WARNING,"Incorrect UserName,PassWord And Try Again",ButtonType.CLOSE).show();
        }
    }

    public void moveToPassWord(ActionEvent actionEvent) {
        txtPassWord.requestFocus();
    }
}

