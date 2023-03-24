package com.example.finallier_project.controllers;

import com.example.finallier_project.models.Miasto;
import com.example.finallier_project.service_implementation.KrajServiceImpl;
import com.example.finallier_project.service_implementation.MiastoServiceImpl;
import com.example.finallier_project.service_implementation.UserServiceImpl;
import com.example.finallier_project.services.KrajService;
import com.example.finallier_project.services.MiastoService;
import com.example.finallier_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    UserService userService = new UserServiceImpl();
    @RequestMapping(path = "/uzytkownicy",method = RequestMethod.GET)
    public ModelAndView uzytkownicy(){
        var mav = new ModelAndView("admins_views/uzytkownicy");
        var listaUzytkownik = userService.getAllUsers();
        mav.addObject("listaUzytkownik",listaUzytkownik);
        return mav;
    }

    @RequestMapping(path = "zmienStanUzytkownika",method = RequestMethod.GET)
    public ModelAndView zmienStanUzytkownika(
            @RequestParam(value = "id") long id
    ){
        var mav = new ModelAndView("admins_views/uzytkownicy");
        userService.changeEnabled(id);
        mav.addObject("listaUzytkownik",userService.getAllUsers());
        return mav;
    }

    @RequestMapping(path = "zmienUprawnieniaUzytkownika",method = RequestMethod.GET)
    public ModelAndView zmienUprawnieniaUzytkownika(
            @RequestParam(value = "id") long id
    ){
        var mav = new ModelAndView("admins_views/uzytkownicy");
        userService.changeRole(id);
        mav.addObject("listaUzytkownik",userService.getAllUsers());
        return mav;
    }
}
