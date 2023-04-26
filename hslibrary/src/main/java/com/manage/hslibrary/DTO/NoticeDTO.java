package com.manage.hslibrary.DTO;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDTO {
    private int noticeNUM;
    private String noticeTitle;
    private String noticeDate;
    //private String noticeContent; -> 공지사항 내용도 추가해야함.

    public NoticeDTO(){}

}
