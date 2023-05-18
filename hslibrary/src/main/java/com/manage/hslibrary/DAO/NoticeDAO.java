package com.manage.hslibrary.DAO;
import com.manage.hslibrary.DTO.NoticeDTO;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDAO {
    private NoticeDTO noticeDTO;
    private JdbcTemplate jdbcTemplate;

    public NoticeDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}


    public NoticeDTO selectByNoticeNUM(int inputNoticeNUM){

        //selecting notice by noticeNUM
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM Notice WHERE NoticeNum=?;",
                    (rs, rowNum) -> new NoticeDTO(rs.getInt("NoticeNum"), rs.getString("NoticeTitle"), rs.getString("NoticeAuthor"),
                            rs.getString("NoticeMain"), rs.getDate("NoticeDate")),
                    inputNoticeNUM);

        } catch(Exception Ex){
            return null;
        }
    }

    public NoticeDTO selectByNoticeTitle(String inputNoticeTitle){
        //selecting notice by noticeTitle
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM Notice WHERE NoticeTitle=?;",
                    (rs, rowNum) -> new NoticeDTO(rs.getInt("NoticeNum"), rs.getString("NoticeTitle"), rs.getString("NoticeAuthor"),
                            rs.getString("NoticeMain"), rs.getDate("NoticeDate")),
                    inputNoticeTitle);

        } catch(Exception Ex){
            return null;
        }

    }
    public List<NoticeDTO> showAll() {
        // viewing all notice
        List<NoticeDTO> result = jdbcTemplate.query("SELECT * FROM Notice;", (rs, rowNum) -> {
            NoticeDTO noticeDTO = new NoticeDTO(rs.getInt("NoticeNum"), rs.getString("NoticeTitle"), rs.getString("NoticeAuthor"), rs.getString("NoticeMain"),
                    rs.getDate("NoticeDate"));
            return noticeDTO;
        });
        return result;
    }

    public void insertNotice(NoticeDTO _noticeDTO) {
        // adding notice
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("INSERT INTO Notice(NoticeTitle, NoticeAuthor, NoticeMain, NoticeDate) VALUES('"
                + noticeDTO.getNoticeTitle()+"', '"+ noticeDTO.getNoticeAuthor() + "', '" + noticeDTO.getNoticeMain() +"', " +"NOW());");
    }

    public void deleteNotice(NoticeDTO _noticeDTO) {
        //deleting notice
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("DELETE FROM Notice WHERE NoticeNum='" + noticeDTO.getNoticeNUM() + "';");
    }
    public void updateNotice(NoticeDTO _noticeDTO) {
        //updating notice
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("UPDATE Notice SET NoticeTitle='" + noticeDTO.getNoticeTitle() + "', NoticeAuthor='" + noticeDTO.getNoticeAuthor()
                + "', noticeMain='" + noticeDTO.getNoticeMain() + "'WHERE NoticeNUM='" + noticeDTO.getNoticeNUM());
    }
}
