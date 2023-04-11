package com.manage.hslibrary.DTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Component
public class MemberDTO {
    private String staffNUM;
    private String staffPW;
    private String staffName;
    private String staffID;
    private String staffAddr;
    private String staffPhone;
    private String staffDeparture;

    public MemberDTO(String StaffNUM, String StaffPW, String StaffName, String StaffID,
                     String StaffAddr, String StaffPhone, String StaffDeparture)
    {
        //adding new staff data
        this.staffNUM=StaffNUM;
        this.staffPW=StaffPW;
        this.staffName=StaffName;
        this.staffID=StaffID;
        this.staffAddr=StaffAddr;
        this.staffPhone=StaffPhone;
        this.staffDeparture=StaffDeparture;
    }

    public MemberDTO(String StaffID, String StaffPW, String StaffName, String StaffDeparture){
        //getting staff data from
        this.staffID=StaffID;
        this.staffPW=StaffPW;
        this.staffName=StaffName;
        this.staffDeparture=StaffDeparture;
    }
    public MemberDTO(){}

    public String getStaffNUM(){return staffNUM;}
    public void setStaffNUM(String staffNUM){this.staffNUM=staffNUM;}
    public String getStaffPW(){return staffPW;}
    public void setStaffPW(String staffPW){this.staffPW=staffPW;}
    public String getStaffName(){return staffName;}
    public void setStaffName(String staffName){this.staffName=staffName;}
    public String getStaffID(){return staffID;}
    public void setStaffID(String staffID){this.staffID=staffID;}
    public String getStaffAddr(){return staffAddr;}
    public void setStaffAddr(String staffAddr){this.staffAddr=staffAddr;}
    public String getStaffPhone(){return staffPhone;}
    public void setStaffPhone(String staffPhone){this.staffPhone=staffPhone;}
    public String getStaffDeparture(){return staffDeparture;}
    public void setStaffDeparture(String staffDeparture){this.staffDeparture=staffDeparture;}
    @Override
    public String toString(){
        return "MemberDTO [staffNUM= "+staffNUM+", staffPW= "+staffPW+", staffName= "
                +staffName+", staffID= "+staffID+", staffAddr= "+staffAddr+", staffPhone= "
                +staffPhone+", staffDeparture= "+staffDeparture+"]";
    }

}
