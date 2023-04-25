package com.manage.hslibrary.controller;

import com.manage.hslibrary.DAO.VideoDAO;
import com.manage.hslibrary.DTO.VideoDTO;
import com.manage.hslibrary.exception.AlreadyExistingException;
import com.manage.hslibrary.exception.FillOutInformationException;
import com.manage.hslibrary.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
@Controller
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    VideoDAO videoDAO;

    @RequestMapping(value = "/videoAdd", method = RequestMethod.GET)
    public String admin_video_add(Model model) {
        List<VideoDTO> videoList = videoDAO.showAll();

        model.addAttribute("videoList", videoList);

        return "videoAdd";
    }

    //adding videos
    @PostMapping(value = "/videoAdd")
    public void admin_video_add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoID = request.getParameter("inputVideoID");
            String inputVideoName = request.getParameter("inputVideoName");
            String inputVideoDirector = request.getParameter("inputVideoDirector");
            String inputVideoCompany = request.getParameter("inputVideoCompany");
            String inputVideoRelease = request.getParameter("inputVideoRelease");
            String inputVideoGenre = request.getParameter("inputVideoGenre");
            String inputVideoSequel = request.getParameter("inputVideoSequel");


            VideoDTO videoDTO = videoDAO.selectByVideoID(inputVideoID);

            if (videoDTO != null)
                throw new AlreadyExistingException("이미 존재하는 영상입니다.");

/*
            if (inputBookID.equals("") || inputBookGenre.equals("") || inputBookName.equals("")
                    || inputBookWriter.equals("") || inputBookCompany.equals("")|| inputBookISBN.equals("")
                    || inputBookYear.equals("")|| inputBookEdition.equals("")|| inputBookVolume.equals("")
                    || inputBookIssue.equals("")|| inputBookSummary.equals("")|| inputBookRegister.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


 */


            videoDTO = new VideoDTO(inputVideoID, inputVideoName, inputVideoDirector, inputVideoCompany, inputVideoRelease,
                    inputVideoGenre, inputVideoSequel);

            videoDTO = videoService.addVideo(videoDTO);

            System.out.println(videoDTO.toString());

            response.sendRedirect("/videoAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 도서입니다.'); location.href='/videoAdd';</script>");

            out.flush();
        }
        /*
        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/videoAdd';</script>");

            out.flush();
        }

         */
    }
    //deleting videos
    //updating videos


}
