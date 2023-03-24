package com.example.finallier_project.controllers;

import com.example.finallier_project.service_implementation.PrzewoznicyServiceImpl;
import com.example.finallier_project.services.PrzewoznicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrzewoznicyController {
    @Autowired
    PrzewoznicyService przewoznicyService = new PrzewoznicyServiceImpl();
    @RequestMapping(path = "przewoznicyLista",method = RequestMethod.GET)
    public ModelAndView przewoznicyLista(){
        var mav = new ModelAndView("admins_views/przewoznicyLista");
        mav.addObject("listaPrzewoznicy",przewoznicyService.findAll());
        return mav;
    }

    @RequestMapping(path = "przewoznikForm",method = RequestMethod.GET)
    public ModelAndView przewoznicyForm(
            @RequestParam(value = "id",required = false,defaultValue = "-1") long id
    ){
        var mav = new ModelAndView("admins_views/przewoznicyForm");
        var przewoznik = przewoznicyService.findById(id);
        mav.addObject("przewoznik",przewoznik);
        return mav;
    }

    @RequestMapping(path = "editAddPrzewoznik",method = RequestMethod.GET)
    public ModelAndView editAddPrzewoznik(
            long id,
            String nazwa
    ){
        var mav = new ModelAndView("admins_views/przewoznicyLista");
        przewoznicyService.updatePrzewoznik(id,nazwa);
        mav.addObject("listaPrzewoznicy",przewoznicyService.findAll());
        return mav;
    }
}
