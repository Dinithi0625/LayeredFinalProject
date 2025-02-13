package com.example.mywork.controller;
import com.example.mywork.bo.BOFactory;
import com.example.mywork.bo.custom.ProductBO;
import com.example.mywork.db.DBConnection;
import com.example.mywork.dto.ProductDTO;
import com.example.mywork.dto.tm.ProductTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
public class ProductController {

    @FXML
    private TextField txtQty;
    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private Button btnReport;

    @FXML
    private TableColumn<ProductTM , String> colDescription;

    @FXML
    private TableColumn<ProductTM , Integer> tblQty;

    @FXML
    private TableColumn<ProductTM , String> colItemId;

    @FXML
    private TableColumn<ProductTM, String> colName;

    @FXML
    private TableColumn<ProductTM, Double> colPrice;

    @FXML
    private Label lblProductId;

    @FXML
    private AnchorPane paneItem;

    @FXML
    private TableView<ProductTM> tblItem;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRODUCT);

    public void initialize() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load product id").show();
        }
    }

    private void refreshPage() throws SQLException {
        try {
             loadNextProductId();
            loadTableData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnDeleteItem.setDisable(true);
        btnSaveItem.setDisable(false);
        btnUpdateItem.setDisable(true);

        txtName.setText("");
        txtPrice.setText("");
        txtDescription.setText("");
    }

   // ProductDAOImpl productModel = new ProductDAOImpl();

    private void loadTableData() throws SQLException {
        ArrayList<ProductDTO> productDTOS = productBO.getAll();
        ObservableList<ProductTM> productTMS = FXCollections.observableArrayList();
        for (ProductDTO productDTO : productDTOS) {
            ProductTM productTM = new ProductTM(
                    productDTO.getProductId(),
                    productDTO.getName(),
                    productDTO.getPrice(),
                    productDTO.getDescription(),
                    productDTO.getQty()
            );
            productTMS.add(productTM);
        }
        tblItem.setItems(productTMS);
    }

    public void loadNextProductId() throws SQLException {
        String nextProductId = productBO.getNextProductId();
        lblProductId.setText(nextProductId);
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) throws SQLException {
        String productId = lblProductId.getText();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtPrice.setStyle(txtPrice.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String pricePattern = "^[0-9]+(\\.[0-9]{1,2})?$";
        String descriptionPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidDescription = description.matches(descriptionPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidDescription) {
            txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: red;");
        }

        ProductDTO dto = new ProductDTO(productId, name, price, description,qty);
        boolean isDeleted = productBO.delete(productId);

        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Product Deleted...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Delete product...!").show();
        }
    }

    @FXML
    void btnSaveItemOnAction(ActionEvent event) throws SQLException {
        String productId = lblProductId.getText();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtPrice.setStyle(txtPrice.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String pricePattern = "^[0-9]+(\\.[0-9]{1,2})?$";
        String descriptionPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidDescription = description.matches(descriptionPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidDescription) {
            txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: red;");
        }

        ProductDTO dto = new ProductDTO(productId, name, price, description,qty);
        boolean isSaved = productBO.save(dto);

        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Product Saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Save product...!").show();
        }
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) throws SQLException {
        String productId = lblProductId.getText();
        String name = txtName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtPrice.setStyle(txtPrice.getStyle() + ";-fx-border-color: #7367F0;");
        txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String pricePattern = "^[0-9]+(\\.[0-9]{1,2})?$";
        String descriptionPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidDescription = description.matches(descriptionPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }
        if (!isValidDescription) {
            txtDescription.setStyle(txtDescription.getStyle() + ";-fx-border-color: red;");
        }

        ProductDTO dto = new ProductDTO(productId, name, price, description,qty);
        boolean isDeleted = productBO.update(dto);

        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Product Deleted...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Delete product...!").show();
        }
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/product_report.jrxml"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ProductTM productTM = tblItem.getSelectionModel().getSelectedItem();
        if (productTM != null) {
            lblProductId.setText(productTM.getProductId());
            txtName.setText(productTM.getName());
            txtPrice.setText(""+productTM.getPrice());
            txtDescription.setText(productTM.getDescription());
            txtQty.setText(""+tblItem.getSelectionModel().getSelectedItem().getQty());

            btnSaveItem.setDisable(true);
            btnDeleteItem.setDisable(false);
            btnUpdateItem.setDisable(false);
        }
    }

}
