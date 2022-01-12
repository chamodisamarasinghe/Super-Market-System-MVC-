package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Item;
import tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemsFormController extends ItemController{
    public AnchorPane manageItemFormContext;
    public AnchorPane registerItemsContext;
    public TableView<Item> tblItems;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQtyOnHand;
    public TableColumn colUnitPrice;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;

    public void initialize(){

        try {
            colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));



            setItemsToTable(new ItemController().getAllItems());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }

    }
    private void setItemsToTable(ArrayList<Item> items) {
        ObservableList<Item> obList = FXCollections.observableArrayList();
        items.forEach(e->{
            obList.add(
                    new Item(e.getItemCode(),e.getDescription(),e.getPackSize(),e.getQtyOnHand(),e.getUnitPrice()));
        });
       tblItems.setItems(obList);
    }



    public void backToManagerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManagerLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageItemFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void registerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i1 = new Item(
                txtCode.getText(),txtDescription.getText(), txtPackSize.getText(),
                Integer.valueOf(txtQtyOnHand.getText()),
        Double.parseDouble(txtUnitPrice.getText())
        );
        if(new ItemController().saveItem(i1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

        setItemsToTable(new ItemController().getAllItems());

   }




    public void modifyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Item i1 = new Item(txtCode.getText(), txtDescription.getText(),
                    txtPackSize.getText(), Integer.valueOf(txtQtyOnHand.getText())
                    , Double.parseDouble(txtUnitPrice.getText()));


            if (new ItemController().updateItem(i1))
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.WARNING, e.getMessage());
        }
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemController().deleteItem(txtCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setItemsToTable(new ItemController().getAllItems());
    }

    public void selectAllOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemCode = txtCode.getText();

        Item i2= new ItemController().getItem(itemCode);
        if (i2==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i2);
        }


    }

    private void setData(Item i1) {
        txtCode.setText(i1.getItemCode());
        txtDescription.setText(i1.getDescription());
        txtPackSize.setText(i1.getPackSize());
        txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
    }





    public void moveToDescription(ActionEvent actionEvent) {
        txtDescription.requestFocus();
    }

    public void moveToPackSize(ActionEvent actionEvent) {
        txtPackSize.requestFocus();
    }

    public void moveToQtyOnHand(ActionEvent actionEvent) {
        txtQtyOnHand.requestFocus();
    }

    public void moveToUnitPrice(ActionEvent actionEvent) {
        txtUnitPrice.requestFocus();
    }
}
