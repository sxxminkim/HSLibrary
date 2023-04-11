package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.MemberDTO;
import org.springframework.jdbc.core.*;
import java.util.*;
import org.springframework.stereotype.*;
import javax.sql.*;

@Component
public class MemberDAO {

    private MemberDTO memberDTO;
    private JdbcTemplate jdbcTemplate;
    public MemberDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public MemberDTO selectByID(String inputID){


        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM staff_Information WHERE staffID=?;",
                    (rs, rowNum) -> new MemberDTO(rs.getString("staffID"), rs.getString("staffPW"), rs.getString("staffName"),
                            rs.getString("staffNUM"),rs.getString("staffAddr"), rs.getString("staffPhone"), rs.getString("staffDeparture")),
                    inputID);

        } catch(Exception Ex){
            return null;
        }
    }
    public List<MemberDTO> showAll() {
        // viewing all admin
        List<MemberDTO> result = jdbcTemplate.query("SELECT * FROM staff_Information;", (rs, rowNum) -> {
            MemberDTO memberDTO = new MemberDTO(rs.getString("staffID"), rs.getString("staffPW"), rs.getString("staffName"),
                    rs.getString("staffNUM"),rs.getString("staffAddr"), rs.getString("staffPhone"), rs.getString("staffDeparture"));
            return memberDTO;
        });
        return result;
    }

    public void insertMember(MemberDTO _memberDTO) {
        // adding admin
        this.memberDTO = _memberDTO;

        jdbcTemplate.update("INSERT INTO staff_Information(staffID, staffPW, stafName) VALUES('" + memberDTO.getStaffID() + "', '"
                + memberDTO.getStaffPW() + "', '" + memberDTO.getStaffName() + "');");
    }
    /*
    public void updatePassword(MemberDTO _memberDTO, String newPassword) {
        // 비밀번호 업데이트
        this.memberDTO = _memberDTO;

        jdbcTemplate.update(
                "UPDATE MEMBER SET PASSWORD='" + newPassword + "' WHERE EMAIL='" + memberDTO.getMemberEmail() + "';");
    }
     */
    public void deleteMember(MemberDTO member) {

    }
}


