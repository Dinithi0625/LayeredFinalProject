package com.example.mywork.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainLayoutcontroller {

        @FXML
        private AnchorPane MainLayot;

        @FXML
        private AnchorPane content;

        @FXML
        private Button butonCustomer;

        @FXML
        private Button buttonBack;

        @FXML
        private Button buttonBatch;

        @FXML
        private Button buttonOrder;

        @FXML
        private Button buttonItem;

        @FXML
        private Button buttonPyment;

        @FXML
        private Button buttonStock;

        @FXML
        private Button buttonSupplier;

        @FXML
        private Pane paneSide;

        @FXML
        void NavigateBatchOnAction(ActionEvent event) {

        }

        @FXML
        void NavigateCustomerOnAction(ActionEvent event) throws IOException {

                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/Customer.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);

        }

        @FXML
        void NavigateOrderOnAction(ActionEvent event) throws IOException {
                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/Orders.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);
        }


        @FXML
        void NavigateItemOnAction(ActionEvent event) throws IOException {
                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/product.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);

        }

        @FXML
        void NavigatePaymentOnAction(ActionEvent event) throws IOException {
                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/ViewOrders.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);

        }

        @FXML
        void NavigateMetirialOnAction(ActionEvent event) throws IOException {
                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/Metirial.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);

        }

        @FXML
        void buttonBackOnAction(ActionEvent event) throws IOException {
                AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
                MainLayot.getChildren().clear();
                MainLayot.getChildren().add(load);

        }


        public void NavigateEmployeeOnAction(ActionEvent actionEvent) throws IOException {
                AnchorPane customerPane = FXMLLoader.load(getClass().getResource("/view/Employee.fxml"));

                // Clear existing content in the 'content' AnchorPane
                content.getChildren().clear();

                // Add the loaded customerPane to the 'content' AnchorPane
                content.getChildren().add(customerPane);

        }
}