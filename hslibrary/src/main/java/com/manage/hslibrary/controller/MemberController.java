package com.manage.hslibrary.controller;

import com.manage.hslibrary.exception.AlreadyExistingException;
import com.manage.hslibrary.exception.FillOutInformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.manage.hslibrary.DTO.MemberDTO;
import com.manage.hslibrary.DAO.MemberDAO;
import com.manage.hslibrary.service.MemberService;
@ComponentScan(basePackages={"com.manage.hslibrary.DTO.MemberDTO"})


@Controller
public class MemberController {
    @Autowired
    MemberDTO memberDTO;
    @Autowired
    MemberDAO memberDAO;
    @Autowired
    MemberService memberService;

    @RequestMapping (value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    @PostMapping(value="/login")
    public void memberLogin(HttpServletRequest request, HttpServletResponse response){
        try {

            HttpSession session = request.getSession(true);
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputPW = request.getParameter("inputPW");

            MemberDTO memberDTO = memberService.loginMember(inputStaffNUM, inputPW);

            if (memberDTO == null) {
                System.out.println("login error in controller");
                response.sendRedirect("login");
            } else {

                session.setAttribute("loginStaffName", memberDTO.getStaffName());
                session.setAttribute("loginStaffDTO", memberDTO);
                response.sendRedirect("adminIndex");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    //login failure
    @GetMapping("/login?error")
    public String fail() throws IOException {
        return "redirect:/?error";
    }

    //logout
    @GetMapping("/logout")
    public String logout(final HttpSession session){
        if(session.getAttribute("loginStaffName")!=null)
            session.removeAttribute("loginStaffName");
        session.invalidate();

        return "redirect:/";
    }
    //admin register(admin add)
    @RequestMapping(value = "/adminRegister", method = RequestMethod.GET)
    public String admin_admin_add(Model model) {
        List<MemberDTO> memberList = memberDAO.showAll();

        model.addAttribute("memberList", memberList);

        return "adminRegister";
    }

    // admin add
    @PostMapping(value = "/adminRegister")
    public void admin_book_add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputStaffPW = request.getParameter("inputStaffPW");
            String inputStaffName = request.getParameter("inputStaffName");
            String inputStaffID = request.getParameter("inputStaffID");
            String inputStaffAddr = request.getParameter("inputStaffAddr");
            String inputStaffPhone = request.getParameter("inputStaffPhone");
            String inputStaffDeparture = request.getParameter("inputStaffDeparture");


            MemberDTO memberDTO = memberDAO.selectByStaffNUM(inputStaffNUM);

            if (memberDTO != null)
                throw new AlreadyExistingException("이미 존재하는 관리자입니다.");

/*
            if (inputStaffNUM.equals("") || inputStaffPW.equals("") || inputStaffName.equals("")
                    || inputStaffID.equals("") || inputStaffAddr.equals("")|| inputStaffPhone.equals("")
                    || inputStaffDeparture.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");


 */


            memberDTO = new MemberDTO(inputStaffNUM, inputStaffPW, inputStaffName,
                    inputStaffID, inputStaffAddr, inputStaffPhone, inputStaffDeparture);

            memberDTO = memberService.addMember(memberDTO);

            System.out.println(memberDTO.toString());

            response.sendRedirect("./adminRegister");
        } catch (AlreadyExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('이미 존재하는 관리자입니다.'); location.href='/adminRegister';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/adminRegister';</script>");

            out.flush();
        }
    }


}

