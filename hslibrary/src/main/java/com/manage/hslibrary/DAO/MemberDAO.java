package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MemberDAO {
    private MemberDTO memberDTO;
    private JdbcTemplate jdbcTemplate;
    public MemberDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public MemberDTO selectByClientNUM(String inputClientNUM){

        //selecting admin by staffID
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM client_Information WHERE clientNUM=?;",
                    (rs, rowNum) -> new MemberDTO(rs.getString("clientNUM"), rs.getString("clientName"), rs.getString("clientID"),
                            rs.getString("clientPhone"),rs.getString("clientAddr"), rs.getString("clientEmail")),
                    inputClientNUM);

        } catch(Exception Ex){
            return null;
        }
    }
    //viewing all members
    public List<MemberDTO> showAll() {
        // viewing all admin
        List<MemberDTO> result = jdbcTemplate.query("SELECT * FROM client_Information;", (rs, rowNum) -> {
            MemberDTO memberDTO = new MemberDTO(rs.getString("clientNUM"), rs.getString("clientName"), rs.getString("clientID"),
                    rs.getString("clientPhone"),rs.getString("clientAddr"), rs.getString("clientEmail"));
            return memberDTO;
        });
        return result;
    }
    //viewing book delays
    public List<MemberDTO> showBook(){
        List<MemberDTO> result = jdbcTemplate.query("SELECT * FROM client_Information INNER JOIN bookRental on client_Information.clientNUM = bookRental.clientNUM WHERE bookRental_end<NOW();", (rs, rowNum) -> {
            MemberDTO memberDTO = new MemberDTO(rs.getString("clientNUM"), rs.getString("clientName"), rs.getString("clientID"),
                    rs.getString("clientPhone"),rs.getString("clientAddr"), rs.getString("clientEmail"));
            return memberDTO;
        });
        return result;
    }
    //viewing video delays
    public List<MemberDTO> showVideo(){
        List<MemberDTO> result = jdbcTemplate.query("SELECT * FROM client_Information INNER JOIN videoRental on client_Information.clientNUM = videoRental.clientNUM WHERE videoRental_end<NOW();", (rs, rowNum) -> {
            MemberDTO memberDTO = new MemberDTO(rs.getString("clientNUM"), rs.getString("clientName"), rs.getString("clientID"),
                    rs.getString("clientPhone"),rs.getString("clientAddr"), rs.getString("clientEmail"));
            return memberDTO;
        });
        return result;
    }

    //adding new member
    public void insertMember(MemberDTO _memberDTO) {
        // adding admin
        this.memberDTO = _memberDTO;

        jdbcTemplate.update("INSERT INTO client_Information(clientNUM, clientName, clientID, clientPhone, clientAddr, clientEmail, clientRegister) VALUES('" + memberDTO.getClientNUM() + "', '"
                + memberDTO.getClientName()+"', '"+ memberDTO.getClientID() + "', '" + memberDTO.getClientPhone() +"', '" + memberDTO.getClientAddr() +"', '" + memberDTO.getClientEmail() + "', " + "NOW());");
    }

    //deleting member
    public void deleteMember(MemberDTO _memberDTO) {
        this.memberDTO = _memberDTO;

        jdbcTemplate.update("DELETE FROM client_Information WHERE clientNUM='" + memberDTO.getClientNUM() + "';");

    }
    //updating member information
    public void updateMember(MemberDTO _memberDTO) {
        this.memberDTO = _memberDTO;

        jdbcTemplate.update("UPDATE client_Information SET clientName='" + memberDTO.getClientName() + "', clientID='" + memberDTO.getClientID()
                + "', clientPhone='" + memberDTO.getClientPhone() + "', clientAddr='" + memberDTO.getClientAddr() + "', clientEmail='"
                + memberDTO.getClientEmail() + "'WHERE clientNUM='" + memberDTO.getClientNUM() + "';");
    }
}
