
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


    public class SignupController {
        @FXML
        private TextField tf_username;
        @FXML
        private TextField tf_firstname;
        @FXML
        private TextField tf_lastname;
        @FXML
        private PasswordField pf_password;
        @FXML
        private PasswordField pf_conpassword;
        @FXML
        private Label warning;
@FXML
private void onLoginPage(ActionEvent event) throws IOException {
    //switch scene to login page
    Parent root = FXMLLoader.load(getClass().getResource("loginform.fxml"));
    Scene scene = new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();
}
        @FXML
        private void onSignInButtonClick(ActionEvent event) {
            String username = tf_username.getText();
            String firstname = tf_firstname.getText();
            String lastname = tf_lastname.getText();
            String password = pf_password.getText();
            String conpassword = pf_conpassword.getText();
            //connection
            DatabaseConnection connectnow = new DatabaseConnection();
            Connection connectdb = connectnow.connectDb();
            //setting null
           PreparedStatement psinsert = null;
            PreparedStatement pscheck = null;
            ResultSet resultSet = null;
            //setting condition
            if (!tf_username.getText().isBlank() && !tf_firstname.getText().isBlank() && !tf_lastname.getText().isBlank() && !pf_password.getText().isBlank() && !pf_conpassword.getText().isBlank()) {
                try {
                    pscheck = connectdb.prepareStatement("select * from user where user_name = ?");
                    pscheck.setString(1, username);
                    resultSet = pscheck.executeQuery();
                    if (resultSet.isBeforeFirst()) {
                        warning.setText("YOU CANNOT USE THIS USERNAME.");
                    } else {//CONFIRM PASSWORD
                        if (pf_password.getText().equals(conpassword)){
                            psinsert = connectdb.prepareStatement("insert into user VALUES (?,?,?,?)");
                            psinsert.setString(1, username);
                            psinsert.setString(2, firstname);
                            psinsert.setString(3, lastname);
                            psinsert.setString(4, password);
                            psinsert.executeUpdate();

                            //alert button
                            Alert alert;
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("registration successful!");
                            alert.setContentText("Successfully registered");
                            //button_login.getScene().getWindow().hide();
                            alert.showAndWait();
                            Parent root = FXMLLoader.load(getClass().getResource("loginform.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            warning.setText("CONFIRM PASSWORD DOESN'T MATCH! ");
                        }
                    }//END OF CONFIRM PASSWORD
                } catch (SQLException | IOException ep) {
                    ep.printStackTrace();
                }
            }//END OF IF STATMENT TO CHECK THE CREDENTIALS
    else {
        warning.setText("ENTER ALL THE CREDENTIALS!");
            }
        }
    }


