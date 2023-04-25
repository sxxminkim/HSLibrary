package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.BookDTO;
import com.manage.hslibrary.DTO.MemberDTO;
import com.manage.hslibrary.DTO.VideoDTO;
import org.springframework.jdbc.core.*;
import java.util.*;
import org.springframework.stereotype.*;
import javax.sql.*;

@Component
public class VideoDAO {
    private VideoDTO videoDTO;

    private JdbcTemplate jdbcTemplate;

    public VideoDAO(DataSource dataSource){this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public VideoDTO selectByVideoID(String inputVideoID){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM video WHERE videoID=?;",
                    (rs, rowNum) -> new VideoDTO(rs.getString("videoID"), rs.getString("videoName"), rs.getString("videoDirector"),
                            rs.getString("videoCompany"), rs.getString("videoRelease"), rs.getString("videoGenre"), rs.getString("videoSequel")),
                    inputVideoID);
        }catch(Exception Ex){
            return null;
        }
    }
    public List<VideoDTO> showAll() {
        // viewing all video
        return jdbcTemplate.query("SELECT * FROM video;", (rs, rowNum) -> {
            return new VideoDTO(rs.getString("videoID"), rs.getString("videoName"), rs.getString("videoDirector"),
                    rs.getString("videoCompany"), rs.getString("videoRelease"),rs.getString("videoGenre"), rs.getString("videoSequel"));
        });
    }
    public void insertVideo(VideoDTO _videoDTO) {
        // adding video
        this.videoDTO = _videoDTO;

        jdbcTemplate.update("INSERT INTO video(videoID, videoName, videoDirector, videoCompany, videoRelease, videoGenre, videoSequel, videoRegister) VALUES('"
                + videoDTO.getVideoID() + "', '" + videoDTO.getVideoName() + "', '" + videoDTO.getVideoDirector()
                + "', '" + videoDTO.getVideoCompany() + "', '" + videoDTO.getVideoRelease() + "', '"
                + videoDTO.getVideoGenre() + "', '"  + videoDTO.getVideoSequel() + "', " + "NOW());");
    }
    public void deleteVideo(VideoDTO _videoDTO) {
        this.videoDTO = _videoDTO;

        jdbcTemplate.update("DELETE FROM video WHERE videoID=" + videoDTO.getVideoID() + ";");
    }

    public void updateVideo(VideoDTO _videoDTO) {
        this.videoDTO = _videoDTO;

        jdbcTemplate.update("UPDATE video SET VideoName='" + videoDTO.getVideoName() + "', videoDirector='" + videoDTO.getVideoDirector()
                + "', videoCompany='" + videoDTO.getVideoCompany() + "', videoRelease='" + videoDTO.getVideoRelease() + "', " +
                "videoGenre='" + videoDTO.getVideoGenre() + "', videoSequel='" + videoDTO.getVideoSequel() + "', videoRegister='"
                + videoDTO.getVideoRegister() +"'WHERE videoID='" + videoDTO.getVideoID()
                + "';");
    }


}
