package com.example.mywork.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane LoginPage;

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonRegisterHere;

    @FXML
    private Text labelPassword01;

    @FXML
    private Label labelUserName01;

    @FXML
    private Text textWelcome;

    @FXML
    private Text txtAskAccount;

    @FXML
    private TextField txtfield01;

    @FXML
    private PasswordField txtfield02;

    @FXML
    void ButtonRegisterHereOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));
        LoginPage.getChildren().clear();
        LoginPage.getChildren().add(load);
    }

    private String username = "Dinithi";
    private String password = "1234";

    @FXML
    void buttonLoginOnAction(ActionEvent event) throws IOException {
        String Username = txtfield01.getText();
        String Password = txtfield02.getText();

        if(Username.equals(username) && Password.equals(password)){
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/MainLayout.fxml"));
            LoginPage.getChildren().clear();
            LoginPage.getChildren().add(load);
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
        }

    }



}

