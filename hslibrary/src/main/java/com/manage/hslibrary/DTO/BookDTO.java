package com.manage.hslibrary.DTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class BookDTO {
    private String bookID;
    private String bookName;
    private String bookWriter;
    private String bookGenre;
    private String bookCompany;
    private String bookISBN;
    private String bookYear;
    private String bookEdition;
    private String bookVolume;
    private String bookIssue;
    private String bookSummary;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bookRegister;
    public BookDTO(){}


    public BookDTO(String BookID, String BookName, String BookWriter, String BookGenre, String BookCompany, String BookISBN,
                   String BookYear, String BookEdition, String BookVolume, String BookIssue,
                   String BookSummary, Date BookRegister)
    {
        //getting book data
        this.bookID=BookID;
        this.bookName=BookName;
        this.bookWriter=BookWriter;
        this.bookGenre=BookGenre;
        this.bookCompany=BookCompany;
        this.bookISBN=BookISBN;
        this.bookYear=BookYear;
        this.bookEdition=BookEdition;
        this.bookVolume=BookVolume;
        this.bookIssue=BookIssue;
        this.bookSummary=BookSummary;
        this.bookRegister=BookRegister;
    }
    public BookDTO(String BookID, String BookName, String BookWriter, String BookGenre, String BookCompany,
                   String BookISBN, String BookYear, String BookEdition, String BookVolume, String BookIssue,
                    String BookSummary)
    {
        //adding book data
        this.bookID=BookID;
        this.bookName=BookName;
        this.bookWriter=BookWriter;
        this.bookGenre=BookGenre;
        this.bookCompany=BookCompany;
        this.bookISBN=BookISBN;
        this.bookYear=BookYear;
        this.bookEdition=BookEdition;
        this.bookVolume=BookVolume;
        this.bookIssue=BookIssue;
        this.bookSummary=BookSummary;
    }

    public String getBookID(){return bookID;}
    public void setBookID(String bookID){this.bookID=bookID;}
    public String getBookName(){return bookName;}
    public void setBookName(String bookName){this.bookName=bookName;}
    public String getBookWriter(){return bookWriter;}
    public void setBookWriter(String bookWriter){this.bookWriter=bookWriter;}
    public String getBookGenre() {return bookGenre;}
    public void setBookGenre(String bookGenre) {this.bookGenre = bookGenre;}
    public String getBookCompany(){return bookCompany;}
    public void setBookCompany(String bookCompany){this.bookCompany=bookCompany;}
    public String getBookISBN(){return bookISBN;}
    public void setBookISBN(String bookISBN){this.bookISBN=bookISBN;}
    public String getBookYear(){return bookYear;}
    public void setBookYear(String bookYear){this.bookYear=bookYear;}
    public String getBookEdition(){return bookEdition;}
    public void setBookEdition(String bookEdition){this.bookEdition=bookEdition;}
    public String getBookVolume(){return bookVolume;}
    public void setBookVolume(String bookVolume){this.bookVolume=bookVolume;}
    public String getBookIssue(){return bookIssue;}
    public void setBookIssue(String bookIssue){this.bookIssue=bookIssue;}
    public String getBookSummary() {return bookSummary;}
    public void setBookSummary(String bookSummary) {this.bookSummary = bookSummary;}

    public Date getBookRegister() {return bookRegister;}

    public void setBookRegister(Date bookRegister) {this.bookRegister = bookRegister;}

    @Override
    public String toString(){
        return "BookDTO [bookID= "+bookID+", bookName= "+bookName+", bookWriter= "+bookWriter+
        ", bookGenre= "+bookGenre+", bookCompany= "+bookCompany+", bookISBN= "+bookISBN+
        ", bookYear= "+bookYear+ ", bookEdition= "+bookEdition+", bookVolume= "+bookVolume+
        ", bookIssue= "+bookIssue+ ", bookSummary= "+bookSummary+"]";
    }

}
