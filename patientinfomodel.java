package com.example.hopeitworks;

import java.sql.Date;

public class patientinfomodel {

    Integer PatientId;
    String PatientName;
    Integer BedAssigned;
    String RoomName;
    Date AdmissionDate;

    public patientinfomodel(Integer PatientId, String PatientName, String RoomName, Integer BedAssigned, Date AdmissionDate) {
        this.PatientId = PatientId;
        this.PatientName = PatientName;
        this.BedAssigned=BedAssigned;
        this.RoomName = RoomName;
        this.AdmissionDate = AdmissionDate;
    }
    public Integer getPatientId() {
        return PatientId;
    }

    public void setPatientId(Integer patientId) {
        PatientId = patientId;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public Integer getBedAssigned() {
        return BedAssigned;
    }

    public void setBedAssigned(Integer bedAssigned) {
        BedAssigned = bedAssigned;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public Date getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        AdmissionDate = admissionDate;
    }
}