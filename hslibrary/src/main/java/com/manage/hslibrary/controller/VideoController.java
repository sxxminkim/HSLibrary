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
    //deleting books
    @RequestMapping(value = "/videoDelete", method = RequestMethod.GET)
    public String videoDelete(Model model) {
        List<VideoDTO> videoList = videoDAO.showAll();

        model.addAttribute("videoList", videoList);

        return "videoDelete";
    }

    // deleting books
    @PostMapping(value = "/videoDelete")
    public void videoDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoID = request.getParameter("inputVideoID");
            String inputVideoName = request.getParameter("inputVideoName");
            String inputVideoNameConfirm = request.getParameter("inputVideoNameConfirm");

            VideoDTO videoDTO = videoDAO.selectByVideoID(inputVideoID);
            VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoID(inputVideoID);

            if (videoRentDTO == null) // 대여한 사람이 있다는 것
                throw new AlreadyExistingException("해당 도서를 대여한 회원이 있습니다.");

            if (videoDTO == null)
                throw new NotExistingException("존재하지 않는 도서입니다.");
            else {
                if (videoDTO.getVideoName().equals(inputVideoName)) {
                    if (inputVideoName.equals(inputVideoNameConfirm)) {
                        videoService.deleteVideo(videoDTO);

                        response.sendRedirect("./videoDelete");
                    } else
                        throw new NotMatchingException("확인 제목과 맞지 않습니다.");
                } else
                    throw new NotExistingException("영상의 제목이 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 제목과 맞지 않습니다.'); location.href='/videoDelete';</script>");

            out.flush();
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('존재하지 않는 영상입니다.'); location.href='/videoDelete';</script>");

            out.flush();
        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('영상의 제목이 맞지 않습니다.'); location.href='/videoDelete';</script>");

            out.flush();
        }
        catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('해당 도서를 대여한 회원이 있습니다.'); location.href='/admin/book/delete';</script>");

            out.flush();
        }
    }

    //updating video
    @RequestMapping(value = "/videoUpdate", method = RequestMethod.GET)
    public String videoUpdate(Model model) {
        List<VideoDTO> videoList = videoDAO.showAll();

        model.addAttribute("videoList", videoList);

        return "videoUpdate";
    }

    //updating books
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

            response.sendRedirect("./videoUpdate");
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

    // book rent
    @PostMapping(value = "/videoRent")
    public void videoRent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoRentNUM = request.getParameter("inputBookRentNUM");
            String inputVideoID = request.getParameter("inputBookID");
            String inputClientNUM = request.getParameter("inputClientNUM");

            VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoID(inputVideoID);
            //

            if (videoRentDTO != null)
                throw new AlreadyExistingException("이미 누군가 빌려간 영상입니다.");

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

        }

    }
    @RequestMapping(value = "/videoReturn", method = RequestMethod.GET)
    public String videoReturn(Model model) {
        List<VideoRentDTO> videoRentList = videoRentDAO.showAll();

        model.addAttribute("videoRentList", videoRentList);

        return "videoReturn";
    }

    // deleting books
    @PostMapping(value = "/videoReturn")
    public void videoReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoRentNUM = request.getParameter("inputBookID");
            String inputVideoID = request.getParameter("inputBookName");
            String inputVideoIDConfirm = request.getParameter("inputBookNameConfirm");
            VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(inputVideoRentNUM);

            if (videoRentDTO == null)
                throw new NotExistingException("대출중인 도서가 아닙니다.");
            else {
                if (videoRentDTO.getVideoID().equals(inputVideoID)) {
                    if (inputVideoID.equals(inputVideoIDConfirm)) {
                        videoRentService.returnVideo(videoRentDTO);

                        response.sendRedirect("./videoReturn");
                    } else
                        throw new NotMatchingException("확인 영상 번호와 맞지 않습니다.");
                } else
                    throw new NotExistingException("영상 번호가 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 영상 번호와 맞지 않습니다.'); location.href='./videoReturn';</script>");

            out.flush();

            response.sendRedirect("./videoReturn");

        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('대출중인 영상이 아닙니다.'); location.href='./videoReturn';</script>");

            out.flush();


        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('영상 번호가 맞지 않습니다.'); location.href='./videoReturn';</script>");

            out.flush();

        }

    }

    //updating books
    @RequestMapping(value = "/videoExtend", method = RequestMethod.GET)
    public String videoExtend(Model model) {
        List<VideoRentDTO> videoRentList = videoRentDAO.showAll();

        model.addAttribute("videoRentList", videoRentList);

        return "videoExtend";
    }

    //updating books
    @PostMapping(value = "/videoExtend")
    public void videoExtend(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputVideoRentNUM = request.getParameter("inputBookRentalNUM");
            String inputVideoID = request.getParameter("inputBookID");
            String inputClientNUM = request.getParameter("inputClientNUM");


            VideoRentDTO videoRentDTO = videoRentDAO.selectByVideoRentalNUM(inputVideoRentNUM);

            if (videoRentDTO == null)
                throw new NotExistingException("연장할 영상이 없습니다.");

            if (inputVideoRentNUM.equals("") || inputVideoID.equals("") || inputClientNUM.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            videoRentDTO = new VideoRentDTO(inputVideoRentNUM, inputVideoID, inputClientNUM);

            videoRentDTO = videoRentService.extendVideo(videoRentDTO);

            System.out.println(videoRentDTO.toString());

            response.sendRedirect("./videoExtend");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('연장할 영상이 없습니다.'); location.href='./videoExtend';</script>");

            out.flush();

        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='./videoExtend';</script>");

            out.flush();

        }
    }



}
