package com.example.finallier_project.controllers;

import com.example.finallier_project.models.Klasa;
import com.example.finallier_project.service_implementation.KrajServiceImpl;
import com.example.finallier_project.services.KrajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KrajeController {
    @Autowired
    KrajService krajService = new KrajServiceImpl();
    @RequestMapping(path = "krajeLista",method = RequestMethod.GET)
    public ModelAndView krajeLista(){
        var mav = new ModelAndView("admins_views/krajeLista");
        mav.addObject("listaKraje",krajService.findAll());
        return mav;
    }

    @RequestMapping(path = "krajForm",method = RequestMethod.GET)
    public ModelAndView krajForm(
            @RequestParam(value = "id",required = false,defaultValue = "-1") long id
    ){
        var mav = new ModelAndView("admins_views/krajForm");
        var kraj = krajService.findKraj(id);
        mav.addObject("kraj",kraj);
        return mav;
    }

    @RequestMapping(path = "editAddKraj",method = RequestMethod.GET)
    public ModelAndView editAddKraj(
            long id,
            String nazwa
    ){
        var mav = new ModelAndView("admins_views/krajeLista");
        krajService.updateKraj(id,nazwa);
        mav.addObject("listaKraje",krajService.findAll());
        return mav;
    }

}
