package com.manage.hslibrary.DTO;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.*;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDTO {
    private int noticeNUM;
    private String noticeTitle;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date noticeDate;
    private String noticeMain;
    private String noticeAuthor;

    public NoticeDTO(String NoticeTitle, String NoticeAuthor, String NoticeMain)
    {
        //adding notice info
        //this.noticeNUM=0;
        this.noticeTitle=NoticeTitle;
        this.noticeAuthor=NoticeAuthor;
        this.noticeMain=NoticeMain;
    }
    public NoticeDTO(int NoticeNUM, String NoticeTitle, String NoticeAuthor, String NoticeMain,
                     Date NoticeDate)
    {
        //getting notice information
        this.noticeNUM=NoticeNUM;
        this.noticeTitle=NoticeTitle;
        this.noticeMain=NoticeMain;
        this.noticeAuthor=NoticeAuthor;
        this.noticeDate=NoticeDate;
    }
    public NoticeDTO(){}

    public int getNoticeNUM() {
        return noticeNUM;
    }

    public void setNoticeNUM(int noticeNUM) {
        this.noticeNUM = noticeNUM;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeMain() {
        return noticeMain;
    }

    public void setNoticeMain(String noticeMain) {
        this.noticeMain = noticeMain;
    }

    public String getNoticeAuthor() {
        return noticeAuthor;
    }

    public void setNoticeAuthor(String noticeAuthor) {
        this.noticeAuthor = noticeAuthor;
    }

    @Override
    public String toString(){
        return "NoticeDTO [noticeTitle= "+noticeTitle+", noticeAuthor= "+noticeAuthor+", noticeMain= "
                +noticeMain+"]";
    }
}
