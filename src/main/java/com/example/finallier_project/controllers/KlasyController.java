package com.example.finallier_project.controllers;

import com.example.finallier_project.service_implementation.KlasyServiceImpl;
import com.example.finallier_project.service_implementation.PrzewoznicyServiceImpl;
import com.example.finallier_project.services.KlasyService;
import com.example.finallier_project.services.PrzewoznicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KlasyController {

    @Autowired
    KlasyService klasyService = new KlasyServiceImpl();

    @RequestMapping(path = "klasyLista",method = RequestMethod.GET)
    public ModelAndView przewoznicyLista(){
        var mav = new ModelAndView("admins_views/klasyLista");
        mav.addObject("listaKlasy",klasyService.getKlasy());
        return mav;
    }

    @RequestMapping(path = "klasaForm",method = RequestMethod.GET)
    public ModelAndView przewoznicyForm(
            @RequestParam(value = "id",required = false,defaultValue = "-1") long id
    ){
        var mav = new ModelAndView("admins_views/klasyForm");
        var klasa = klasyService.findById(id);
        mav.addObject("klasa",klasa);
        return mav;
    }

    @RequestMapping(path = "editAddKlasa",method = RequestMethod.GET)
    public ModelAndView editAddKlasa(
            long id,
            String nazwa
    ){
        var mav = new ModelAndView("admins_views/klasyLista");
        klasyService.updateKlasa(id,nazwa);
        mav.addObject("listaKlasy",klasyService.getKlasy());
        return mav;
    }
}
