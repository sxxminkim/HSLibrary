package com.manage.hslibrary.service;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoRoomService {
    private VideoRoomDAO videoRoomDAO;
    @Autowired
    public VideoRoomService (VideoRoomDAO _videoRoomDAO) {
        this.videoRoomDAO = _videoRoomDAO;
    }

    public VideoRoomDTO addVideoRoom(VideoRoomDTO _videoRoomDTO) { // adding books
        VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(_videoRoomDTO.getVid_roomNUM());

        if (videoRoomDTO == null) { // if the book doesn't exist -> add to BookDTO
            videoRoomDAO.insertVideoRoom(_videoRoomDTO);

            return _videoRoomDTO; // return the added book
        } else { //if the book already exists
            System.out.println("The video rooom already exists.");

            return null;
        }
    }

    public void deleteVideoRoom(VideoRoomDTO _videoRoomDTO) { // deleting books
        VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(_videoRoomDTO.getVid_roomNUM());

        if (videoRoomDTO == null) {
            System.out.println("The video room doesn't exist");
        } else {
            videoRoomDAO.deleteVideoRoom(videoRoomDTO);
        }
    }

    public VideoRoomDTO updateVideoRoom(VideoRoomDTO _videoRoomDTO) { //updating book
        VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(_videoRoomDTO.getVid_roomNUM());

        if (videoRoomDTO == null) { //The book doesn't exist
            System.out.println("No video room to update");

            return null;
        } else { // The book exists
            videoRoomDAO.updateVideoRoom(_videoRoomDTO);

            return _videoRoomDTO;
        }
    }



}
