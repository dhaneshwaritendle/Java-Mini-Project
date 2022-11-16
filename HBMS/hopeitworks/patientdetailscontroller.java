package com.example.hopeitworks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class patientdetailscontroller implements Initializable {
    @FXML
    public TextField usernameTextField10;
    @FXML
    public TextField usernameTextField11;
    @FXML
    public TextField usernameTextField12;
    @FXML
    public ChoiceBox<String> usernameTextField13;
    @FXML
    public TextField usernameTextField14;
    @FXML
    public TextField usernameTextField15;
    @FXML
    public DatePicker usernameTextField16;
    @FXML
    public TextField usernameTextField17;
    @FXML
    public TextField usernameTextField18;
    @FXML
    public Label Alertbox;
    @FXML
    private Label gender;

    private String[] g = {"Male" , "Female"};

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        usernameTextField13.getItems().addAll(g);
    }
    public void getGender(ActionEvent event){
        String mygender = usernameTextField13.getValue();
        gender.setText(mygender);
    }

    public void Enroll(ActionEvent event) throws IOException {

        try {
            Connection connect = DatabaseConnection.connectDb();
            //Creating Connection Object
            String Sql = "INSERT INTO `hbms_new`.`patient`(p_name,p_phone,p_age,p_gender,p_relative_name,p_relative_no,p_admission,b_id,p_lastname) VALUES(?,?,?,?,?,?,?,?,?)";

            //Preapared Statement
            PreparedStatement Pstatement = connect.prepareStatement(Sql);
            //Specifying the values of it's parameter

            Pstatement.setString(1, usernameTextField10.getText());
            Pstatement.setString(2, usernameTextField11.getText());
            Pstatement.setString(3, usernameTextField12.getText());
            Pstatement.setString(4, usernameTextField13.getValue());
            Pstatement.setString(5, usernameTextField14.getText());
            Pstatement.setString(6, usernameTextField15.getText());
            Pstatement.setString(7, String.valueOf(usernameTextField16.getValue()));
            Pstatement.setString(8, usernameTextField17.getText());
            Pstatement.setString(9, usernameTextField18.getText());



            int result = Pstatement.executeUpdate();
            Alert alert;
           if(usernameTextField11.getText().isEmpty()||usernameTextField10.getText().isEmpty()||usernameTextField13.getValue().isEmpty()||usernameTextField14.getText().isEmpty()){
                Alertbox.setText("Please Enter Complete Details!");
            }
//            else if(usernameTextField12.getText().equals("")||usernameTextField15.getText().equals("")||usernameTextField16.getValue().equals("")||usernameTextField17.getText().equals("") ){
//                Alertbox.setText("Please Enter Complete Details!");
//            }
            else{
                Parent NextParent = FXMLLoader.load(getClass().getResource("patient_infos.fxml"));
                Scene NextScene = new Scene(NextParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(NextScene);
                window.show();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
//        Parent NextParent = FXMLLoader.load(getClass().getResource("patient_infos.fxml"));
//        Scene NextScene = new Scene(NextParent);
//        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window.setScene(NextScene);
//        window.show();
    }
        @FXML
        public void onDashboardButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onPatientInfoButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onBedInfoButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    @FXML
    public void Logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load((getClass().getResource("loginform.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void onCancelButtonClick(ActionEvent event) throws IOException{
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_info.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

}