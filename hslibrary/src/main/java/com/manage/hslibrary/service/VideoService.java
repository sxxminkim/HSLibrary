package com.manage.hslibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.hslibrary.DTO.VideoDTO;
import com.manage.hslibrary.DAO.VideoDAO;
@Service
public class VideoService {
    private VideoDAO videoDAO;
    @Autowired
    public VideoService(VideoDAO _videoDAO) {
        this.videoDAO = _videoDAO;
    }
    public VideoDTO addVideo(VideoDTO _videoDTO) { // adding books
        VideoDTO videoDTO = videoDAO.selectByVideoID(_videoDTO.getVideoID());

        if (videoDTO == null) { // if the book doesn't exist -> add to BookDTO
            videoDAO.insertVideo(_videoDTO);

            return _videoDTO; // return the added book
        } else { //if the book already exists
            System.out.println("The video already exists.");

            return null;
        }
    }

    public void deleteVideo(VideoDTO _videoDTO) { // deleting books
        VideoDTO videoDTO = videoDAO.selectByVideoID(_videoDTO.getVideoID());

        if (videoDTO == null) {
            System.out.println("The video doesn't exist");
        } else {
            videoDAO.deleteVideo(videoDTO);
        }
    }

    public VideoDTO updateVideo(VideoDTO _videoDTO) { //updating book
        VideoDTO videoDTO = videoDAO.selectByVideoID(_videoDTO.getVideoID());

        if (videoDTO == null) { //The book doesn't exist
            System.out.println("No video to update");

            return null;
        } else { // The book exists
            videoDAO.updateVideo(_videoDTO);

            return _videoDTO;
        }
    }
}
