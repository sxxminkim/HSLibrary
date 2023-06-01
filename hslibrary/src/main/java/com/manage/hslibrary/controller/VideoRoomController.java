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
public class VideoRoomController {
    @Autowired
    VideoRoomDAO videoRoomDAO;
    @Autowired
    VideoRoomService videoRoomService;
    @Autowired
    VidRoomRentDAO vidRoomRentDAO;
    @Autowired
    VidRoomRentService vidRoomRentService;
    @Autowired
    MemberDAO memberDAO;
    @RequestMapping(value = "/VideoRoomAdd", method = RequestMethod.GET)
    public String VideoRoomAdd(Model model) {
        List<VideoRoomDTO> videoRoomList = videoRoomDAO.showAll();

        model.addAttribute("videoRoomList", videoRoomList);

        return "VideoRoomAdd";
    }

    //adding video rooms
    @PostMapping(value = "/VideoRoomAdd")
    public void VideoRoomAddAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");
            String inputVidRoomName = request.getParameter("inputVidRoomName");


            VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(inputVidRoomNUM);

            if (videoRoomDTO != null)
                throw new AlreadyExistingException("이미 존재하는 영상입니다.");


            if (inputVidRoomNUM.equals("") || inputVidRoomName.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            videoRoomDTO = new VideoRoomDTO(inputVidRoomNUM,inputVidRoomName);

            videoRoomDTO = videoRoomService.addVideoRoom(videoRoomDTO);

            System.out.println(videoRoomDTO.toString());

            response.sendRedirect("./VideoRoomAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 영상입니다.'); location.href='/VideoRoomAdd';</script>");

            out.flush();
        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/VideoRoomAdd';</script>");

            out.flush();
        }

    }
    //deleting video room
    @RequestMapping(value="/VideoRoomDelete", method = RequestMethod.GET)
    public String VideoRoomDelete(Model model, @RequestParam(defaultValue ="1")String vid_roomNUM) throws Exception {
        VideoRoomDTO videoRoomDTO=videoRoomDAO.selectByVid_roomNUM(vid_roomNUM);
        videoRoomService.deleteVideoRoom(videoRoomDTO);
        return "redirect:VideoRoomAdd";
    }

    //updating video room
    @RequestMapping(value = "/VideoRoomUpdate", method = RequestMethod.GET)
    public String VideoRoomUpdate(Model model, @RequestParam(defaultValue ="1")String vid_roomNUM) {
        model.addAttribute("vid_roomNUM", vid_roomNUM);
        VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(vid_roomNUM);

        model.addAttribute("videoRoomDTO", videoRoomDTO);

        return "VideoRoomUpdate";
    }

    //updating books
    @PostMapping(value = "/VideoRoomUpdate")
    public void VideoRoomUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");
            String inputVidRoomName = request.getParameter("inputVidRoomName");

            VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(inputVidRoomNUM);

            if (videoRoomDTO == null)
                throw new NotExistingException("수정할 영상이 없습니다.");

            if (inputVidRoomNUM.equals("") || inputVidRoomName.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            videoRoomDTO = new VideoRoomDTO(inputVidRoomNUM, inputVidRoomName);

            videoRoomDTO = videoRoomService.updateVideoRoom(videoRoomDTO);

            System.out.println(videoRoomDTO.toString());

            response.sendRedirect("./VideoRoomAdd");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 시청각실이 없습니다.'); location.href='/VideoRoomUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/videoUpdate';</script>");

            out.flush();
        }
    }
    @RequestMapping(value = "/VideoRoomRent", method = RequestMethod.GET)
    public String VideoRoomRent(Model model) {
        List<VidRoomRentDTO> vidRoomRentList = vidRoomRentDAO.showAll();
        List<VideoRoomDTO> videoRoomList = videoRoomDAO.showAll();
        List<MemberDTO> memberList = memberDAO.showAll();

        model.addAttribute("vidRoomRentList", vidRoomRentList);
        model.addAttribute("videoRoomList", videoRoomList);
        model.addAttribute("memberList", memberList);

        return "VideoRoomRent";
    }

    // book rent
    @PostMapping(value = "/VideoRoomRent")
    public void VideoRoomRent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVidRoomRentNUM = request.getParameter("inputVidRoomRentNUM");
            String inputClientNUM = request.getParameter("inputClientNUM");
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");

            VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVidRoomRentalNUM(inputVidRoomRentNUM);
            //

            if (vidRoomRentDTO != null)
                throw new AlreadyExistingException("이미 누군가 빌려간 영상입니다.");

            if (inputVidRoomRentNUM.equals("") || inputClientNUM.equals("")|| inputVidRoomNUM.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");

            vidRoomRentDTO = new VidRoomRentDTO(inputVidRoomRentNUM, inputClientNUM, inputVidRoomNUM);

            vidRoomRentDTO = vidRoomRentService.rentVidRoom(vidRoomRentDTO);

            System.out.println(vidRoomRentDTO.toString());

            response.sendRedirect("./VideoRoomRent");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 누군가 빌려간 영상입니다.'); location.href='./VideoRoomRent';</script>");

            out.flush();

        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./VideoRoomRent';</script>");

            out.flush();

        }

    }
    @RequestMapping(value="/videoRoomReturn", method = RequestMethod.GET)
    public String videoRoomReturn(Model model, @RequestParam(defaultValue ="1")String vid_roomNUM) throws Exception {
        VidRoomRentDTO vidRoomRentDTO=vidRoomRentDAO.selectByVid_roomNUM(vid_roomNUM);
        vidRoomRentService.returnVidRoom(vidRoomRentDTO);
        return "redirect:videoRoomRent";
    }
    @RequestMapping(value = "/videoRoom_detail", method = RequestMethod.GET)
    public String videoRoom_detail(Model model, @RequestParam(defaultValue ="1")String vid_roomNUM) {
        //List<NoticeDTO> noticeDTO=noticeDAO.showOne(noticeNUM);
        model.addAttribute("vid_roomNUM", vid_roomNUM);
        VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(vid_roomNUM);

        model.addAttribute("videoRoomDTO", videoRoomDTO);

        return "videoRoom_detail";
    }

}
