package com.example.mywork.controller;

import com.example.mywork.DAO.custom.impl.ViewOrdersDAOImpl;
import com.example.mywork.dto.tm.OrderProductTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewOrdersController {

    @FXML
    private TableColumn<OrderProductTM, String> colDate;

    @FXML
    private TableColumn<OrderProductTM, String> colOrderId;

    @FXML
    private TableColumn<OrderProductTM, Double> colPrice;

    @FXML
    private TableColumn<OrderProductTM, String> colProductId;

    @FXML
    private TableColumn<OrderProductTM, Integer> colQty;

    @FXML
    private AnchorPane paneOrdersView;

    @FXML
    private TableView<OrderProductTM> tblOrdersView;

    private ObservableList<OrderProductTM> orderList = FXCollections.observableArrayList();

    private final ViewOrdersDAOImpl viewOrdersModel = new ViewOrdersDAOImpl();

    @FXML
    public void initialize() {
        // Map TableColumn with OrderProductTM fields
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load data into the TableView
        loadOrderData();
    }

    private void loadOrderData() {
        try {
            orderList.clear();
            orderList.addAll(viewOrdersModel.getOrderData()); // Load data from the model
            tblOrdersView.setItems(orderList);
        } catch (Exception e) {
            e.printStackTrace();
            // Add proper error handling here
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        OrderProductTM selectedOrder = tblOrdersView.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            System.out.println("Selected Order: " + selectedOrder);
        }
    }
}
