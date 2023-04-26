package com.manage.hslibrary.DTO;

import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class RentDTO {
    private String rentalNUM;
    private String bookID;
    private String videoID;
    private String clientNUM;
    private String vid_roomNUM;
    private Date rental_start;
    private Date rental_end;
    public RentDTO(String RentalNUM, String BookID, String VideoID, String ClientNUM, String Vid_roomNUM){
        this.rentalNUM=RentalNUM;
        this.bookID=BookID;
        this.videoID=VideoID;
        this.clientNUM=ClientNUM;
        this.vid_roomNUM=Vid_roomNUM;
    }
    public RentDTO(String RentalNUM, String BookID, String VideoID,
                   String ClientNUM, String Vid_roomNUM, Date Rental_start, Date Rental_end)
    {
        this.rentalNUM=RentalNUM;
        this.bookID=BookID;
        this.videoID=VideoID;
        this.clientNUM=ClientNUM;
        this.vid_roomNUM=Vid_roomNUM;
        this.rental_start=Rental_start;
        this.rental_end=Rental_end;
    }

    public String getRentalNUM() {return rentalNUM;}
    public void setRentalNUM(String rentalNUM) {this.rentalNUM = rentalNUM;}
    public String getBookID() {return bookID;}
    public void setBookID(String bookID) {this.bookID = bookID;}
    public String getVideoID() {return videoID;}
    public void setVideoID(String videoID) {this.videoID = videoID;}
    public String getClientNUM() {return clientNUM;}
    public void setClientNUM(String clientNUM) {this.clientNUM = clientNUM;}
    public String getVid_roomNUM() {return vid_roomNUM;}
    public void setVid_roomNUM(String vid_roomNUM) {this.vid_roomNUM = vid_roomNUM;}
    public Date getRental_start() {return rental_start;}
    public void setRental_start(Date rental_start) {this.rental_start = rental_start;}
    public Date getRental_end() {return rental_end;}
    public void setRental_end(Date rental_end) {this.rental_end = rental_end;}

    public RentDTO(){}
}
