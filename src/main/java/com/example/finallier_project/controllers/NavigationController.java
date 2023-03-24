package com.example.finallier_project.controllers;

import com.example.finallier_project.models.Uzytkownik;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class NavigationController {

    @GetMapping("/index")
    public ModelAndView index() throws ParseException{
        var mav = new ModelAndView("index");
        return mav;
    }
    @GetMapping("/login")
    public ModelAndView login() throws ParseException{
        var mav = new ModelAndView("login");
        return mav;
    }
    @GetMapping("/error403")
    public ModelAndView error403() throws ParseException{
        var mav = new ModelAndView("403");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView reg() throws ParseException{
        var mav = new ModelAndView("rejestracja");
        mav.addObject("user",new Uzytkownik());
        return mav;
    }
}
