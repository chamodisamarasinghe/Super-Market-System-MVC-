package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class IncomeReportsFormController {
    public AnchorPane incomeReportsContext;
    public Label lblDate;
    public Label lblTime;
    public ComboBox cmbMonth;

    public void initialize(){
        loadDateAndTime();
        cmbMonth.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");
    }

    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backToManagerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManagerLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) incomeReportsContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
