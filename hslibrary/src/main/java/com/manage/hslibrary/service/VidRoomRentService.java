package com.manage.hslibrary.service;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VidRoomRentService {
    private VidRoomRentDAO vidRoomRentDAO;
    @Autowired
    public VidRoomRentService(VidRoomRentDAO _vidRoomRentDAO) {
        this.vidRoomRentDAO = _vidRoomRentDAO;
    }

    public VidRoomRentDTO rentVidRoom(VidRoomRentDTO _vidRoomRentDTO) { // adding books
        VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVidRoomRentalNUM(_vidRoomRentDTO.getVideoRoomRentalNUM());

        if (vidRoomRentDTO == null) { // if the book doesn't exist -> add to BookDTO
            vidRoomRentDAO.RentVidRoom(_vidRoomRentDTO);

            return _vidRoomRentDTO; // return the added book
        } else { //if the video is already taken
            System.out.println("The video room is already taken");

            return null;
        }
    }

    public void returnVidRoom(VidRoomRentDTO _vidRoomRentDTO) { // deleting books
        VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVidRoomRentalNUM(_vidRoomRentDTO.getVideoRoomRentalNUM());

        if (vidRoomRentDTO == null) {
            System.out.println("The video Room is not checked out yet.");
        } else {
            vidRoomRentDAO.ReturnVidRoom(vidRoomRentDTO);
        }
    }
}
