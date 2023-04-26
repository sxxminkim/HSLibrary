package com.manage.hslibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manage.hslibrary.DTO.BookDTO;
import com.manage.hslibrary.DAO.BookDAO;

@Service
public class BookService {
    private BookDAO bookDAO;

    @Autowired
    public BookService(BookDAO _bookDAO) {
        this.bookDAO = _bookDAO;
    }

    public BookDTO addBook(BookDTO _bookDTO) { // adding books
        BookDTO bookDTO = bookDAO.selectByBookID(_bookDTO.getBookID());

        if (bookDTO == null) { // if the book doesn't exist -> add to BookDTO
            bookDAO.insertBook(_bookDTO);

            return _bookDTO; // return the added book
        } else { //if the book already exists
            System.out.println("The book already exists.");

            return null;
        }
    }

    public void deleteBook(BookDTO _bookDTO) { // deleting books
        BookDTO bookDTO = bookDAO.selectByBookID(_bookDTO.getBookID());

        if (bookDTO == null) {
            System.out.println("The book doesn't exist");
        } else {
            bookDAO.deleteBook(bookDTO);
        }
    }

    public BookDTO updateBook(BookDTO _bookDTO) { //updating book
        BookDTO bookDTO = bookDAO.selectByBookID(_bookDTO.getBookID());

        if (bookDTO == null) { //The book doesn't exist
            System.out.println("No book to update");

            return null;
        } else { // The book exists
            bookDAO.updateBook(_bookDTO);

            return _bookDTO;
        }
    }

}
