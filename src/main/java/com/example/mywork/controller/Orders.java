package com.example.mywork.controller;

import com.example.mywork.bo.BOFactory;
import com.example.mywork.bo.custom.OrderDetailBO;
import com.example.mywork.bo.custom.OrdersBO;
import com.example.mywork.dao.custom.impl.OrdersDAOImpl;
import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.tm.CartTM;
import com.example.mywork.entity.Order;
import com.example.mywork.entity.OrderDetail;
import com.example.mywork.entity.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Orders implements Initializable {

    @FXML
    private TextField txtBalance;
    private OrdersDAOImpl ordersModel;

    @FXML
    public Button btnPlaceOrders;

    @FXML
    private TextField txtPayPrice;

    @FXML
    private TextField txtTotPrice;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private ComboBox<String> cmbProdctId;

    @FXML
    private TableColumn<CartTM, Integer> colQty;

    @FXML
    private TableColumn<CartTM, Double> colAmount;

    @FXML
    private TableColumn<CartTM, String> colCustomerId;


    @FXML
    private TableColumn<CartTM, String> colOrderId;

    @FXML
    private TableColumn<CartTM, String> colPaymentId;

    @FXML
    private TableColumn<CartTM, String> colProductId;

    @FXML
    private AnchorPane paneOrders;

    @FXML
    private TableView<CartTM> tblOrder;


    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Button btnBill;

    OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDER);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERPRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // ordersModel = (OrdersDAOImpl) new OrdersDAOImpl();
        setCellValues();
        try {
            refreshPage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValues() {
        
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        loadTablesOrders();
    }

    private void loadTablesOrders() {
        ObservableList<CartTM> osli = FXCollections.observableArrayList();
        try {
            List<CartTM> sli = ordersBO.loadtDetails();
            osli.addAll(sli);
            double price = ordersBO.getAllPrice();
            txtTotPrice.setText(""+price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblOrder.setItems(osli);
    }

    private void refreshPage() throws SQLException {
        String ii = orderDetailBO.getNextOrderId();
        txtOrderId.setText(ii);
        txtDate.setText(LocalDate.now().toString());

        loadCustomerId();
        loadProductId();
        loadPaymentMethods();

        // reset ui fields
        cmbPaymentMethod.getSelectionModel().clearSelection();
        cmbCustomerId.getSelectionModel().clearSelection();
        cmbProdctId.getSelectionModel().clearSelection();
        txtQty.setText("");
        txtUnitPrice.setText("");
    }

    private void loadCustomerId() throws SQLException {
        ArrayList<String> customerIds = ordersBO.getAllICustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmbCustomerId.setItems(observableList);
    }

    private void loadProductId() throws SQLException {
        ArrayList<String> productIds = ordersBO.getAllIProductIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(productIds);
        cmbProdctId.setItems(observableList);
    }

    private void loadPaymentMethods() throws SQLException {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Card", "Cash");
        cmbPaymentMethod.setItems(paymentMethods);
    }

    @FXML
    void buttonAddToCartOnAction(ActionEvent event) {
        String selectedProductId = cmbProdctId.getValue();
        String selectedCustomerId = cmbCustomerId.getValue();
        String selectedPaymentMethod = cmbPaymentMethod.getValue();
        String ss = txtOrderId.getText();
        String date = txtDate.getText();

            if (selectedProductId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select product..!").show();
                return;
            }

            if (selectedCustomerId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select Customer...").show();
                return;
            }

        String cartQtyString = txtQty.getText();
        String qtyPattern = "^[0-9]+$";

        if (!cartQtyString.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
            return;
        }

        int cartQty = Integer.parseInt(cartQtyString);
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double total = unitPrice * cartQty;

        Order ordersDTO = new Order(ss,date,selectedCustomerId);
        OrderDetail orderDetailDTO =new OrderDetail(ss,selectedProductId,date,cartQty,unitPrice);

//transaction eke insertAll
        try {
            String resp = ordersBO.insertAll(ordersDTO,orderDetailDTO,selectedProductId);
            loadTablesOrders();
            refreshPage();
            JOptionPane.showMessageDialog(null, "Save Status: " + resp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//transaction
    @FXML
    void buttonPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String ordId =txtOrderId.getText();
        double price = Double.parseDouble(txtTotPrice.getText());
        String date = txtDate.getText();

        Payment paymentDTO = new Payment(price,date,ordId);
        try {
            String rsp = ordersModel.insertPayment(paymentDTO);
            loadTablesOrders();
            refreshPage();
            JOptionPane.showMessageDialog(null, "Save Status: " + rsp, "Save Status", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionQty(ActionEvent event) {
        String  mm = cmbProdctId.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        try {
            double price = ordersBO.getpriceqty(mm);
            txtUnitPrice.setText(""+(price*qty));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void OnActionPayPrice(ActionEvent event) {
        double pp = Double.parseDouble(txtTotPrice.getText());
        double pay = Double.parseDouble(txtPayPrice.getText());
        txtBalance.setText(""+(pay-pp));
    }
    @FXML
    void buttonBillOnAction(ActionEvent event) {
        CartTM cartTM = tblOrder.getSelectionModel().getSelectedItem();
        if (cartTM == null) {
            return;
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/OrdersReport.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_CustomerId", cartTM.getCustomerId());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
    }

    public void cmbProductOnAction(ActionEvent actionEvent) {

    }
}
