package com.manage.hslibrary.DTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class VideoRentDTO {
    private String videoRentalNUM;
    private String videoID;
    private String clientNUM;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date videoRental_start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date videoRental_end;

    public VideoRentDTO(String VideoRentalNUM, String VideoID, String ClientNUM, Date VideoRental_start,
                       Date VideoRental_end)
    {
        //getting book rental data
        this.videoRentalNUM=VideoRentalNUM;
        this.videoID=VideoID;
        this.clientNUM=ClientNUM;
        this.videoRental_start=VideoRental_start;
        this.videoRental_end=VideoRental_end;
    }

    public VideoRentDTO(String VideoRentalNUM, String VideoID, String ClientNUM)
    {
        //adding book rental data
        this.videoRentalNUM=VideoRentalNUM;
        this.videoID=VideoID;
        this.clientNUM=ClientNUM;
    }
    public VideoRentDTO(){}
    public String getVideoRentalNUM() {return videoRentalNUM;}

    public void setVideoRentalNUM(String videoRentalNUM) {this.videoRentalNUM = videoRentalNUM;}

    public String getVideoID() {return videoID;}

    public void setVideoID(String videoID) {this.videoID = videoID;}

    public String getClientNUM() {return clientNUM;}

    public void setClientNUM(String clientNUM) {this.clientNUM = clientNUM;}

    public Date getVideoRental_start() {return videoRental_start;}

    public void setVideoRental_start(Date videoRental_start) {this.videoRental_start = videoRental_start;}

    public Date getVideoRental_end() {return videoRental_end;}

    public void setVideoRental_end(Date videoRental_end) {this.videoRental_end = videoRental_end;}

    @Override
    public String toString(){
        return "VideoRentDTO [videoRentalNUM= "+videoRentalNUM+", videoID= "+videoID+", clientNUM= "+clientNUM+"]";
    }
}
