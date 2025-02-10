package com.example.mywork.controller;
import com.example.mywork.DAO.DAOFactory;
import com.example.mywork.DAO.custom.EmployeeDAO;
import com.example.mywork.dto.EmployeeDTO;
import com.example.mywork.dto.tm.EmployeeTM;
import com.example.mywork.DAO.custom.impl.EmployeeDAOImpl;
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

public class EmployeeController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeeDTO, String> colAddress;

    @FXML
    private TableColumn<EmployeeDTO, String> colContact;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeDTO, String> colName;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private AnchorPane paneCustomer;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);


    public void initialize() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load employee id").show();
        }
    }


    private void refreshPage() throws SQLException {

        try {
            loadNextEmployeeId();
            loadTableData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnSave.setDisable(false);

        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    EmployeeDAOImpl employeeModel = new EmployeeDAOImpl();

    private void loadTableData() throws SQLException {
        ArrayList<EmployeeDTO> employeeDTOS = employeeDAO.getAll();

        ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();


        for (EmployeeDTO employeeDTO : employeeDTOS) {
            EmployeeTM employeeTM = new EmployeeTM(
                    employeeDTO.getEmployeeId(),
                    employeeDTO.getName(),
                    employeeDTO.getAddress(),
                    employeeDTO.getContact()
            );
            employeeTMS.add(employeeTM);
        }

        tblEmployee.setItems(employeeTMS);
    }

    public void loadNextEmployeeId() throws SQLException {
        String nextCustomerId = employeeDAO.getNextEmployeeId();
        lblEmployeeId.setText(nextCustomerId);
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtContact.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidAddress) {
            txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
        }

        EmployeeDTO dto = new EmployeeDTO(employeeId,name,address,phone);
        boolean isDeleted = employeeDAO.delete(employeeId);

        if (isDeleted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Delete employee...!").show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtContact.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidAddress) {
            txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
        }

        EmployeeDTO dto = new EmployeeDTO(employeeId,name,address,phone);

        boolean isSaved = employeeDAO.save(dto);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee saved...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to save employee...!").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String phone = txtContact.getText();

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #7367F0;");
        txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: #7367F0;");

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        if (!isValidName) {
            System.out.println(txtName.getStyle());
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            System.out.println("Invalid name.............");
//            return;
        }

        if (!isValidAddress) {
            txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: red;");
//            return;
        }

        if (!isValidPhone) {
            txtContact.setStyle(txtContact.getStyle() + ";-fx-border-color: red;");
        }

        EmployeeDTO dto = new EmployeeDTO(employeeId,name,address,phone);

        boolean isUpdted = employeeDAO.update(dto);
        if (isUpdted) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee Updated...!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to Update employee...!").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        EmployeeTM employeeTM = tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTM != null) {
            lblEmployeeId.setText(employeeTM.getEmployeeId());
            txtName.setText(employeeTM.getName());
            txtAddress.setText(employeeTM.getAddress());
            txtContact.setText(employeeTM.getContact());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }

    }

}
