package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Item;
import model.Order;
import model.OrderDetail;
import tm.ItemTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageOrdersFormController extends OrderController {
    public AnchorPane manageOrdersContext;
    public Label lblDate;
    public Label lblTime;
    public ComboBox <String>cmbItemCode;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public Label txtTotal;
    public TextField txtQty;
    public TableView<ItemTM> tblListOfItems;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public  Label txtDiscount;
    public ComboBox <String> cmbCustomerId;
    public Label lblOrderId;
    public ComboBox cmbOrderId;
    private int cartSelectedRowForRemove=-1;

    public void initialize() {
        cmbOrderId.getItems().addAll("O-001","O-002","O-003","O-004","O-005","O-006");

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));

        try {
            loadCustomerIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadDateAndTime();
        try {
            loadItemCodes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });
        tblListOfItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new ItemController().getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
        }
    }


    private void loadItemCodes() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemCodes();
        cmbItemCode.getItems().addAll(itemIds);
    }


    private void loadDateAndTime() {
       
        Date date = new Date();
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


    public void backToCashierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierLogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageOrdersContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    ObservableList<ItemTM> obList= FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyForCustomer = Integer.parseInt(txtQty.getText());
        double total = qtyForCustomer * unitPrice;


        if (qtyOnHand<qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        ItemTM tm = new ItemTM(
                (String) cmbItemCode.getValue(),
                description,
                packSize,
                qtyForCustomer,
                unitPrice,
                total
        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            ItemTM temp = obList.get(rowNumber);
            ItemTM newTm = new ItemTM(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                    temp.getQty()+qtyForCustomer,
                    unitPrice,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblListOfItems.setItems(obList);
        calculateCost();
    }

    private void calculateCost() {
        double ttl = 0;
        for (ItemTM tm : obList
        ) {
            ttl += tm.getTotal();
        }
        txtTotal.setText(ttl + " /=");

        double discount=0;

        for (ItemTM tm:obList
        ) {
            discount+=tm.getTotal();
            if(ttl >= 1000){
                txtDiscount.setText(String.valueOf(ttl-(ttl/10)));
            } else if(ttl <= 1000){
                ttl=ttl;
            }
        }


    }

    private int isExists(ItemTM tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }


    public void deleteOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblListOfItems.refresh();
        }
    }

    public void openNewOrderOnAction(ActionEvent actionEvent) {

    }


    public void placeOrderFormOnAction(ActionEvent actionEvent) {
        ArrayList<Item> items= new ArrayList<>();
        double total=0;
        for (ItemTM tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new Item(tempTm.getItemCode(),
                    tempTm.getDescription(),
                    tempTm.getPackSize(),
                    tempTm.getQty(),
                    tempTm.getUnitPrice()));
        }

        Order order= new Order(lblOrderId.getText(), cmbCustomerId.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                items
        );

        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

