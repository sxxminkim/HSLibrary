package com.manage.hslibrary.service;

import com.manage.hslibrary.DAO.StaffDAO;
import com.manage.hslibrary.DTO.StaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private StaffDAO staffDAO;
    @Autowired
    public StaffService(StaffDAO _staffDAO) {
        this.staffDAO = _staffDAO;
    }

    public StaffDTO addStaff(StaffDTO _staffDTO) {
        // 회원가입
        StaffDTO staffDTO = staffDAO.selectByStaffNUM(_staffDTO.getStaffNUM());

        if (staffDTO == null) { // 회원 존재하지 않음 --> 회원가입 진행
            staffDAO.insertStaff(_staffDTO);

            return _staffDTO; // 가입한 계정 반환
        } else {
            System.out.println("This staff already exists.");

            return null; // null 반환
        }
    }


    public StaffDTO loginMember(String inputStaffNUM, String inputPW) {
        // admin login
        StaffDTO staffDTO = staffDAO.selectByStaffNUM(inputStaffNUM);

        if (staffDTO == null) {
            System.out.println("error in inserting StaffNUM");
            return null;
        } else if (!staffDTO.getStaffPW().equals(inputPW)) {
            // 비밀번호 오류
            System.out.println("error in inserting PW");
            return null;
        }

        return staffDTO;
    }
    //deleting admin
    public void deleteStaff(StaffDTO _staffDTO) { // deleting books
        StaffDTO staffDTO = staffDAO.selectByStaffNUM(_staffDTO.getStaffNUM());

        if (staffDTO == null) {
            System.out.println("The staff doesn't exist");
        } else {
            staffDAO.deleteStaff(staffDTO);
        }
    }
    //updating admin
    public StaffDTO updateStaff(StaffDTO _staffDTO) { //updating book
        StaffDTO staffDTO = staffDAO.selectByStaffNUM(_staffDTO.getStaffNUM());

        if (staffDTO == null) { //The staff doesn't exist
            System.out.println("No staff to update");

            return null;
        } else { // The staff exists
            staffDAO.updateStaff(_staffDTO);

            return _staffDTO;
        }
    }

}
