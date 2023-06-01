package com.manage.hslibrary.DAO;

import com.manage.hslibrary.DTO.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BookRentDAO {
    private BookRentDTO bookRentDTO;

    private JdbcTemplate jdbcTemplate;
    public BookRentDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}

    //selecting by rental-NUM
    public BookRentDTO selectByBookRentalNUM(String inputBookRentalNUM){
        try{return this.jdbcTemplate.queryForObject("SELECT * FROM bookRental WHERE bookRentalNUM=?;",
                (rs, rowNum) -> new BookRentDTO(rs.getString("bookRentalNUM"), rs.getString("bookID"), rs.getString("clientNUM")
                ),
                inputBookRentalNUM);

        }catch(Exception EX){
            return null;
        }
    }
    //selecting by book-id
    public BookRentDTO selectByBookID(String inputBookID){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM bookRental WHERE bookID=?;",
                    (rs, rowNum) -> new BookRentDTO(rs.getString("bookRentalNUM"), rs.getString("bookID"), rs.getString("clientNUM")
                    ),
                    inputBookID);
        }catch(Exception EX){
            return null;
        }
    }
    //selecting by client-num
    public BookRentDTO selectByClientNUM(String inputClientNUM){
        try {
            return this.jdbcTemplate.queryForObject("SELECT * FROM bookRental WHERE clientNUM=?;",
                    (rs, rowNum) -> new BookRentDTO(rs.getString("bookRentalNUM"), rs.getString("bookID"), rs.getString("clientNUM")
                    ),
                    inputClientNUM);
        }catch(Exception EX){
            return null;
        }
    }
    //viewing all book-rental history
    public List<BookRentDTO> showAll() {
        List<BookRentDTO> result = jdbcTemplate.query("SELECT * FROM bookRental;", (rs, rowNum) -> {
            BookRentDTO bookRentDTO = new BookRentDTO(rs.getString("bookRentalNUM"), rs.getString("bookID"), rs.getString("clientNUM"),
                    rs.getDate("bookRental_start"), rs.getDate("bookRental_end")
            );
            return bookRentDTO;
        });
        return result;
    }
    //rent book
    public void RentBook(BookRentDTO _bookRentDTO) {
        this.bookRentDTO = _bookRentDTO;

        jdbcTemplate.update(
                "INSERT INTO bookRental(bookRentalNUM, bookID, clientNUM, bookRental_start, bookRental_end" +
                        ") VALUES('"+ bookRentDTO.getBookRentalNUM() + "', '" + bookRentDTO.getBookID() + "', '" + bookRentDTO.getClientNUM()
                        + "', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY));");
    }
    //returning book & deleting book rent history
    public void ReturnBook(BookRentDTO _bookRentDTO) {
        this.bookRentDTO = _bookRentDTO;

        jdbcTemplate.update("DELETE FROM bookRental WHERE bookRentalNUM='" + bookRentDTO.getBookRentalNUM() + "';");
    }
    //extending book for one week
    public void ExtendBook(BookRentDTO _bookRentDTO) {
        this.bookRentDTO=_bookRentDTO;
        // 도서 연장
        jdbcTemplate.update(
                "UPDATE bookRental SET bookRental_end=DATE_ADD(bookRental_end, INTERVAL 7 DAY) WHERE bookRentalNUM='"
                        + bookRentDTO.getBookRentalNUM() + "' AND clientNUM='" + bookRentDTO.getClientNUM() + "';");
    }


}
