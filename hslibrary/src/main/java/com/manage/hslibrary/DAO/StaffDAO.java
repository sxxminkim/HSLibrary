package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.StaffDTO;
import org.springframework.jdbc.core.*;
import java.util.*;
import org.springframework.stereotype.*;
import javax.sql.*;

@Component
public class StaffDAO {

    private StaffDTO staffDTO;
    private JdbcTemplate jdbcTemplate;
    public StaffDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public StaffDTO selectByStaffNUM(String inputStaffNUM){

            //selecting admin by staffID
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM staff_Information WHERE staffNUM=?;",
                    (rs, rowNum) -> new StaffDTO(rs.getString("staffNUM"), rs.getString("staffPW"), rs.getString("staffName"),
                            rs.getString("staffID"),rs.getString("staffAddr"), rs.getString("staffPhone"), rs.getString("staffDepartment")),
                    inputStaffNUM);

        } catch(Exception Ex){
            return null;
        }
    }
    public List<StaffDTO> showAll() {
        // viewing all admin
        List<StaffDTO> result = jdbcTemplate.query("SELECT * FROM staff_Information;", (rs, rowNum) -> {
            StaffDTO staffDTO = new StaffDTO(rs.getString("staffNUM"), rs.getString("staffPW"), rs.getString("staffName"),
                    rs.getString("staffID"), rs.getString("staffAddr"),rs.getString("staffPhone"),rs.getString("staffDepartment")
            );
            return staffDTO;
        });
        return result;
    }


    public void insertStaff(StaffDTO _staffDTO) {
        // adding admin
        this.staffDTO = _staffDTO;

        jdbcTemplate.update("INSERT INTO staff_Information(staffNUM, staffPW, staffID, staffName, staffAddr, staffPhone, staffDepartment) VALUES('" + staffDTO.getStaffNUM() + "', '"
                + staffDTO.getStaffPW()+"', '"+ staffDTO.getStaffID() + "', '" + staffDTO.getStaffName() +"', '" + staffDTO.getStaffAddr() +"', '" + staffDTO.getStaffPhone() + "', '" + staffDTO.getStaffDepartment() +"');");
    }

    /*
    public void updatePassword(StaffDTO _staffDTO, String newPassword) {
        // 비밀번호 업데이트
        this.memberDTO = _staffDTO;

        jdbcTemplate.update(
                "UPDATE MEMBER SET PASSWORD='" + newPassword + "' WHERE EMAIL='" + memberDTO.getMemberEmail() + "';");
    }
     */
    public void deleteStaff(StaffDTO _staffDTO) {
        this.staffDTO = _staffDTO;

        jdbcTemplate.update("DELETE FROM staff_Information WHERE staffNUM='" + staffDTO.getStaffNUM() + "';");

    }
}


