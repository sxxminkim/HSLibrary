package com.manage.hslibrary.service;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRentService {
    private BookRentDAO bookRentDAO;
    @Autowired
    public BookRentService(BookRentDAO _bookRentDAO) {
        this.bookRentDAO = _bookRentDAO;
    }

    public BookRentDTO rentBook(BookRentDTO _bookRentDTO) { // adding books
        BookRentDTO bookRentDTO = bookRentDAO.selectByBookRentalNUM(_bookRentDTO.getBookRentalNUM());

        if (bookRentDTO == null) { // if the book doesn't exist -> add to BookDTO
            bookRentDAO.RentBook(_bookRentDTO);

            return _bookRentDTO; // return the added book
        } else { //if the book already exists
            System.out.println("The book is already taken");

            return null;
        }
    }

    public void returnBook(BookRentDTO _bookRentDTO) { // deleting books
        BookRentDTO bookRentDTO = bookRentDAO.selectByBookRentalNUM(_bookRentDTO.getBookRentalNUM());

        if (bookRentDTO == null) {
            System.out.println("The book is not checked out yet.");
        } else {
            bookRentDAO.ReturnBook(bookRentDTO);
        }
    }

    public BookRentDTO extendBook(BookRentDTO _bookRentDTO) { //updating book
        BookRentDTO bookRentDTO = bookRentDAO.selectByBookRentalNUM(_bookRentDTO.getBookRentalNUM());

        if (bookRentDTO == null) { //The book doesn't exist
            System.out.println("No book to extend");

            return null;
        } else { // The book exists
            bookRentDAO.ExtendBook(_bookRentDTO);

            return _bookRentDTO;
        }
    }
}
