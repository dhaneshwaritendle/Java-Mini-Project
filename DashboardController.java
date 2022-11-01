
package com.example.hopeitworks;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class DashboardController extends LoginformController implements Initializable {
    private int occupancy, unoccupancy;
    private int occ_general,occ_icu,occ_emergency,occ_private;
    private int vac_general,vac_icu,vac_emergency,vac_private;
    @FXML
    Label label;
    @FXML
    Label AdmitLabel;
    @FXML
    Button dashboard;
    @FXML
    private PieChart piechart;
    @FXML
    private StackedBarChart stackedBarChart;

    public void setAdmitNumber() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connectdb = databaseConnection.connectDb();
        Statement st = null;
        st = connectdb.createStatement();
        String query = "select count(*) from patient where p_allotment_status=1;";
        ResultSet rs=st.executeQuery(query);
        rs.next();
        //displays number of rows returned after query execution
        AdmitLabel.setText(String.valueOf(rs.getInt("count(*)")));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            setAdmitNumber();
            //setBarChart();
            setPiechart();
            dashboard.setStyle("-fx-background-color: #f4f7f7; ");
            usernameDisplay();
            setStackedBarChart();


        } catch (SQLException e) {
            System.out.println("error in loading dashboard page!");
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public void usernameDisplay()
    {
        label.setText(username);
    }
    private void setStackedBarChart() throws SQLException {
        //database connection

        //stacked bar graph

        final XYChart.Series<String,Number>dataSeries1 = new XYChart.Series();
        dataSeries1.setName("occupied");
        occ_general=DatabaseConnection.getocc_general();
        occ_icu=DatabaseConnection.getocc_icu();
        occ_emergency=DatabaseConnection.getocc_emergency();
        occ_private=DatabaseConnection.getocc_private();
        dataSeries1.getData().add(new XYChart.Data("General", occ_general));
        dataSeries1.getData().add(new XYChart.Data("ICU", occ_icu));
        dataSeries1.getData().add(new XYChart.Data("Emergency", occ_emergency));
        dataSeries1.getData().add(new XYChart.Data("Private", occ_private));



        final XYChart.Series<String,Number>dataSeries2 = new XYChart.Series();
        dataSeries2.setName("vacant");
        vac_general=DatabaseConnection.getvac_general();
        vac_icu =DatabaseConnection.getvac_icu();
        vac_emergency=DatabaseConnection.getvac_emergency();
        vac_private=DatabaseConnection.getvac_private();
        dataSeries2.getData().add(new XYChart.Data("General", vac_general));
        dataSeries2.getData().add(new XYChart.Data("ICU", vac_icu));
        dataSeries2.getData().add(new XYChart.Data("Emergency", vac_emergency));
        dataSeries2.getData().add(new XYChart.Data("Private", vac_private));

        //stackedBarChart.setMinWidth(30);
        stackedBarChart.getData().addAll(dataSeries1,dataSeries2);
    }
    private void setPiechart() throws SQLException {
      //
        occupancy = DatabaseConnection.getOccupancy();
        unoccupancy = DatabaseConnection.getUnoccupancy();
        PieChart.Data a = new PieChart.Data("OCCUPIED", occupancy);
        PieChart.Data b = new PieChart.Data("VACANT", unoccupancy);

        piechart.getData().addAll(a, b);
        piechart.setLabelLineLength(10);
        piechart.setLegendSide(Side.BOTTOM);


    }

    public void Dashboard(ActionEvent event) throws IOException {
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

    public void Patientinfo(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("patients_infotable.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }

    public void Logout(ActionEvent event) throws IOException {
        Parent NextParent = FXMLLoader.load(getClass().getResource("loginform.fxml"));
        Scene NextScene = new Scene(NextParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(NextScene);
        window.show();
    }
}












