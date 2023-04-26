package com.manage.hslibrary.DTO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class MemberDTO {
    private String clientNUM;
    private String clientName;
    private String clientID;
    private String clientPhone;
    private String clientAddr;
    private String clientEmail;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date clientRegister;
    public MemberDTO(){}
    public MemberDTO(String ClientNUM, String ClientName, String ClientID, String ClientPhone,
                     String ClientAddr, String ClientEmail, Date ClientRegister)
    {
        //getting member data
        this.clientNUM=ClientNUM;
        this.clientName=ClientName;
        this.clientID=ClientID;
        this.clientPhone=ClientPhone;
        this.clientAddr=ClientAddr;
        this.clientEmail=ClientEmail;
        this.clientRegister=ClientRegister;
    }
    public MemberDTO(String ClientNUM, String ClientName, String ClientID,
                     String ClientPhone, String ClientAddr, String ClientEmail)
    {
        //adding new member data
        this.clientNUM=ClientNUM;
        this.clientName=ClientName;
        this.clientID=ClientID;
        this.clientPhone=ClientPhone;
        this.clientAddr=ClientAddr;
        this.clientEmail=ClientEmail;

    }

    public String getClientNUM() {return clientNUM;}
    public void setClientNUM(String clientNUM) {this.clientNUM = clientNUM;}
    public String getClientName() {return clientName;}
    public void setClientName(String clientName) {this.clientName = clientName;}
    public String getClientID() {return clientID;}
    public void setClientID(String clientID) {this.clientID = clientID;}
    public String getClientPhone() {return clientPhone;}
    public void setClientPhone(String clientPhone) {this.clientPhone = clientPhone;}
    public String getClientAddr() {return clientAddr;}
    public void setClientAddr(String clientAddr) {this.clientAddr = clientAddr;}
    public String getClientEmail() {return clientEmail;}
    public void setClientEmail(String clientEmail) {this.clientEmail = clientEmail;}
    public Date getClientRegister() {return clientRegister;}
    public void setClientRegister(Date clientRegister) {this.clientRegister = clientRegister;}

    @Override
    public String toString(){
        return "MemberDTO [clientNUM= "+clientNUM+", clientName= "+clientName+", clientID= "
                +clientID+", clientPhone= "+clientPhone+", clientAddr= "+clientAddr+ ", clientEmail= "+clientEmail+"]";
    }
}
