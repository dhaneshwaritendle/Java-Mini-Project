
package com.example.hopeitworks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;



public class LoginformController {

    @FXML
    private TextField usernameTextField;
    public static String username;
    @FXML
    private PasswordField passwordField;
    @FXML
    public void onLoginButtonClick(ActionEvent event) throws IOException {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDb = connectnow.connectDb();
        String verifylogin = "select count(1) from user where user_name = '" + usernameTextField.getText() + "' and user_password  = '" + passwordField.getText() + "'";

        Statement statement;
        try {
            statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifylogin);
            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    try {
                        username=usernameTextField.getText();
                       /*
                        Alert alert;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INFORMATION MESSAGE");
                        alert.setContentText("Successfully login");
                        button_login.getScene().getWindow().hide();
                        alert.showAndWait();
                        */

                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);

                        stage.setScene(scene);
                        stage.show();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    if (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                        Alert alert;
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error message");
                        alert.setHeaderText("null");
                        alert.setContentText("please fill all the details");
                        alert.showAndWait();
                    }
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
@FXML
    public void onSignUpButtonClick(ActionEvent event) throws IOException {
//switch scene to login page
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
