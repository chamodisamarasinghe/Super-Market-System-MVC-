package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagerLogInFormController {
    public AnchorPane managerLogInFormContext;

    public void openIncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/IncomeReportsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managerLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openMovableItemsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/MovableItemsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managerLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openManageItemsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageItemsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managerLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) managerLogInFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
