package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class VidRoomRentDAO {
    private VidRoomRentDTO vidRoomRentDTO;

    private JdbcTemplate jdbcTemplate;
    public VidRoomRentDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public VidRoomRentDTO selectByVidRoomRentalNUM(String inputVidRoomRentalNUM){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM videoRoomRental WHERE vid_roomNUM=?;",
                    (rs, rowNum) -> new VidRoomRentDTO(rs.getString("videoRoomRentalNUM"), rs.getString("clientNUM"), rs.getString("vid_roomNUM")
                    ),
                    inputVidRoomRentalNUM);
        }catch(Exception EX){
            return null;
        }
    }
    public VidRoomRentDTO selectByVid_roomNUM(String inputVid_roomNUM){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM videoRoomRental WHERE clientNUM=?;",
                    (rs, rowNum) -> new VidRoomRentDTO(rs.getString("videoRoomRentalNUM"), rs.getString("clientNUM"), rs.getString("vid_roomNUM")
                    ),
                    inputVid_roomNUM);
        }catch(Exception EX){
            return null;
        }
    }
    public List<VidRoomRentDTO> showAll() {
        List<VidRoomRentDTO> result = jdbcTemplate.query("SELECT * FROM videoRental;", (rs, rowNum) -> {
            VidRoomRentDTO vidRoomRentDTO = new VidRoomRentDTO(rs.getString("videoRentalNUM"), rs.getString("videoID"), rs.getString("clientNUM"),
                    rs.getDate("videoRental_start"), rs.getDate("videoRental_end")
            );
            return vidRoomRentDTO;
        });
        return result;
    }

    public void RentVidRoom(VidRoomRentDTO _vidRoomRentDTO) {
        this.vidRoomRentDTO = _vidRoomRentDTO;

        jdbcTemplate.update(
                "INSERT INTO videoRoomRental(videoRoomRentalNUM, clientNUM, vid_roomNUM, videoRoomRental_start, videoRoomRental_end" +
                        ") VALUES('"+ vidRoomRentDTO.getVideoRoomRentalNUM() + "', '" + vidRoomRentDTO.getClientNUM() + "', '" + vidRoomRentDTO.getVid_roomNUM()
                        + "', NOW(), NOW());");
    }

    public void ReturnVidRoom(VidRoomRentDTO _vidRoomRentDTO) {
        this.vidRoomRentDTO = _vidRoomRentDTO;

        jdbcTemplate.update("DELETE FROM videoRoomRental WHERE videoRoomRentalNUM='" + vidRoomRentDTO.getVideoRoomRentalNUM() + "';");
    }

}
