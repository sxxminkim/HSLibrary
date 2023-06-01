package com.manage.hslibrary.controller;

import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.service.*;
import com.manage.hslibrary.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@ComponentScan(basePackages={"com.manage.hslibrary.DTO.StaffDTO"})
@Controller
public class StaffController {
    @Autowired
    StaffDTO staffDTO;
    @Autowired
    StaffDAO staffDAO;
    @Autowired
    StaffService staffService;

    //login
    @RequestMapping (value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }
    //login
    @PostMapping(value="/login")
    public void memberLogin(HttpServletRequest request, HttpServletResponse response){
        try {

            HttpSession session = request.getSession(true);
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputPW = request.getParameter("inputPW");

            StaffDTO staffDTO = staffService.loginMember(inputStaffNUM, inputPW);

            if (staffDTO == null) {
                System.out.println("login error in controller");
                response.sendRedirect("login");
            } else {

                session.setAttribute("loginStaffName", staffDTO.getStaffName());
                session.setAttribute("loginStaffDTO", staffDTO);
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
    public String adminRegister(Model model) {
        List<StaffDTO> memberList = staffDAO.showAll();

        model.addAttribute("memberList", memberList);

        return "adminRegister";
    }

    // admin add
    @PostMapping(value = "/adminRegister")
    public void adminRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputStaffPW = request.getParameter("inputStaffPW");
            String inputStaffName = request.getParameter("inputStaffName");
            String inputStaffID = request.getParameter("inputStaffID");
            String inputStaffAddr = request.getParameter("inputStaffAddr");
            String inputStaffPhone = request.getParameter("inputStaffPhone");
            String inputStaffDepartment = request.getParameter("inputStaffDepartment");


            StaffDTO staffDTO = staffDAO.selectByStaffNUM(inputStaffNUM);

            if (staffDTO != null)
                throw new AlreadyExistingException("이미 존재하는 관리자입니다.");

            if (inputStaffNUM.equals("") || inputStaffPW.equals("") || inputStaffName.equals("")
                    || inputStaffID.equals("") || inputStaffAddr.equals("")|| inputStaffPhone.equals("")
                    || inputStaffDepartment.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");



            staffDTO = new StaffDTO(inputStaffNUM, inputStaffPW, inputStaffName,
                    inputStaffID, inputStaffAddr, inputStaffPhone, inputStaffDepartment);

            staffDTO = staffService.addStaff(staffDTO);

            System.out.println(staffDTO.toString());

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
    //deleting admin
    @RequestMapping(value = "/adminDelete", method = RequestMethod.GET)
    public String adminDelete(Model model) {
        List<StaffDTO> memberList = staffDAO.showAll();

        model.addAttribute("memberList", memberList);

        return "adminDelete";
    }

    // deleting admin
    @PostMapping(value = "/adminDelete")
    public void adminDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputStaffName = request.getParameter("inputStaffName");
            String inputStaffNameConfirm = request.getParameter("inputStaffNameConfirm");

            StaffDTO staffDTO = staffDAO.selectByStaffNUM(inputStaffNUM);

            if (staffDTO == null)
                throw new NotExistingException("존재하지 않는 관리자입니다.");
            else {
                if (staffDTO.getStaffName().equals(inputStaffName)) {
                    if (inputStaffName.equals(inputStaffNameConfirm)) {
                        staffService.deleteStaff(staffDTO);

                        response.sendRedirect("./adminDelete");
                    } else
                        throw new NotMatchingException("확인 이름과 맞지 않습니다.");
                } else
                    throw new NotExistingException("관리자의 이름이 맞지 않습니다.");
            }
        } catch (NotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('확인 이름과 맞지 않습니다.'); location.href='/adminDelete';</script>");

            out.flush();
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('존재하지 않는 관리자입니다.'); location.href='/adminDelete';</script>");

            out.flush();
        } catch (ConfirmNotMatchingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('관리자의 이름이 맞지 않습니다.'); location.href='/adminDelete';</script>");

            out.flush();
        }

    }
    //updating admin information
    @RequestMapping(value = "/adminUpdate", method = RequestMethod.GET)
    public String adminUpdate(Model model, @RequestParam(defaultValue ="1")String staffNUM) {
        model.addAttribute("staffNUM", staffNUM);
        StaffDTO staffDTO = staffDAO.selectByStaffNUM(staffNUM);

        model.addAttribute("staffDTO", staffDTO);
        System.out.println(staffNUM);

        return "adminUpdate";
    }

    // updating admin information
    @PostMapping(value = "/adminUpdate")
    public void adminUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String inputStaffNUM = request.getParameter("inputStaffNUM");
            String inputStaffPW = request.getParameter("inputStaffPW");
            String inputStaffName = request.getParameter("inputStaffName");
            String inputStaffID = request.getParameter("inputStaffID");
            String inputStaffAddr = request.getParameter("inputStaffAddr");
            String inputStaffPhone = request.getParameter("inputStaffPhone");
            String inputStaffDepartment = request.getParameter("inputStaffDepartment");


            StaffDTO staffDTO = staffDAO.selectByStaffNUM(inputStaffNUM);

            if (staffDTO == null)
                throw new NotExistingException("존재하지 않는 관리자입니다.");

            if (inputStaffNUM.equals("") || inputStaffPW.equals("") || inputStaffName.equals("")
                    || inputStaffID.equals("") || inputStaffAddr.equals("")|| inputStaffPhone.equals("")
                    || inputStaffDepartment.equals(""))
                throw new FillOutInformationException("모든 정보를 입력해주세요.");



            staffDTO = new StaffDTO(inputStaffNUM, inputStaffPW, inputStaffName,
                    inputStaffID, inputStaffAddr, inputStaffPhone, inputStaffDepartment);

            staffDTO = staffService.updateStaff(staffDTO);

            System.out.println(staffDTO.toString());

            response.sendRedirect("./adminRegister");
        } catch (NotExistingException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('존재하지 않는 관리자입니다.'); location.href='/adminUpdate';</script>");

            out.flush();
        } catch (FillOutInformationException ex) {
            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('모든 정보를 입력해주세요.'); location.href='/adminUpdate';</script>");

            out.flush();
        }
    }

}

