package com.manage.hslibrary.DTO;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class VidRoomRentDTO {
    private String videoRoomRentalNUM;
    private String clientNUM;
    private String vid_roomNUM;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date videoRoomRental_start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date videoRoomRental_end;
    public VidRoomRentDTO(){}
    public VidRoomRentDTO(String VideoRoomRentalNUM, String ClientNUM,
                          String Vid_roomNUM, Date VideoRoomRental_start,
                          Date VideoRoomRental_end)
    {
        this.videoRoomRentalNUM=VideoRoomRentalNUM;
        this.clientNUM=ClientNUM;
        this.vid_roomNUM=Vid_roomNUM;
        this.videoRoomRental_start=VideoRoomRental_start;
        this.videoRoomRental_end=VideoRoomRental_end;
    }
    public VidRoomRentDTO(String VideoRoomRentalNUM, String ClientNUM, String Vid_roomNUM)
    {
        this.videoRoomRentalNUM=VideoRoomRentalNUM;
        this.clientNUM=ClientNUM;
        this.vid_roomNUM=Vid_roomNUM;
    }

    public String getVideoRoomRentalNUM() {
        return videoRoomRentalNUM;
    }

    public void setVideoRoomRentalNUM(String videoRoomRentalNUM) {
        this.videoRoomRentalNUM = videoRoomRentalNUM;
    }

    public String getClientNUM() {
        return clientNUM;
    }

    public void setClientNUM(String clientNUM) {
        this.clientNUM = clientNUM;
    }

    public String getVid_roomNUM() {
        return vid_roomNUM;
    }

    public void setVid_roomNUM(String vid_roomNUM) {
        this.vid_roomNUM = vid_roomNUM;
    }

    public Date getVideoRoomRental_start() {
        return videoRoomRental_start;
    }

    public void setVideoRoomRental_start(Date videoRoomRental_start) {
        this.videoRoomRental_start = videoRoomRental_start;
    }

    public Date getVideoRoomRental_end() {
        return videoRoomRental_end;
    }

    public void setVideoRoomRental_end(Date videoRoomRental_end) {
        this.videoRoomRental_end = videoRoomRental_end;
    }
    @Override
    public String toString(){
        return "VidRoomRentDTO=[videoRoomRentalNUM= "+videoRoomRentalNUM+
                ", clientNUM= "+clientNUM+", vid_roomNUM= "+vid_roomNUM+
                "]";
    }
}
