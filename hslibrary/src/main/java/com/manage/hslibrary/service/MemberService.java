package com.manage.hslibrary.service;

import com.manage.hslibrary.DAO.MemberDAO;
import com.manage.hslibrary.DTO.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberDAO memberDAO;
    @Autowired
    public MemberService(MemberDAO _memberDAO) {
        this.memberDAO = _memberDAO;
    }

    public MemberDTO addMember(MemberDTO _memberDTO) {
        // 회원가입
        MemberDTO memberDTO = memberDAO.selectByClientNUM(_memberDTO.getClientNUM());

        if (memberDTO == null) { // 회원 존재하지 않음 --> 회원가입 진행
            memberDAO.insertMember(_memberDTO);

            return _memberDTO; // 가입한 계정 반환
        } else {
            System.out.println("This member already exists.");

            return null; // null 반환
        }
    }


    public void deleteMember(MemberDTO _memberDTO) { // deleting books
        MemberDTO memberDTO = memberDAO.selectByClientNUM(_memberDTO.getClientNUM());

        if (memberDTO == null) {
            System.out.println("The member doesn't exist");
        } else {
            memberDAO.deleteMember(memberDTO);
        }
    }
    public MemberDTO updateMember(MemberDTO _memberDTO) { //updating book
        MemberDTO memberDTO = memberDAO.selectByClientNUM(_memberDTO.getClientNUM());

        if (memberDTO == null) { //The book doesn't exist
            System.out.println("No member to update");

            return null;
        } else { // The book exists
            memberDAO.updateMember(_memberDTO);

            return _memberDTO;
        }
    }

}
