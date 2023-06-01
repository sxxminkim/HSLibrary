package com.manage.hslibrary.controller;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
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
public class VideoController {
    @Autowired
    VideoRentDAO videoRentDAO;
    @Autowired
    VideoService videoService;
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    VideoRentService videoRentService;
    @Autowired
    MemberDAO memberDAO;

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


            if (inputVideoID.equals("") || inputVideoName.equals("") || inputVideoDirector.equals("")
                    || inputVideoCompany.equals("") || inputVideoRelease.equals("")|| inputVideoGenre.equals("")
                    || inputVideoSequel.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");




            videoDTO = new VideoDTO(inputVideoID, inputVideoName, inputVideoDirector, inputVideoCompany, inputVideoRelease,
                    inputVideoGenre, inputVideoSequel);

            videoDTO = videoService.addVideo(videoDTO);

            System.out.println(videoDTO.toString());

            response.sendRedirect("./videoAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 영상입니다.'); location.href='/videoAdd';</script>");

            out.flush();
        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/videoAdd';</script>");

            out.flush();
        }

    }
    //deleting videos
    @RequestMapping(value="/videoDelete", method = RequestMethod.GET)
    public String videoDelete(Model model, @RequestParam(defaultValue ="1")String videoID) throws Exception {
        VideoDTO videoDTO=videoDAO.selectByVideoID(videoID);
        videoService.deleteVideo(videoDTO);
        return "redirect:videoAdd";
    }
    //updating videos
    @RequestMapping(value = "/videoUpdate", method = RequestMethod.GET)
    public String videoUpdate(Model model, @RequestParam(defaultValue ="1")String videoID) {
        model.addAttribute("videoID", videoID);
        VideoDTO videoDTO = videoDAO.selectByVideoID(videoID);

        model.addAttribute("videoDTO", videoDTO);
        System.out.println(videoID);


        return "videoUpdate";
    }
    //updating videos
    @PostMapping(value = "/videoUpdate")
    public void bookUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoID = request.getParameter("inputVideoID");
            String inputVideoName = request.getParameter("inputVideoName");
            String inputVideoDirector = request.getParameter("inputVideoDirector");
            String inputVideoCompany = request.getParameter("inputVideoCompany");
            String inputVideoRelease = request.getParameter("inputVideoRelease");
            String inputVideoGenre = request.getParameter("inputVideoGenre");
            String inputVideoSequel = request.getParameter("inputVideoSequel");

            VideoDTO videoDTO = videoDAO.selectByVideoID(inputVideoID);

            if (videoDTO == null)
                throw new NotExistingException("수정할 영상이 없습니다.");

            if (inputVideoID.equals("") || inputVideoName.equals("") || inputVideoDirector.equals("")
                    || inputVideoCompany.equals("") || inputVideoRelease.equals("")|| inputVideoGenre.equals("")
                    || inputVideoSequel.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            videoDTO = new VideoDTO(inputVideoID, inputVideoName, inputVideoDirector, inputVideoCompany, inputVideoRelease,
                    inputVideoGenre, inputVideoSequel);

            videoDTO = videoService.updateVideo(videoDTO);

            System.out.println(videoDTO.toString());

            response.sendRedirect("./videoAdd");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 영상이 없습니다.'); location.href='/videoUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/videoUpdate';</script>");

            out.flush();
        }
    }
    //videoRent
    @RequestMapping(value = "/videoRent", method = RequestMethod.GET)
    public String videoRent(Model model) {
        List<VideoRentDTO> videoRentList = videoRentDAO.showAll();
        List<VideoDTO> videoList = videoDAO.showAll();
        List<MemberDTO> memberList = memberDAO.showAll();

        model.addAttribute("videoRentList", videoRentList);
        model.addAttribute("videoList", videoList);
        model.addAttribute("memberList", memberList);

        return "videoRent";
    }
    // videoRent
    @PostMapping(value = "/videoRent")
    public void videoRent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoRentNUM = request.getParameter("inputVideoRentNUM");
            String inputVideoID = request.getParameter("inputVideoID");
            String inputClientNUM = request.getParameter("inputClientNUM");

            VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoID(inputVideoID);
            VideoRentDTO _videoRentDTO= videoRentDAO.selectByClientNUM(inputClientNUM);

            if (videoRentDTO != null)
                throw new AlreadyExistingException("이미 누군가 빌려간 영상입니다.");
            if (_videoRentDTO !=null)
                throw new NotAvailableException("이미 대출한 회원입니다.");

            if (inputVideoRentNUM.equals("") || inputVideoID.equals("") || inputClientNUM.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");

            videoRentDTO = new VideoRentDTO(inputVideoRentNUM, inputVideoID, inputClientNUM);

            videoRentDTO = videoRentService.rentVideo(videoRentDTO);

            System.out.println(videoRentDTO.toString());

            response.sendRedirect("./videoRent");

        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 누군가 빌려간 영상입니다.'); location.href='./videoRent';</script>");

            out.flush();

        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./videoRent';</script>");

            out.flush();

        }catch (NotAvailableException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 대출한 회원입니다.'); location.href='./videoRent';</script>");

            out.flush();
        }

    }
    @RequestMapping(value="/videoReturn", method = RequestMethod.GET)
    public String videoReturn(Model model, @RequestParam(defaultValue ="1")String videoRentalNUM) throws Exception {
        VideoRentDTO videoRentDTO=videoRentDAO.selectByVideoRentalNUM(videoRentalNUM);
        videoRentService.returnVideo(videoRentDTO);
        return "redirect:videoRent";
    }
    @RequestMapping(value="/videoExtend", method = RequestMethod.GET)
    public String videoExtend(Model model, @RequestParam(defaultValue ="1")String videoRentalNUM) throws Exception {
        VideoRentDTO videoRentDTO=videoRentDAO.selectByVideoRentalNUM(videoRentalNUM);
        videoRentService.extendVideo(videoRentDTO);
        return "redirect:videoRent";
    }

    @RequestMapping(value = "/video_detail", method = RequestMethod.GET)
    public String video_detail(Model model, @RequestParam(defaultValue ="1")String videoID) {
        //List<NoticeDTO> noticeDTO=noticeDAO.showOne(noticeNUM);
        model.addAttribute("videoID", videoID);
        VideoDTO videoDTO = videoDAO.selectByVideoID(videoID);

        model.addAttribute("videoDTO", videoDTO);

        return "video_detail";
    }
    @RequestMapping(value = "/video_subview", method = RequestMethod.GET)
    public String video_subview(Model model, @RequestParam(defaultValue ="1")String videoID) {
        //List<NoticeDTO> noticeDTO=noticeDAO.showOne(noticeNUM);
        model.addAttribute("videoID", videoID);
        VideoDTO videoDTO = videoDAO.selectByVideoID(videoID);

        model.addAttribute("videoDTO", videoDTO);

        return "video_subview";
    }
    @RequestMapping(value = "/videoRent_detail", method = RequestMethod.GET)
    public String videoRent_detail(Model model, @RequestParam(defaultValue ="1")String videoRentalNUM) {
        model.addAttribute("videoRentalNUM", videoRentalNUM);
        VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(videoRentalNUM);

        model.addAttribute("videoRentDTO", videoRentDTO);

        return "videoRent_detail";
    }

}
