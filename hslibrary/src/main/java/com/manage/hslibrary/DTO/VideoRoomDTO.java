package com.manage.hslibrary.DTO;

import org.springframework.stereotype.Repository;

@Repository
public class VideoRoomDTO {

    private String vid_roomNUM;
    private String vid_roomName;

    public VideoRoomDTO(String Vid_RoomNUM)
    {
        //getting video room data from
        this.vid_roomNUM=Vid_RoomNUM;
    }
    public VideoRoomDTO(String Vid_RoomNUM, String Vid_RoomName)
    {
        //setting video room data
        this.vid_roomNUM=Vid_RoomNUM;
        this.vid_roomName=Vid_RoomName;
    }
    public VideoRoomDTO(){}

    public String getVid_roomNUM() {return vid_roomNUM;}
    public void setVid_roomNUM(String vid_roomNUM) {this.vid_roomNUM = vid_roomNUM;}
    public String getVid_roomName() {return vid_roomName;}
    public void setVid_roomName(String vid_roomName) {this.vid_roomName = vid_roomName;}

    @Override
    public String toString(){
        return "VideoRoomDTO [vid_roomNUM= "+vid_roomNUM+", vid_roomName= "+vid_roomName+"]";
    }


}
