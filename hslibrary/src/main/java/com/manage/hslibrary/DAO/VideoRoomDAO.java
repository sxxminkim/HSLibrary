package com.manage.hslibrary.DAO;
import com.manage.hslibrary.DTO.BookDTO;
import com.manage.hslibrary.DTO.StaffDTO;
import org.springframework.jdbc.core.*;
import com.manage.hslibrary.DTO.VideoRoomDTO;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class VideoRoomDAO {
    private VideoRoomDTO videoRoomDTO;
    private JdbcTemplate jdbcTemplate;

    public VideoRoomDAO(DataSource dataSource) {this.jdbcTemplate = new JdbcTemplate(dataSource);}
    public VideoRoomDTO selectByVid_roomNUM(String inputVid_roomNUM){
        try{return this.jdbcTemplate.queryForObject("SELECT * FROM videoRoom WHERE vid_roomNUM=?;",
                (rs, rowNum) -> new VideoRoomDTO(rs.getString("vid_roomNUM"), rs.getString("vid_roomName")
                ),
                inputVid_roomNUM);

        }catch(Exception EX){
            return null;
        }
    }
    public List<VideoRoomDTO> showAll() {
        List<VideoRoomDTO> result = jdbcTemplate.query("SELECT * FROM videoRoom;", (rs, rowNum) -> {
            VideoRoomDTO videoRoomDTO = new VideoRoomDTO(rs.getString("vid_roomNUM"), rs.getString("vid_roomName")
            );
            return videoRoomDTO;
        });
        return result;
    }


    public void insertVideoRoom(VideoRoomDTO _videoRoomDTO) {
        // adding admin
        this.videoRoomDTO = _videoRoomDTO;

        jdbcTemplate.update("INSERT INTO videoRoom(vid_roomNUM, vid_roomName) VALUES('" + videoRoomDTO.getVid_roomNUM() + "', '"
                + videoRoomDTO.getVid_roomName()+"', '"+"');");
    }

    public void deleteVideoRoom(VideoRoomDTO _videoRoomDTO) {
        this.videoRoomDTO = _videoRoomDTO;

        jdbcTemplate.update("DELETE FROM videoRoom WHERE vid_roomNUM='" + videoRoomDTO.getVid_roomNUM() + "';");
    }

    public void updateVideoRoom(VideoRoomDTO _videoRoomDTO) {
        this.videoRoomDTO = _videoRoomDTO;

        jdbcTemplate.update("UPDATE videoRoom SET vid_roomName='" + videoRoomDTO.getVid_roomName() + "'WHERE vid_roomNum='" + videoRoomDTO.getVid_roomNUM()
                + "';");
    }





}
