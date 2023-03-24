package com.example.finallier_project.controllers;

import com.example.finallier_project.service_implementation.*;
import com.example.finallier_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LotController {

    @Autowired
    LotService lotService = new LotServiceImpl();
    @Autowired
    MiastoService miastoService = new MiastoServiceImpl();
    @Autowired
    KlasyService klasyService = new KlasyServiceImpl();
    @Autowired
    PrzewoznicyService przewoznicyService = new PrzewoznicyServiceImpl();
    @RequestMapping(path = "lotyLista",method = RequestMethod.GET)
    public ModelAndView lotyLista(){
        var mav = new ModelAndView("admins_views/lotyLista");
        mav.addObject("listaLoty",lotService.getLoty());

        return mav;
    }

    @RequestMapping(path = "lotForm",method = RequestMethod.GET)
    public ModelAndView lotForm(
            @RequestParam(value = "id",required = false,defaultValue = "-1") long id
    ){
        var mav = new ModelAndView("admins_views/lotForm");
        var lot = lotService.getLotById(id);
        mav.addObject("listaMiasta",miastoService.miasta());
        mav.addObject("listaKlasy",klasyService.getKlasy());
        mav.addObject("listaPrzewoznicy",przewoznicyService.findAll());
        mav.addObject("lot",lot);
        return mav;
    }

    @RequestMapping(path = "editAddLot",method = RequestMethod.GET)
    public ModelAndView editAddLot(
            long id,
            long zmiasto,
            long domiasto,
            long przewoznik,
            long klasa,
            String data,
            boolean od_okna
    ){
        var mav = new ModelAndView("admins_views/lotyLista");
        System.out.println(id);
        System.out.println(zmiasto);
        System.out.println(domiasto);
        System.out.println(przewoznik);
        System.out.println(klasa);
        System.out.println(data);
        System.out.println(od_okna);
        lotService.updateLot(id,zmiasto,domiasto,przewoznik,klasa,data,od_okna);
        mav.addObject("listaLoty",lotService.getLoty());
        return mav;
    }
}
