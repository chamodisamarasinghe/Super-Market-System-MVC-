package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MovableItemsFormController {
    public AnchorPane movableItemsContext;

    public void backToManagerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManagerLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) movableItemsContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
