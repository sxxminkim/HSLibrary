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
    private String noticeLink;

    public NoticeDTO(int NoticeNUM, String NoticeTitle, String NoticeMain, String NoticeAuthor,
                     String NoticeLink)
    {
        //adding notice info
        this.noticeNUM=NoticeNUM;
        this.noticeTitle=NoticeTitle;
        this.noticeMain=NoticeMain;
        this.noticeAuthor=NoticeAuthor;
        this.noticeLink=NoticeLink;
    }
    public NoticeDTO(int NoticeNUM, String NoticeTitle, String NoticeMain, String NoticeAuthor,
                     String NoticeLink, Date NoticeDate)
    {
        this.noticeNUM=NoticeNUM;
        this.noticeTitle=NoticeTitle;
        this.noticeMain=NoticeMain;
        this.noticeAuthor=NoticeAuthor;
        this.noticeLink=NoticeLink;
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

    public String getNoticeLink() {
        return noticeLink;
    }

    public void setNoticeLink(String noticeLink) {
        this.noticeLink = noticeLink;
    }
    @Override
    public String toString(){
        return "NoticeDTO [noticeNUM= "+noticeNUM+", noticeTitle= "+noticeTitle+", noticeAuthor= "+noticeAuthor+
        ", noticeMain= "+noticeMain+", noticeLink= "+noticeLink+ "]";
    }
}
