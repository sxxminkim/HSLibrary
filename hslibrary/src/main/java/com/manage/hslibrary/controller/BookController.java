package com.manage.hslibrary.controller;

import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.exception.*;
import com.manage.hslibrary.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
@Controller

public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    BookRentDAO bookRentDAO;
    @Autowired
    BookRentService bookRentService;
    @Autowired
    MemberDAO memberDAO;

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
            String inputBookType = request.getParameter("inputBookType");


            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);

            if (bookDTO != null)
                throw new AlreadyExistingException("이미 존재하는 도서입니다.");

            if (inputBookID.equals("") || inputBookName.equals("") || inputBookWriter.equals("")
                    || inputBookGenre.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals("")|| inputBookType.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");




            bookDTO = new BookDTO(inputBookID, inputBookName, inputBookWriter, inputBookGenre,
                    inputBookCompany, inputBookISBN, inputBookYear, inputBookEdition, inputBookVolume,
                    inputBookIssue, inputBookSummary, Integer.parseInt(inputBookType));

            bookDTO = bookService.addBook(bookDTO);

            System.out.println(bookDTO.toString());

            response.sendRedirect("./bookAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='./bookAdd';</script>");

            out.flush();

        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./bookAdd';</script>");

            out.flush();

        }

    }
    //deleting books
    @RequestMapping(value="/bookDelete", method = RequestMethod.GET)
    public String bookDelete(Model model, @RequestParam(defaultValue ="1")String bookID) throws Exception {
        BookDTO bookDTO=bookDAO.selectByBookID(bookID);
        bookService.deleteBook(bookDTO);
        return "redirect:bookAdd";
    }

    //updating books
    @RequestMapping(value = "/bookUpdate", method = RequestMethod.GET)
    public String bookUpdate(Model model, @RequestParam(defaultValue ="1")String bookID) {
        model.addAttribute("bookID", bookID);
        BookDTO bookDTO = bookDAO.selectByBookID(bookID);

        model.addAttribute("bookDTO", bookDTO);
        System.out.println(bookID);


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
            String inputBookType=request.getParameter("inputBookType");
            BookDTO bookDTO = bookDAO.selectByBookID(inputBookID);

            if (bookDTO == null)
                throw new NotExistingException("수정할 도서가 없습니다.");

            if (inputBookID.equals("") || inputBookName.equals("") || inputBookWriter.equals("")
                    || inputBookGenre.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals("")|| inputBookType.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            bookDTO = new BookDTO(inputBookID, inputBookName, inputBookWriter, inputBookGenre,
                    inputBookCompany, inputBookISBN, inputBookYear, inputBookEdition, inputBookVolume,
                    inputBookIssue, inputBookSummary,Integer.parseInt(inputBookType));

            bookDTO = bookService.updateBook(bookDTO);

            System.out.println(bookDTO.toString());

            response.sendRedirect("./bookAdd");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 도서가 없습니다.'); location.href='./bookUpdate';</script>");

            out.flush();



        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./bookUpdate';</script>");

            out.flush();

        }
    }
    @RequestMapping(value = "/bookRent", method = RequestMethod.GET)
    public String bookRent(Model model) {
        List<BookDTO> bookList = bookDAO.showAll();
        List<MemberDTO> memberList = memberDAO.showAll();
        List<BookRentDTO> bookRentList = bookRentDAO.showAll();

        model.addAttribute("bookList", bookList);
        model.addAttribute("memberList", memberList);
        model.addAttribute("bookRentList", bookRentList);

        return "bookRent";
    }

    // book rent
    @PostMapping(value = "/bookRent")
    public void bookRent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputBookRentNUM = request.getParameter("inputBookRentNUM");
            String inputBookID = request.getParameter("inputBookID");
            String inputClientNUM = request.getParameter("inputClientNUM");


            BookRentDTO bookRentDTO = bookRentDAO.selectByBookID(inputBookID);
            BookRentDTO _bookRentDTO= bookRentDAO.selectByClientNUM(inputClientNUM);
            if (bookRentDTO != null)
                throw new AlreadyExistingException("이미 누군가 빌려간 도서입니다.");
            if (_bookRentDTO !=null)
                throw new NotAvailableException("이미 대출한 회원입니다.");

            if (inputBookRentNUM.equals("") || inputBookID.equals("") || inputClientNUM.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");

            bookRentDTO = new BookRentDTO(inputBookRentNUM, inputBookID, inputClientNUM);

            bookRentDTO = bookRentService.rentBook(bookRentDTO);

            System.out.println(bookRentDTO.toString());

            response.sendRedirect("./bookRent");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 누군가 빌려간 도서입니다.'); location.href='./bookRent';</script>");

            out.flush();

        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./bookRent';</script>");

            out.flush();
        } catch (NotAvailableException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 대출한 회원입니다.'); location.href='./bookRent';</script>");

            out.flush();
        }

    }
    @RequestMapping(value="/bookReturn", method = RequestMethod.GET)
    public String bookReturn(Model model, @RequestParam(defaultValue ="1")String bookRentalNUM) throws Exception {
        BookRentDTO bookRentDTO=bookRentDAO.selectByBookRentalNUM(bookRentalNUM);
        bookRentService.returnBook(bookRentDTO);
        return "redirect:bookRent";
    }
    @RequestMapping(value="/bookExtend", method = RequestMethod.GET)
    public String bookExtend(Model model, @RequestParam(defaultValue ="1")String bookRentalNUM) throws Exception {
        BookRentDTO bookRentDTO=bookRentDAO.selectByBookRentalNUM(bookRentalNUM);
        bookRentService.extendBook(bookRentDTO);
        return "redirect:bookRent";
    }

    @RequestMapping(value = "/book_detail", method = RequestMethod.GET)
    public String book_detail(Model model, @RequestParam(defaultValue ="1")String bookID) {
        model.addAttribute("bookID", bookID);
        BookDTO bookDTO = bookDAO.selectByBookID(bookID);

        model.addAttribute("bookDTO", bookDTO);

        return "book_detail";
    }
    @RequestMapping(value = "/book_subview", method = RequestMethod.GET)
    public String book_subview(Model model, @RequestParam(defaultValue ="1")String bookID) {
        model.addAttribute("bookID", bookID);
        BookDTO bookDTO = bookDAO.selectByBookID(bookID);

        model.addAttribute("bookDTO", bookDTO);

        return "book_subview";
    }
    @RequestMapping(value = "/bookRent_detail", method = RequestMethod.GET)
    public String bookRent_detail(Model model, @RequestParam(defaultValue ="1")String bookRentalNUM) {
        model.addAttribute("bookRentalNUM", bookRentalNUM);
        BookRentDTO bookRentDTO = bookRentDAO.selectByBookRentalNUM(bookRentalNUM);

        model.addAttribute("bookRentDTO", bookRentDTO);

        return "bookRent_detail";
    }

}

