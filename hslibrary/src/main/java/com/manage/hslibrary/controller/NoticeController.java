package com.manage.hslibrary.controller;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.exception.*;
import com.manage.hslibrary.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeDAO noticeDAO;
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/notice_detail", method = RequestMethod.GET)
    public String notice_detail(Model model, @RequestParam(defaultValue ="1")int noticeNUM) {
        model.addAttribute("noticeNUM", noticeNUM);
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(noticeNUM);

        model.addAttribute("noticeDTO", noticeDTO);
        System.out.println(noticeNUM);


        return "notice_detail";
    }
    @RequestMapping(value = "/notice_subview", method = RequestMethod.GET)
    public String notice_subview(Model model, @RequestParam(defaultValue ="1")int noticeNUM) {
        model.addAttribute("noticeNUM", noticeNUM);
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(noticeNUM);

        model.addAttribute("noticeDTO", noticeDTO);
        System.out.println(noticeNUM);


        return "notice_subview";
    }
    @RequestMapping(value = "/noticeList", method = RequestMethod.GET)
    public String noticeList(Model model, @RequestParam(defaultValue ="1")int noticeNUM) {
        List<NoticeDTO> noticeList=noticeDAO.showAll();
        model.addAttribute("noticeList", noticeList);

        return ("noticeList");
    }

    @RequestMapping(value="/noticeAdd", method=RequestMethod.GET)
    public String noticeAdd(Model model){
        List<NoticeDTO> noticeList=noticeDAO.showAll();
        model.addAttribute("noticeList", noticeList);

        return ("noticeAdd");
    }
    @PostMapping(value = "/noticeAdd")
    public void noticeAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputNoticeTitle = request.getParameter("inputNoticeTitle");
            String inputNoticeAuthor = request.getParameter("inputNoticeAuthor");
            String inputNoticeMain = request.getParameter("inputNoticeMain").replaceAll("\r\n", "<br />");

            NoticeDTO noticeDTO = new NoticeDTO(inputNoticeTitle,inputNoticeAuthor,inputNoticeMain);


            if (inputNoticeTitle.equals("") || inputNoticeAuthor.equals("") || inputNoticeMain.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");

            noticeDTO = new NoticeDTO(inputNoticeTitle, inputNoticeAuthor, inputNoticeMain);

            noticeDTO = noticeService.addNotice(noticeDTO);

            System.out.println(noticeDTO.toString());


            response.sendRedirect("./noticeAdd");
        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./noticeAdd';</script>");

            out.flush();

        }

    }
    @RequestMapping(value = "/noticeDelete", method = RequestMethod.GET)
    public String noticeDelete(Model model, @RequestParam(defaultValue ="1")int noticeNUM) {
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(noticeNUM);
        noticeService.deleteNotice(noticeDTO);
        return "redirect:noticeAdd";
    }

    @RequestMapping(value = "/noticeUpdate", method = RequestMethod.GET)
    public String noticeUpdate(Model model, @RequestParam(defaultValue ="1")int noticeNUM) {
        model.addAttribute("noticeNUM", noticeNUM);
        NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(noticeNUM);

        model.addAttribute("noticeDTO", noticeDTO);


        return "noticeUpdate";
    }
    @PostMapping(value = "/noticeUpdate")
    public void noticeUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputNoticeNUM = request.getParameter("inputNoticeNUM");
            String inputNoticeTitle = request.getParameter("inputNoticeTitle");
            String inputNoticeAuthor = request.getParameter("inputNoticeAuthor");
            String inputNoticeMain = request.getParameter("inputNoticeMain").replaceAll("\r\n", "<br />");

            NoticeDTO noticeDTO = noticeDAO.selectByNoticeNUM(Integer.parseInt(inputNoticeNUM));

            if (noticeDTO == null)
                throw new NotExistingException("수정할 공지가 없습니다.");

            if (inputNoticeTitle.equals("") || inputNoticeAuthor.equals("") || inputNoticeMain.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            noticeDTO = new NoticeDTO(inputNoticeTitle, inputNoticeAuthor, inputNoticeMain);

            noticeDTO = noticeService.updateNotice(noticeDTO);


            response.sendRedirect("./noticeAdd");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 공지가 없습니다.'); location.href='./noticeUpdate';</script>");

            out.flush();



        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./noticeUpdate';</script>");

            out.flush();

        }
    }
}
