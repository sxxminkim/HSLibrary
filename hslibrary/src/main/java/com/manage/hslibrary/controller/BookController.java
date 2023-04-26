package com.manage.hslibrary.controller;

import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.exception.*;
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
import java.util.List;
@Controller

public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BookDAO bookDAO;
    @RequestMapping(value = "/bookAdd", method = RequestMethod.GET)
    public String bookAdd(Model model) {
        List<BookDTO> bookList = bookDAO.showAll();

        model.addAttribute("bookList", bookList);

        return "bookAdd";
    }

    // adding books
    @PostMapping(value = "/bookAdd")
    public void bookAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputBookID = request.getParameter("inputBookID");
            String inputBookName = request.getParameter("inputBookName");
            String inputBookWriter = request.getParameter("inputBookWriter");
            String inputBookGenre = request.getParameter("inputBookGenre");
            String inputBookCompany = request.getParameter("inputBookCompany");
            String inputBookISBN = request.getParameter("inputBookISBN");
            String inputBookYear = request.getParameter("inputBookYear");
            String inputBookEdition = request.getParameter("inputBookEdition");
            String inputBookVolume = request.getParameter("inputBookVolume");
            String inputBookIssue = request.getParameter("inputBookIssue");
            String inputBookSummary = request.getParameter("inputBookSummary").replaceAll("\r\n", "<br />");


            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);

            if (bookDTO != null)
                throw new AlreadyExistingException("이미 존재하는 도서입니다.");

            if (inputBookID.equals("") || inputBookName.equals("") || inputBookWriter.equals("")
                    || inputBookGenre.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");




            bookDTO = new BookDTO(inputBookID, inputBookName, inputBookWriter, inputBookGenre,
                    inputBookCompany, inputBookISBN, inputBookYear, inputBookEdition, inputBookVolume,
                    inputBookIssue, inputBookSummary);

            bookDTO = bookService.addBook(bookDTO);

            System.out.println(bookDTO.toString());

            response.sendRedirect("./bookAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='/bookAdd';</script>");

            out.flush();
        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/bookAdd';</script>");

            out.flush();
        }

    }
    //deleting books
    @RequestMapping(value = "/bookDelete", method = RequestMethod.GET)
    public String bookDelete(Model model) {
        List<BookDTO> bookList = bookDAO.showAll();

        model.addAttribute("bookList", bookList);

        return "bookDelete";
    }

    // deleting books
    @PostMapping(value = "/bookDelete")
    public void bookDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputBookID = request.getParameter("inputBookID");
            String inputBookName = request.getParameter("inputBookName");
            String inputBookNameConfirm = request.getParameter("inputBookNameConfirm");

            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);
/*
            List<RentDTO> RentDTO = RentDAO.selectByISBN(inputBookISBN);

            if (checkOutDTO == null) // 대여한 사람이 있다는 것
                throw new AlreadyExistingException("해당 도서를 대여한 회원이 있습니다.");


 */
            if (bookDTO == null)
                throw new NotExistingException("존재하지 않는 도서입니다.");
            else {
                if (bookDTO.getBookName().equals(inputBookName)) {
                    if (inputBookName.equals(inputBookNameConfirm)) {
                        bookService.deleteBook(bookDTO);

                        response.sendRedirect("./bookDelete");
                    } else
                        throw new NotMatchingException("확인 제목과 맞지 않습니다.");
                } else
                    throw new NotExistingException("책의 제목이 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 제목과 맞지 않습니다.'); location.href='/bookDelete';</script>");

            out.flush();
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('존재하지 않는 도서입니다.'); location.href='/bookDelete';</script>");

            out.flush();
        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('책의 제목이 맞지 않습니다.'); location.href='/bookDelete';</script>");

            out.flush();
        }
        /*
        catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('해당 도서를 대여한 회원이 있습니다.'); location.href='/admin/book/delete';</script>");

            out.flush();
        }

         */
    }

    //updating books
    @RequestMapping(value = "/bookUpdate", method = RequestMethod.GET)
    public String bookUpdate(Model model) {
        List<BookDTO> bookList = bookDAO.showAll();

        model.addAttribute("bookList", bookList);

        return "bookUpdate";
    }

    //updating books
    @PostMapping(value = "/bookUpdate")
    public void bookUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputBookID = request.getParameter("inputBookID");
            String inputBookName = request.getParameter("inputBookName");
            String inputBookWriter = request.getParameter("inputBookWriter");
            String inputBookGenre = request.getParameter("inputBookGenre");
            String inputBookCompany = request.getParameter("inputBookCompany");
            String inputBookISBN = request.getParameter("inputBookISBN");
            String inputBookYear = request.getParameter("inputBookYear");
            String inputBookEdition = request.getParameter("inputBookEdition");
            String inputBookVolume = request.getParameter("inputBookVolume");
            String inputBookIssue = request.getParameter("inputBookIssue");
            String inputBookSummary = request.getParameter("inputBookSummary").replaceAll("\r\n", "<br />");

            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);

            if (bookDTO == null)
                throw new NotExistingException("수정할 도서가 없습니다.");
/*
            if (!_inputBookImage.isEmpty()) {
                try {
                    String uploadDir = "/bookImageStorage/";
                    String realPathUpload = request.getServletContext().getRealPath(uploadDir);

                    String fileName = _inputBookImage.getOriginalFilename();
                    String filePath = realPathUpload + fileName;

                    File files = new File(filePath);
                    _inputBookImage.transferTo(files);

                    inputBookImage = fileName;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


 */
            if (inputBookID.equals("") || inputBookName.equals("") || inputBookWriter.equals("")
                    || inputBookGenre.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            bookDTO = new BookDTO(inputBookID, inputBookName, inputBookWriter, inputBookGenre,
                    inputBookCompany, inputBookISBN, inputBookYear, inputBookEdition, inputBookVolume,
                    inputBookIssue, inputBookSummary);

            bookDTO = bookService.updateBook(bookDTO);

            System.out.println(bookDTO.toString());

            response.sendRedirect("./bookUpdate");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 도서가 없습니다.'); location.href='/bookUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/bookUpdate';</script>");

            out.flush();
        }
    }

}
