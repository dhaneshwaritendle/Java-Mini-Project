
package com.example.hopeitworks;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class PatientTableController implements Initializable {
    @FXML
    private Button patient_info;
@FXML
private Label AdmitPatient;
    @FXML
    private Label DischargePatient;
    @FXML
    private Label TotalPatient;
    @FXML
    private TableView<patientinfomodel> patientTableView;
    @FXML
    private TableColumn<patientinfomodel, Integer> patientIdTableColumn;
    @FXML
    private TableColumn<patientinfomodel, String> patientNameTableColumn;
   @FXML
   private TableColumn<patientinfomodel, Integer>  bedAssignedTableColumn;
    @FXML
    private TableColumn<patientinfomodel, String> roomAssignedTableColumn;
    @FXML
    private TableColumn<patientinfomodel, Date> admissionDateTableColumn;

    ObservableList<patientinfomodel> listM;

    public void initialize(URL url, ResourceBundle rb) {

        try {
            setPatientTableView();
            setAdmitNumber();
            setdischargeNumber();
            setTotalNumber();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        patient_info.setStyle("-fx-background-color: #f4f7f7; ");
    }
    public void setPatientTableView(){
        patientIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        patientNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        bedAssignedTableColumn.setCellValueFactory(new PropertyValueFactory<>("BedAssigned"));
        roomAssignedTableColumn.setCellValueFactory(new PropertyValueFactory<>("RoomName"));
        admissionDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("AdmissionDate"));

        listM = DatabaseConnection.getData();
        patientTableView.setItems(listM);
    }
    public void enrollpatient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Patientenroll.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onDashboardButtonClick(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    public void bedinfo(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("bed_ui.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
    public void setAdmitNumber() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st = null;
        st = connectdb.createStatement();
        String query = "select count(*) from patient where p_allotment_status=1;";
        ResultSet rs=st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        AdmitPatient.setText(String.valueOf(rs.getInt("count(*)")));
    }
    public void setdischargeNumber() throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st = null;
        st = connectdb.createStatement();
        String query = "select count(*) from patient where p_allotment_status=0;";
        ResultSet rs=st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        DischargePatient.setText(String.valueOf(rs.getInt("count(*)")));
    }
    public void setTotalNumber() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st = null;
        st = connectdb.createStatement();
        String query = "select count(*) from patient;";
        ResultSet rs=st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        TotalPatient.setText(String.valueOf(rs.getInt("count(*)")));
    }
    @FXML
    public TextField delete;
    @FXML
    public Label Alertbox;
    @FXML
    public void onDelete(ActionEvent event) {
        String id;
        id = delete.getText();
        if(id.equals("")) {
            Alertbox.setText("Patient ID Field is Empty!");
        } else {
            try {
                DatabaseConnection.delete(Integer.valueOf(id));
                Alertbox.setText("Patient Deleted Successfully!");
            } catch (SQLException ex) {
                Alertbox.setText("Patient Deleted Successfully");
                //System.out.println(ex);
            }
        }

    }
}

