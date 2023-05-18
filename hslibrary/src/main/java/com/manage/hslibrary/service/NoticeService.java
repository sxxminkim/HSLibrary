package com.manage.hslibrary.service;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {
    private NoticeDAO noticeDAO;
    @Autowired
    public NoticeService(NoticeDAO _noticeDAO) {this.noticeDAO = _noticeDAO;}

    public NoticeDTO addNotice(NoticeDTO _noticeDTO) { // adding books
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeTitle(_noticeDTO.getNoticeTitle());

        if (noticeDTO == null) { // if the notice doesn't exist -> add to NoticeDTO
            noticeDAO.insertNotice(_noticeDTO);

            return _noticeDTO; // return the added notice
        } else { //if the notice already exists
            System.out.println("The notice already exists.");

            return null;
        }
    }

    public void deleteNotice(NoticeDTO _noticeDTO) { // deleting books
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(_noticeDTO.getNoticeNUM());

        if (noticeDTO == null) {
            System.out.println("There is not such Notice.");
        } else {
            noticeDAO.deleteNotice(noticeDTO);
        }
    }

    public NoticeDTO updateNotice(NoticeDTO _noticeDTO) { //updating book
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(_noticeDTO.getNoticeNUM());

        if (noticeDTO == null) { //The book doesn't exist
            System.out.println("No Notice to update");

            return null;
        } else { // The book exists
            noticeDAO.updateNotice(_noticeDTO);

            return _noticeDTO;
        }
    }

}
