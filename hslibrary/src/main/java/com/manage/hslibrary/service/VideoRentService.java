package com.manage.hslibrary.service;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class VideoRentService {
    private VideoRentDAO videoRentDAO;
    @Autowired
    public VideoRentService(VideoRentDAO _videoRentDAO) {
        this.videoRentDAO = _videoRentDAO;
    }

    public VideoRentDTO rentVideo(VideoRentDTO _videoRentDTO) { // adding books
        VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(_videoRentDTO.getVideoRentalNUM());

        if (videoRentDTO == null) { // if the book doesn't exist -> add to BookDTO
            videoRentDAO.RentVideo(_videoRentDTO);

            return _videoRentDTO; // return the added book
        } else { //if the video is already taken
            System.out.println("The video is already taken");

            return null;
        }
    }

    public void returnVideo(VideoRentDTO _videoRentDTO) { // deleting books
        VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(_videoRentDTO.getVideoRentalNUM());

        if (videoRentDTO == null) {
            System.out.println("The video is not checked out yet.");
        } else {
            videoRentDAO.ReturnVideo(videoRentDTO);
        }
    }

    public VideoRentDTO extendVideo(VideoRentDTO _videoRentDTO) { //updating book
        VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(_videoRentDTO.getVideoRentalNUM());

        if (videoRentDTO == null) { //The book doesn't exist
            System.out.println("No video to extend");

            return null;
        } else { // The book exists
            videoRentDAO.ExtendVideo(_videoRentDTO);

            return _videoRentDTO;
        }
    }
}
