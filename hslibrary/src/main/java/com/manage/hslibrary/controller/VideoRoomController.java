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
    //deleting books
    @RequestMapping(value = "/VideoRoomDelete", method = RequestMethod.GET)
    public String VideoRoomDelete(Model model) {
        List<VideoRoomDTO> videoRoomList = videoRoomDAO.showAll();

        model.addAttribute("videoRoomList", videoRoomList);

        return "VideoRoomDelete";
    }

    // deleting books
    @PostMapping(value = "/VideoRoomDelete")
    public void VideoRoomDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");
            String inputVidRoomNUMConfirm = request.getParameter("inputVidRoomNUMConfirm");

            VideoRoomDTO videoRoomDTO = videoRoomDAO.selectByVid_roomNUM(inputVidRoomNUM);
            VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVid_roomNUM(inputVidRoomNUM);

            if (vidRoomRentDTO == null) // 대여한 사람이 있다는 것
                throw new AlreadyExistingException("해당 도서를 대여한 회원이 있습니다.");

            if (videoRoomDTO == null)
                throw new NotExistingException("존재하지 않는 도서입니다.");
            else {
                if (videoRoomDTO.getVid_roomNUM().equals(inputVidRoomNUM)) {
                    if (inputVidRoomNUM.equals(inputVidRoomNUMConfirm)) {
                        videoRoomService.deleteVideoRoom(videoRoomDTO);

                        response.sendRedirect("./VideoRoomDelete");
                    } else
                        throw new NotMatchingException("확인 제목과 맞지 않습니다.");
                } else
                    throw new NotExistingException("영상의 제목이 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 제목과 맞지 않습니다.'); location.href='/VideoRoomDelete';</script>");

            out.flush();
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('존재하지 않는 영상입니다.'); location.href='/VideoRoomDelete';</script>");

            out.flush();
        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('영상의 제목이 맞지 않습니다.'); location.href='/VideoRoomDelete';</script>");

            out.flush();
        }
        catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('해당 도서를 대여한 회원이 있습니다.'); location.href='/VideoRoomDelete';</script>");

            out.flush();
        }
    }

    //updating video
    @RequestMapping(value = "/VideoRoomUpdate", method = RequestMethod.GET)
    public String VideoRoomUpdate(Model model) {
        List<VideoRoomDTO> videoRoomList = videoRoomDAO.showAll();

        model.addAttribute("videoRoomList", videoRoomList);

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

            response.sendRedirect("./VideoRoomUpdate");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 영상이 없습니다.'); location.href='/VideoRoomUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/videoUpdate';</script>");

            out.flush();
        }
    }
    @RequestMapping(value = "/VideoRoomRent", method = RequestMethod.GET)
    public String videoRent(Model model) {
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
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");
            String inputClientNUM = request.getParameter("inputClientNUM");

            VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVidRoomRentalNUM(inputVidRoomRentNUM);
            //

            if (vidRoomRentDTO != null)
                throw new AlreadyExistingException("이미 누군가 빌려간 영상입니다.");

            if (inputVidRoomRentNUM.equals("") || inputVidRoomNUM.equals("") || inputClientNUM.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");

            vidRoomRentDTO = new VidRoomRentDTO(inputVidRoomRentNUM, inputVidRoomNUM, inputClientNUM);

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
    @RequestMapping(value = "/VideoRoomReturn", method = RequestMethod.GET)
    public String VideoRoomReturn(Model model) {
        List<VidRoomRentDTO> vidRoomRentList = vidRoomRentDAO.showAll();

        model.addAttribute("vidRoomRentList", vidRoomRentList);

        return "VideoRoomReturn";
    }

    // deleting books
    @PostMapping(value = "/VideoRoomReturn")
    public void VideoRoomReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVidRoomRentNUM = request.getParameter("inputVidRoomRentNUM");
            String inputVidRoomNUM = request.getParameter("inputVidRoomNUM");
            String inputVidRoomNUMConfirm= request.getParameter("inputBookVidRoomNUMConfirm");
            VidRoomRentDTO vidRoomRentDTO = vidRoomRentDAO.selectByVidRoomRentalNUM(inputVidRoomRentNUM);

            if (vidRoomRentDTO == null)
                throw new NotExistingException("대출중인 도서가 아닙니다.");
            else {
                if (vidRoomRentDTO.getVideoRoomRentalNUM().equals(inputVidRoomRentNUM)) {
                    if (inputVidRoomNUM.equals(inputVidRoomNUMConfirm)) {
                        vidRoomRentService.returnVidRoom(vidRoomRentDTO);

                        response.sendRedirect("./VideoRoomReturn");
                    } else
                        throw new NotMatchingException("확인 영상 번호와 맞지 않습니다.");
                } else
                    throw new NotExistingException("영상 번호가 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 영상 번호와 맞지 않습니다.'); location.href='./VideoRoomReturn';</script>");

            out.flush();

            response.sendRedirect("./VideoRoomReturn");

        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('대출중인 영상이 아닙니다.'); location.href='./VideoRoomReturn';</script>");

            out.flush();


        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('영상 번호가 맞지 않습니다.'); location.href='./VideoRoomReturn';</script>");

            out.flush();

        }

    }



}
