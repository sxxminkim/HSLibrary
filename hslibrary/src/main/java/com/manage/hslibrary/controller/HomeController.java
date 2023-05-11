package com.manage.hslibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.manage.hslibrary.DTO.*;
import com.manage.hslibrary.DAO.*;
import com.manage.hslibrary.service.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BookDAO bookDAO;
    @Autowired
    VideoDAO videoDAO;
    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String index(Model model){
        List<BookDTO> bookList=bookDAO.showAll();
        List<VideoDTO> videoList=videoDAO.showAll();
        model.addAttribute("bookList",bookList);
        model.addAttribute("videoList",videoList);

        //home-page
        return ("index");
    }

    @GetMapping("/adminIndex")
    public String adminIndex(){
        //admin home-page
        return "adminIndex";
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
