package com.manage.hslibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.*;

import java.io.IOException;

import com.manage.hslibrary.DTO.MemberDTO;
import com.manage.hslibrary.DAO.MemberDAO;
import com.manage.hslibrary.service.MemberService;
@ComponentScan(basePackages={"com.manage.hslibrary.DTO.MemberDTO"})


@Controller
public class LoginController {
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
            String inputID = request.getParameter("inputID");
            String inputPW = request.getParameter("inputPW");

            MemberDTO memberDTO = memberService.loginMember(inputID, inputPW);

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


}

