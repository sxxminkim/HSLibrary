package com.manage.hslibrary.DTO;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDTO {
    private String staffNUM;
    private String staffPW;
    private String staffName;
    private String staffID;
    private String staffAddr;
    private String staffPhone;
    private String staffDepartment;

    public StaffDTO(String StaffNUM, String StaffPW, String StaffName, String StaffID,
                    String StaffAddr, String StaffPhone, String StaffDepartment)
    {
        //adding new staff data
        this.staffNUM=StaffNUM;
        this.staffPW=StaffPW;
        this.staffName=StaffName;
        this.staffID=StaffID;
        this.staffAddr=StaffAddr;
        this.staffPhone=StaffPhone;
        this.staffDepartment=StaffDepartment;
    }


    public StaffDTO(String StaffNUM, String StaffID, String StaffPW, String StaffName, String StaffDepartment){
        //getting staff data from
        this.staffNUM=StaffNUM;
        this.staffID=StaffID;
        this.staffPW=StaffPW;
        this.staffName=StaffName;
        this.staffDepartment=StaffDepartment;
    }
    public StaffDTO(){}

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
    public String getStaffDepartment(){return staffDepartment;}
    public void setStaffDepartment(String staffDepartment){this.staffDepartment=staffDepartment;}
    @Override
    public String toString(){
        return "StaffDTO [staffNUM= "+staffNUM+", staffPW= "+staffPW+", staffName= "
                +staffName+", staffID= "+staffID+", staffAddr= "+staffAddr+", staffPhone= "
                +staffPhone+", staffDepartment= "+staffDepartment+"]";
    }

}
