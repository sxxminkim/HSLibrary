package com.manage.hslibrary.DTO;

import org.springframework.stereotype.Repository;

@Repository
public class UserDTO {
    private String clientNUM;
    private String clientName;
    private String clientID;
    private String clientPhone;
    private String clientAddre;
    private String clientEmail;
    private String clientRegisterdate;
    public UserDTO(){}
    public UserDTO(String ClientNUM, String ClientName, String ClientID, String ClientPhone,
                   String ClientAddre, String ClientEmail, String ClientRegisterdate)
    {
        //adding new user data
        this.clientNUM=ClientNUM;
        this.clientName=ClientName;
        this.clientID=ClientID;
        this.clientPhone=ClientPhone;
        this.clientAddre=ClientAddre;
        this.clientEmail=ClientEmail;
        this.clientRegisterdate=ClientRegisterdate;
    }
    public UserDTO(String ClientNUM, String ClientName, String CleintPhone, String ClientEmail)
    {
        //getting user data from
        this.clientNUM=ClientNUM;
        this.clientName=ClientName;
        this.clientPhone=CleintPhone;
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
    public String getClientAddre() {return clientAddre;}
    public void setClientAddre(String clientAddre) {this.clientAddre = clientAddre;}
    public String getClientEmail() {return clientEmail;}
    public void setClientEmail(String clientEmail) {this.clientEmail = clientEmail;}
    public String getClientRegisterdate() {return clientRegisterdate;}
    public void setClientRegisterdate(String clientRegisterdate) {this.clientRegisterdate = clientRegisterdate;}

    @Override
    public String toString(){
        return "UserDTO [clientNUM= "+clientNUM+", clientName= "+clientName+", clientID= "
                +clientID+", clientPhone= "+clientPhone+", clientAddre= "+clientAddre+
                ", clientEmail= "+clientEmail+"clientRegisterdate= "+clientRegisterdate+"]";
    }
}
