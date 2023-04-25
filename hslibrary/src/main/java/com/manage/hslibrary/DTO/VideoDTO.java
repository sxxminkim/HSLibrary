package com.manage.hslibrary.DTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class VideoDTO {
    private String videoID;
    private String videoName;
    private String videoDirector;
    private String videoCompany;
    private String videoRelease;
    private String videoGenre;
    private String videoSequel;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date videoRegister;
    public VideoDTO(){}
    public VideoDTO(String VideoID, String VideoName, String VideoDirector,
                    String VideoCompany, String VideoRelease, String VideoGenre,
                    String VideoSequel)
    {
        //getting video data
        this.videoID=VideoID;
        this.videoName=VideoName;
        this.videoDirector=VideoDirector;
        this.videoCompany=VideoCompany;
        this.videoRelease=VideoRelease;
        this.videoGenre=VideoGenre;
        this.videoSequel=VideoSequel;
    }
    public VideoDTO(String VideoID, String VideoName, String VideoDirector,
                    String VideoCompany, String VideoRelease, String VideoGenre,
                    String VideoSequel, Date VideoRegister)
    {
        //setting new video data
        this.videoID=VideoID;
        this.videoName=VideoName;
        this.videoDirector=VideoDirector;
        this.videoCompany=VideoCompany;
        this.videoRelease=VideoRelease;
        this.videoGenre=VideoGenre;
        this.videoSequel=VideoSequel;
        this.videoRegister=VideoRegister;
    }

    public String getVideoID() {return videoID;}
    public void setVideoID(String videoID) {this.videoID = videoID;}
    public String getVideoName() {return videoName;}
    public void setVideoName(String videoName) {this.videoName = videoName;}
    public String getVideoDirector() {return videoDirector;}
    public void setVideoDirector(String videoDirector) {this.videoDirector = videoDirector;}
    public String getVideoCompany() {return videoCompany;}
    public void setVideoCompany(String videoCompany) {this.videoCompany = videoCompany;}
    public String getVideoRelease() {
        return videoRelease;
    }
    public void setVideoRelease(String videoRelease) {
        this.videoRelease = videoRelease;
    }
    public String getVideoGenre() {
        return videoGenre;
    }
    public void setVideoGenre(String videoGenre) {
        this.videoGenre = videoGenre;
    }
    public String getVideoSequel() {
        return videoSequel;
    }
    public void setVideoSequel(String videoSequel) {
        this.videoSequel = videoSequel;
    }
    public Date getVideoRegister() {
        return videoRegister;
    }
    public void setVideoRegister(Date videoRegister) {
        this.videoRegister = videoRegister;
    }

    @Override
    public String toString(){
        return "VideoDTO [videoID= "+videoID+", videoName= "+videoName+", videoDirector= "
                +videoDirector+", videoCompany= "+videoCompany+", videoGenre= "+videoGenre+", videoSequel= "+videoSequel+"]";
    }
}
