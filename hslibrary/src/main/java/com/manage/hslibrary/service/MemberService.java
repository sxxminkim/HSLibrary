package com.manage.hslibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manage.hslibrary.DTO.MemberDTO;
import com.manage.hslibrary.DAO.MemberDAO;

@Service
public class MemberService {
    private MemberDAO memberDAO;
    @Autowired
    public MemberService(MemberDAO _memberDAO) {
        this.memberDAO = _memberDAO;
    }

    public MemberDTO addMember(MemberDTO _memberDTO) {
        // 회원가입
        MemberDTO memberDTO = memberDAO.selectByStaffNUM(_memberDTO.getStaffNUM());

        if (memberDTO == null) { // 회원 존재하지 않음 --> 회원가입 진행
            memberDAO.insertMember(_memberDTO);

            return _memberDTO; // 가입한 계정 반환
        } else {
            System.out.println("This staff already exists.");

            return null; // null 반환
        }
    }


    public MemberDTO loginMember(String inputStaffNUM, String inputPW) {
        // admin login
        MemberDTO memberDTO = memberDAO.selectByStaffNUM(inputStaffNUM);

        if (memberDTO == null) {
            System.out.println("error in inserting StaffNUM");
            return null;
        } else if (!memberDTO.getStaffPW().equals(inputPW)) {
            // 비밀번호 오류
            System.out.println("error in inserting PW");
            return null;
        }

        return memberDTO;
    }
    /*
    public MemberDTO changePassword(MemberDTO _memberDTO, StringBuffer newPassword) {
        // 비밀번호 수정
        memberDAO.updatePassword(_memberDTO, newPassword.toString());

        return memberDAO.selectByID(_memberDTO.getStaffID());
    }
     */
}
