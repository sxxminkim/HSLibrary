package com.manage.hslibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        logger.info("method [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");	// 로그에 메소드명 나타내기x
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login(Model model) {
        logger.info("method [" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]");
        return "login";	// login.jsp 호출!!!
    }

}
