package com.manage.hslibrary.DAO;

import org.springframework.stereotype.Component;

import javax.sql.*;
import java.util.*;

import org.springframework.jdbc.core.*;

import com.manage.hslibrary.DTO.*;
@Component
public class RentDAO {
    private RentDAO rentDAO;
    private JdbcTemplate jdbcTemplate;

    public RentDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RentDTO> selectOverDue() {
        // 연체 도서 받아오기
        List<RentDTO> result = jdbcTemplate.query("SELECT * FROM Rental WHERE NOW() > rental_end;",
                (rs, rowNum) -> {
                    RentDTO rentDTO = new RentDTO(rs.getString("rentalNUM"), rs.getString("bookID"),
                            rs.getString("videoID"), rs.getString("clientNUM"),
                            rs.getString("vid_roomNUM"),rs.getDate("rental_start"),
                            rs.getDate("rental_end"));
                    return rentDTO;
                });
        return result;
    }

    public List<RentDTO> showAll() {
        // 모든 대여 도서 받아오기
        List<RentDTO> result = jdbcTemplate.query("SELECT * FROM Rental;", (rs, rowNum) -> {
            RentDTO rentDTO = new RentDTO(rs.getString("rentalNUM"), rs.getString("bookID"),
                    rs.getString("videoID"), rs.getString("clientNUM"),
                    rs.getString("vid_roomNUM"),rs.getDate("rental_start"),
                    rs.getDate("rental_end"));
            return rentDTO;
        });
        return result;
    }

    public List<RentDTO> selectByBookID(String bookID) {
        // 대여한 도서
        List<RentDTO> result = jdbcTemplate.query("SELECT * FROM Rental WHERE bookID='" + bookID + "';",
                (rs, rowNum) -> {
                    RentDTO rentDTO = new RentDTO(rs.getString("rentalNUM"), rs.getString("bookID"),
                            rs.getString("videoID"), rs.getString("clientNUM"),
                            rs.getString("vid_roomNUM"),rs.getDate("rental_start"),
                            rs.getDate("rental_end"));
                    return rentDTO;
                });
        return result;
    }

    public List<RentDTO> selectByClientNUM(String clientNUM) {
        // 대여한 도서
        List<RentDTO> result = jdbcTemplate.query("SELECT * FROM Rental WHERE clientNUM='" + clientNUM + "';",
                (rs, rowNum) -> {
                    RentDTO rentDTO = new RentDTO(rs.getString("rentalNUM"), rs.getString("bookID"),
                            rs.getString("videoID"), rs.getString("clientNUM"),
                            rs.getString("vid_roomNUM"),rs.getDate("rental_start"),
                            rs.getDate("rental_end"));
                    return rentDTO;
                });
        return result;
    }

}
