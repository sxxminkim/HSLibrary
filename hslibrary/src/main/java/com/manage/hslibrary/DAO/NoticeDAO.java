package com.manage.hslibrary.DAO;
import com.manage.hslibrary.DTO.NoticeDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class NoticeDAO {
    private NoticeDTO noticeDTO;
    private JdbcTemplate jdbcTemplate;
    public NoticeDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    public NoticeDTO selectByNoticeNUM(int inputNoticeNUM){

        //selecting notice by noticeNUM
        try{
            return this.jdbcTemplate.queryForObject("SELECT * FROM Notice WHERE NoticeNUM=?;",
                    (rs, rowNum) -> new NoticeDTO(rs.getInt("NoticeNUM"), rs.getString("NoticeTitle"), rs.getString("NoticeAuthor"), rs.getString("NoticeMain"),
                            rs.getString("NoticeLink")),
                    inputNoticeNUM);

        } catch(Exception Ex){
            return null;
        }
    }
    public List<NoticeDTO> showAll() {
        // viewing all admin
        List<NoticeDTO> result = jdbcTemplate.query("SELECT * FROM Notice;", (rs, rowNum) -> {
            NoticeDTO noticeDTO = new NoticeDTO(rs.getInt("NoticeNUM"), rs.getString("NoticeTitle"), rs.getString("NoticeAuthor"), rs.getString("NoticeMain"),
                    rs.getString("NoticeLink"), rs.getDate("NoticeDate"));
            return noticeDTO;
        });
        return result;
    }


    public void insertNotice(NoticeDTO _noticeDTO) {
        // adding admin
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("INSERT INTO Notice(NoticeNUM, NoticeTitle, NoticeAuthor, NoticeMain, NoticeLink, NoticeDate) VALUES('" + noticeDTO.getNoticeNUM() + "', '"
                + noticeDTO.getNoticeTitle()+"', '"+ noticeDTO.getNoticeAuthor() + "', '" + noticeDTO.getNoticeMain() +"', '" + noticeDTO.getNoticeLink() +"', '" +"NOW());");
    }

    public void deleteNotice(NoticeDTO _noticeDTO) {
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("DELETE FROM Notice WHERE NoticeNUM='" + noticeDTO.getNoticeNUM() + "';");
    }
    public void updateNotice(NoticeDTO _noticeDTO) {
        this.noticeDTO = _noticeDTO;

        jdbcTemplate.update("UPDATE Notice SET NoticeTitle='" + noticeDTO.getNoticeTitle() + "', NoticeAuthor='" + noticeDTO.getNoticeAuthor()
                + "', noticeMain='" + noticeDTO.getNoticeMain() + "', NoticeLink='" + noticeDTO.getNoticeLink()+"'WHERE NoticeNUM='" + noticeDTO.getNoticeNUM()
                + "';");
    }
}


