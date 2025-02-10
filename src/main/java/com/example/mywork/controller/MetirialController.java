package com.example.mywork.controller;

import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.MetirialDAO;
import com.example.mywork.dto.MetirialDTO;
import com.example.mywork.dto.tm.MetirialTM;
import com.example.mywork.DAO.custom.impl.MetirialDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;

public class MetirialController {

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private TableColumn<MetirialTM, String> colMetirialId;

    @FXML
    private TableColumn<MetirialTM, String> colName;

    @FXML
    private TableColumn<MetirialTM, String> colQty;

    @FXML
    private AnchorPane paneMetirial;

    @FXML
    private TableView<MetirialTM> tblMetirial;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblMetirialId;

    MetirialDAO metirialDAO = (MetirialDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.METIRIAL);

    public void initialize() {
        colMetirialId.setCellValueFactory(new PropertyValueFactory<>("metirialId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }
    private void refreshPage() throws SQLException {

        try {
            loadNextMetirialId();
            loadTableData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnSaveItem.setDisable(false);
        btnUpdateItem.setDisable(true);
        btnDeleteItem.setDisable(true);
        txtName.setText("");
        txtQty.setText("");
    }

    MetirialDAOImpl metirialModel = new MetirialDAOImpl();

    private void loadTableData() throws SQLException {
        ArrayList<MetirialDTO> metirialDTOS = metirialDAO.getAll();

        ObservableList<MetirialTM> metirialTMS = FXCollections.observableArrayList();


        for (MetirialDTO metirialDTO : metirialDTOS) {
            MetirialTM metirialTM = new MetirialTM(
                    metirialDTO.getMetirialId(),
                    metirialDTO.getName(),
                    metirialDTO.getQty()
            );
            metirialTMS.add(metirialTM);
        }

        tblMetirial.setItems(metirialTMS);
    }

    public void loadNextMetirialId() throws SQLException {
        String nextMetirialId = metirialDAO.getNextMetirialId();
        lblMetirialId.setText(nextMetirialId);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String metirialId = lblMetirialId.getText();
        String name = txtName.getText();
        String qty = txtQty.getText();

        txtName.setStyle(txtName.getStyle() + " -fx-text-fill:#7367F0;");
        txtQty.setStyle(txtQty.getStyle() + " -fx-text-fill:#7367F0");

        String namePattern = "^[A-Za-z ]+$";
        String qtyPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name...");
        }

        if (!isValidQty) {
            txtQty.setStyle(txtQty.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid qty...");
        }

        MetirialDTO dto = new MetirialDTO(metirialId , name , qty);

        boolean isDeleted = metirialDAO.delete(metirialId);

        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Metirial deleted...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to delete metirial...!").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String metirialId = lblMetirialId.getText();
        String name = txtName.getText();
        String qty = txtQty.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtQty.setStyle(txtQty.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String qtyPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name...");
        }

        if (!isValidQty) {
            txtQty.setStyle(txtQty.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid qty...");
        }

        MetirialDTO dto = new MetirialDTO(metirialId,name,qty);

        boolean isSaved = metirialDAO.save(dto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Metirial saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save metirial...!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String metirialId = lblMetirialId.getText();
        String name = txtName.getText();
        String qty = txtQty.getText();

        txtQty.setStyle(txtQty.getStyle() + ";-fx-border-color: #7367F0;");
        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String qtyPattern = "^[A-Za-z ]+$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidQty = qty.matches(qtyPattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name...");
        }
        if (!isValidQty) {
            txtQty.setStyle(txtQty.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid qty...");
        }

        MetirialDTO dto = new MetirialDTO(metirialId,name,qty);

        boolean isUpdate = metirialDAO.update(dto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Metirial updated...!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Fail to update metirial...!").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        MetirialTM metirialTM = tblMetirial.getSelectionModel().getSelectedItem();
        if (metirialTM != null) {
            lblMetirialId.setText(metirialTM.getMetirialId());
            txtName.setText(metirialTM.getName());
            txtQty.setText(metirialTM.getQty());
            btnSaveItem.setDisable(true);
            btnDeleteItem.setDisable(false);
            btnUpdateItem.setDisable(false);
        }
    }
}
