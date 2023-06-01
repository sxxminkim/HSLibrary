package com.manage.hslibrary.DAO;
import com.manage.hslibrary.DTO.*;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import javax.sql.*;
import java.util.*;
@Component
public class BookDAO {
    private BookDTO bookDTO;
    private JdbcTemplate jdbcTemplate;
    public BookDAO(DataSource dataSource) {this.jdbcTemplate=new JdbcTemplate(dataSource);}
    public BookDTO selectByBookID(String inputBookID){
        try{return this.jdbcTemplate.queryForObject("SELECT * FROM book WHERE bookID=?;",
                (rs, rowNum) -> new BookDTO(rs.getString("bookID"), rs.getString("bookName"), rs.getString("bookWriter"),
                        rs.getString("bookGenre"), rs.getString("bookCompany"),rs.getString("bookISBN"),rs.getString("bookYear"),
                        rs.getString("bookEdition"),rs.getString("bookVolume"),rs.getString("bookIssue"), rs.getString("bookSummary"),
                        rs.getInt("bookType")),
                inputBookID);

        }catch(Exception EX){
            return null;
        }
    }
    //viewing all books
    public List<BookDTO> showAll() {
        List<BookDTO> result = jdbcTemplate.query("SELECT * FROM book;", (rs, rowNum) -> {
            BookDTO bookDTO = new BookDTO(rs.getString("bookID"), rs.getString("bookName"), rs.getString("bookWriter"),
                    rs.getString("bookGenre"), rs.getString("bookCompany"),rs.getString("bookISBN"),rs.getString("bookYear"),
                    rs.getString("bookEdition"),rs.getString("bookVolume"),rs.getString("bookIssue"), rs.getString("bookSummary"),
                    rs.getInt("bookType"),rs.getDate("bookRegister")
                    );
            return bookDTO;
        });
        return result;
    }
    //viewing paper books only
    public List<BookDTO> showPaper() {
        List<BookDTO> result = jdbcTemplate.query("SELECT * FROM book WHERE bookType=1;", (rs, rowNum) -> {
            BookDTO bookDTO = new BookDTO(rs.getString("bookID"), rs.getString("bookName"), rs.getString("bookWriter"),
                    rs.getString("bookGenre"), rs.getString("bookCompany"),rs.getString("bookISBN"),rs.getString("bookYear"),
                    rs.getString("bookEdition"),rs.getString("bookVolume"),rs.getString("bookIssue"), rs.getString("bookSummary"),
                    rs.getInt("bookType"),rs.getDate("bookRegister")
            );
            return bookDTO;
        });
        return result;
    }
    //viewing e-books
    public List<BookDTO> showEbook() {
        List<BookDTO> result = jdbcTemplate.query("SELECT * FROM book WHERE bookType=2;", (rs, rowNum) -> {
            BookDTO bookDTO = new BookDTO(rs.getString("bookID"), rs.getString("bookName"), rs.getString("bookWriter"),
                    rs.getString("bookGenre"), rs.getString("bookCompany"),rs.getString("bookISBN"),rs.getString("bookYear"),
                    rs.getString("bookEdition"),rs.getString("bookVolume"),rs.getString("bookIssue"), rs.getString("bookSummary"),
                    rs.getInt("bookType"),rs.getDate("bookRegister")
            );
            return bookDTO;
        });
        return result;
    }
    //adding new book
    public void insertBook(BookDTO _bookDTO) {
        this.bookDTO = _bookDTO;

        jdbcTemplate.update(
                "INSERT INTO book(bookID, bookName, bookWriter, bookGenre, bookCompany, bookISBN, bookYear, bookEdition, bookVolume, bookIssue, bookSummary, bookType, bookRegister" +
                        ") VALUES('"+ bookDTO.getBookID() + "', '" + bookDTO.getBookName() + "', '" + bookDTO.getBookWriter()
                        + "', '" + bookDTO.getBookGenre() + "', '" + bookDTO.getBookCompany() + "', '"
                        + bookDTO.getBookISBN() + "', '" + bookDTO.getBookYear() + "', '" + bookDTO.getBookEdition()
                        + "', '" + bookDTO.getBookVolume() + "', '"  + bookDTO.getBookIssue() +"', '" + bookDTO.getBookSummary() +"', '"+
                        bookDTO.getBookType()+"', "+"NOW());");
    }
    //deleting book
    public void deleteBook(BookDTO _bookDTO) {
        this.bookDTO = _bookDTO;

        jdbcTemplate.update("DELETE FROM book WHERE bookID='" + bookDTO.getBookID() + "';");
    }
    //updating book information
    public void updateBook(BookDTO _bookDTO) {
        this.bookDTO = _bookDTO;

        jdbcTemplate.update("UPDATE book SET bookName='" + bookDTO.getBookName() + "', bookWriter='" + bookDTO.getBookWriter()
                + "', bookGenre='" + bookDTO.getBookGenre() + "', bookCompany='" + bookDTO.getBookCompany() + "', bookISBN='"
                + bookDTO.getBookISBN() + "', bookYear='" + bookDTO.getBookYear() + "', bookEdition='"
                + bookDTO.getBookEdition() + "', bookVolume='" + bookDTO.getBookVolume() +
                "', bookVolume='" + bookDTO.getBookVolume() + "', bookSummary='" + bookDTO.getBookSummary()+
                "', bookType='" + bookDTO.getBookType()+"'WHERE bookID='" + bookDTO.getBookID()
                + "';");
    }
}
