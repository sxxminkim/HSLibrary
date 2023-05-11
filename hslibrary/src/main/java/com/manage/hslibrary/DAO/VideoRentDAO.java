package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.VideoRentDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class VideoRentDAO {
    private VideoRentDTO videoRentDTO;

    private JdbcTemplate jdbcTemplate;
    public VideoRentDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public VideoRentDTO selectByVideoRentalNUM(String inputVideoRentalNUM){
        try{return this.jdbcTemplate.queryForObject("SELECT * FROM videoRental WHERE videoRentalNUM=?;",
                (rs, rowNum) -> new VideoRentDTO(rs.getString("videoRentalNUM"), rs.getString("videoID"), rs.getString("clientNUM")
                ),
                inputVideoRentalNUM);

        }catch(Exception EX){
            return null;
        }
    }
    public VideoRentDTO selectByVideoID(String inputVideoID){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM videoRental WHERE videoID=?;",
                    (rs, rowNum) -> new VideoRentDTO(rs.getString("videoRentalNUM"), rs.getString("videoID"), rs.getString("clientNUM")
                    ),
                    inputVideoID);
        }catch(Exception EX){
            return null;
        }
    }
    public VideoRentDTO selectByClientNUM(String inputClientNUM){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM videoRental WHERE clientNUM=?;",
                    (rs, rowNum) -> new VideoRentDTO(rs.getString("videoRentalNUM"), rs.getString("videoID"), rs.getString("clientNUM")
                    ),
                    inputClientNUM);
        }catch(Exception EX){
            return null;
        }
    }
    public List<VideoRentDTO> showAll() {
        List<VideoRentDTO> result = jdbcTemplate.query("SELECT * FROM videoRental;", (rs, rowNum) -> {
            VideoRentDTO videoRentDTO = new VideoRentDTO(rs.getString("videoRentalNUM"), rs.getString("videoID"), rs.getString("clientNUM"),
                    rs.getDate("videoRental_start"), rs.getDate("videoRental_end")
            );
            return videoRentDTO;
        });
        return result;
    }

    public void RentVideo(VideoRentDTO _videoRentDTO) {
        this.videoRentDTO = _videoRentDTO;

        jdbcTemplate.update(
                "INSERT INTO videoRental(videoRentalNUM, videoID, clientNUM, videoRental_start, videoRental_end" +
                        ") VALUES('"+ videoRentDTO.getVideoRentalNUM() + "', '" + videoRentDTO.getVideoID() + "', '" + videoRentDTO.getClientNUM()
                        + "', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY));");
    }

    public void ReturnVideo(VideoRentDTO _videoRentDTO) {
        this.videoRentDTO = _videoRentDTO;

        jdbcTemplate.update("DELETE FROM videoRental WHERE videoRentalNUM='" + videoRentDTO.getVideoRentalNUM() + "';");
    }
    public void ExtendVideo(VideoRentDTO _videoRentDTO) {
        this.videoRentDTO=_videoRentDTO;
        // 도서 연장
        jdbcTemplate.update(
                "UPDATE videoRental SET videoRental_end=DATE_ADD(videoRental_end, INTERVAL 7 DAY) WHERE videoRentalNUM='"
                        + videoRentDTO.getVideoRentalNUM() + "' AND clientNUM='" + videoRentDTO.getClientNUM() + "';");
    }
}
