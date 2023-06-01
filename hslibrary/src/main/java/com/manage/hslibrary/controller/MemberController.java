package com.manage.hslibrary.controller;

import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.exception.*;
import com.manage.hslibrary.service.MemberService;
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
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberDAO memberDAO;
    @Autowired
    BookDAO bookDAO;
    @Autowired
    VideoDAO videoDAO;

    @RequestMapping(value = "/memberAdd", method = RequestMethod.GET)
    public String memberAdd(Model model) {
        List<MemberDTO> memberList = memberDAO.showAll();

        model.addAttribute("memberList", memberList);

        return "memberAdd";
    }

    //adding members
    @PostMapping(value = "/memberAdd")
    public void memberAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputClientNUM = request.getParameter("inputClientNUM");
            String inputClientName = request.getParameter("inputClientName");
            String inputClientID = request.getParameter("inputClientID");
            String inputClientPhone = request.getParameter("inputClientPhone");
            String inputClientAddr = request.getParameter("inputClientAddr");
            String inputClientEmail = request.getParameter("inputClientEmail");
            
            MemberDTO memberDTO = memberDAO.selectByClientNUM(inputClientNUM);

            if (memberDTO != null)
                throw new AlreadyExistingException("이미 존재하는 회원입니다.");


            if (inputClientNUM.equals("") || inputClientName.equals("") || inputClientID.equals("")
                    || inputClientPhone.equals("") || inputClientAddr.equals("")|| inputClientEmail.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            memberDTO = new MemberDTO(inputClientNUM, inputClientName, inputClientID, inputClientPhone, inputClientAddr,
                    inputClientEmail);

            memberDTO = memberService.addMember(memberDTO);

            System.out.println(memberDTO.toString());

            response.sendRedirect("./memberAdd");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 회원입니다.'); location.href='/memberAdd';</script>");

            out.flush();
        }

        catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/memberAdd';</script>");

            out.flush();
        }

    }
    //deleting members
    @RequestMapping(value="/memberDelete", method = RequestMethod.GET)
    public String memberDelete(Model model, @RequestParam(defaultValue ="1")String clientNUM) throws Exception {
        MemberDTO memberDTO=memberDAO.selectByClientNUM(clientNUM);
        memberService.deleteMember(memberDTO);
        return "redirect:memberAdd";
    }


    //updating client information
    @RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
    public String memberUpdate(Model model, @RequestParam(defaultValue ="1")String clientNUM) {
        model.addAttribute("clientNUM", clientNUM);
        MemberDTO memberDTO = memberDAO.selectByClientNUM(clientNUM);

        model.addAttribute("memberDTO", memberDTO);
        System.out.println(clientNUM);


        return "memberUpdate";
    }

    //updating client Information
    @PostMapping(value = "/memberUpdate")
    public void bookUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputClientNUM = request.getParameter("inputClientNUM");
            String inputClientName = request.getParameter("inputClientName");
            String inputClientID = request.getParameter("inputClientID");
            String inputClientPhone = request.getParameter("inputClientPhone");
            String inputClientAddr = request.getParameter("inputClientAddr");
            String inputClientEmail = request.getParameter("inputClientEmail");

            MemberDTO memberDTO = memberDAO.selectByClientNUM(inputClientNUM);

            if (memberDTO == null)
                throw new NotExistingException("수정할 회원 정보가 없습니다.");


            if (inputClientNUM.equals("") || inputClientName.equals("") || inputClientID.equals("")
                    || inputClientPhone.equals("") || inputClientAddr.equals("")|| inputClientEmail.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


            memberDTO = new MemberDTO(inputClientNUM, inputClientName, inputClientID, inputClientPhone, inputClientAddr,
                    inputClientEmail);

            memberDTO = memberService.updateMember(memberDTO);

            System.out.println(memberDTO.toString());

            response.sendRedirect("./memberAdd");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('수정할 영상이 없습니다.'); location.href='/memberUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/memberUpdate';</script>");

            out.flush();
        }
    }

    //연체회원
    @RequestMapping(value="/delayList", method= RequestMethod.GET)
    public String delayList(Model model){
        List<MemberDTO> bookBlackList=memberDAO.showBook();
        List<MemberDTO> videoBlackList=memberDAO.showVideo();
        model.addAttribute("bookBlackList", bookBlackList);
        model.addAttribute("videoBlackList", videoBlackList);

        //home-page
        return ("delayList");
    }
    //회원 상세보기 - 수정/삭제 method 포함
    @RequestMapping(value = "/member_detail", method = RequestMethod.GET)
    public String member_detail(Model model, @RequestParam(defaultValue ="1")String clientNUM) {
        model.addAttribute("clientNUM", clientNUM);
        MemberDTO memberDTO = memberDAO.selectByClientNUM(clientNUM);

        model.addAttribute("memberDTO", memberDTO);
        System.out.println(clientNUM);


        return "member_detail";
    }
}
