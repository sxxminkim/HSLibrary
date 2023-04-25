package com.manage.hslibrary.controller;

import com.manage.hslibrary.DTO.BookDTO;
import com.manage.hslibrary.DAO.BookDAO;
import com.manage.hslibrary.exception.AlreadyExistingException;
import com.manage.hslibrary.exception.FillOutInformationException;
import com.manage.hslibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
@Controller

public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BookDAO bookDAO;
    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String admin_book_add(Model model) {
        List<BookDTO> bookList = bookDAO.showAll();

        model.addAttribute("bookList", bookList);

        return "bookAdd";
    }

    // adding books
    @PostMapping(value = "/bookAdd")
    public void admin_book_add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputBookID = request.getParameter("inputBookID");
            String inputBookName = request.getParameter("inputBookName");
            String inputBookWriter = request.getParameter("inputBookWriter");
            String inputBookGenre = request.getParameter("inputBokGenre");
            String inputBookCompany = request.getParameter("inputBookCompany");
            String inputBookISBN = request.getParameter("inputBookISBN");
            String inputBookYear = request.getParameter("inputBookYear");
            String inputBookEdition = request.getParameter("inputBookEdition");
            String inputBookVolume = request.getParameter("inputBookVolume");
            String inputBookIssue = request.getParameter("inputBookIssue");
            String inputBookSummary = request.getParameter("inputBookSummary").replaceAll("\r\n", "<br />");
            //Date bookRegister = java.util.Date

            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);

            if (bookDTO != null)
                throw new AlreadyExistingException("이미 존재하는 도서입니다.");

/*
            if (inputBookID.equals("") || inputBookGenre.equals("") || inputBookName.equals("")
                    || inputBookWriter.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals("")|| inputBookRegister.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


 */


            bookDTO = new BookDTO(inputBookID, inputBookName, inputBookGenre, inputBookWriter, inputBookCompany,
                    inputBookISBN, inputBookYear, inputBookEdition, inputBookVolume, inputBookIssue,
                    inputBookSummary);

            bookDTO = bookService.addBook(bookDTO);

            System.out.println(bookDTO.toString());

            response.sendRedirect("/bookAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='/bookAdd';</script>");

            out.flush();
        }
        /*
        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/bookAdd';</script>");

            out.flush();
        }

         */
    }
    //deleting books
    //updating books

}
