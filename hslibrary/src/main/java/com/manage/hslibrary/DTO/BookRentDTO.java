package com.manage.hslibrary.DTO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class BookRentDTO {
    private String bookRentalNUM;
    private String bookID;
    private String clientNUM;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bookRental_start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bookRental_end;

    public BookRentDTO(String BookRentalNUM, String BookID, String ClientNUM, Date BookRental_start,
                       Date BookRental_end)
    {
        //getting book rental data
        this.bookRentalNUM=BookRentalNUM;
        this.bookID=BookID;
        this.clientNUM=ClientNUM;
        this.bookRental_start=BookRental_start;
        this.bookRental_end=BookRental_end;
    }

    public BookRentDTO(String BookRentalNUM, String BookID, String ClientNUM)
    {
        //adding book rental data
        this.bookRentalNUM=BookRentalNUM;
        this.bookID=BookID;
        this.clientNUM=ClientNUM;
    }
    public BookRentDTO(){}

    public String getBookRentalNUM() {return bookRentalNUM;}

    public void setBookRentalNUM(String bookRentalNUM) {this.bookRentalNUM = bookRentalNUM;}

    public String getBookID() {return bookID;}

    public void setBookID(String bookID) {this.bookID = bookID;}

    public String getClientNUM() {return clientNUM;}

    public void setClientNUM(String clientNUM) {this.clientNUM = clientNUM;}

    public Date getBookRental_start() {return bookRental_start;}

    public void setBookRental_start(Date bookRental_start) {this.bookRental_start = bookRental_start;}

    public Date getBookRental_end() {return bookRental_end;}

    public void setBookRental_end(Date bookRental_end) {this.bookRental_end = bookRental_end;}

    @Override
    public String toString(){
        return "BookRentDTO [bookRentalNUM= "+bookRentalNUM+", bookID= "+bookID+", clientNUM= "+clientNUM+"]";
    }
}
