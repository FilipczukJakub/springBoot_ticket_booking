package com.example.finallier_project.controllers;

import com.example.finallier_project.service_implementation.KrajServiceImpl;
import com.example.finallier_project.service_implementation.MiastoServiceImpl;
import com.example.finallier_project.services.KrajService;
import com.example.finallier_project.services.MiastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MiastaController {
    @Autowired
    MiastoService miastoService = new MiastoServiceImpl();
    @Autowired
    KrajService krajService = new KrajServiceImpl();
    @RequestMapping(path = "miastaLista",method = RequestMethod.GET)
    public ModelAndView miastaLista(){
        var mav = new ModelAndView("admins_views/miastaLista");
        mav.addObject("listaMiasta",miastoService.miasta());
        return mav;
    }

    @RequestMapping(path = "miastoForm",method = RequestMethod.GET)
    public ModelAndView miastoForm(
            @RequestParam(value = "id",required = false,defaultValue = "-1") long id
    ){
        var mav = new ModelAndView("admins_views/miastoForm");
        var miasto = miastoService.findMiasto(id);
        mav.addObject("listaKraj",krajService.findAll());
        mav.addObject("miasto",miasto);
        return mav;
    }

    @RequestMapping(path = "editAddMiasto",method = RequestMethod.GET)
    public ModelAndView editAddMiasto(
            long id,
            String nazwa,
            long kraj
    ){
        var mav = new ModelAndView("admins_views/miastaLista");
        miastoService.updateMiasto(id,nazwa,kraj);
        mav.addObject("listaMiasta",miastoService.miasta());
        return mav;
    }
}
