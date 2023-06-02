package com.manage.hslibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.DAO.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    VideoDAO videoDAO;
    @Autowired
    NoticeDAO noticeDAO;
    @Autowired
    MemberDAO memberDAO;
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index(Model model){

        //home-page
        return ("index");
    }

    @RequestMapping(value="/adminIndex", method= RequestMethod.GET)
    public String adminIndex(Model model){

        //home-page
        return ("adminIndex");
    }
    @RequestMapping(value="/allList", method= RequestMethod.GET)
    public String allList(Model model){
        List<BookDTO> bookList=bookDAO.showAll();
        List<VideoDTO> videoList=videoDAO.showAll();
        model.addAttribute("bookList", bookList);
        model.addAttribute("videoList", videoList);


        return ("allList");
    }
    @RequestMapping(value="/map", method= RequestMethod.GET)
    public String map(Model model){

        //map
        return ("map");
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
