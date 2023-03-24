package com.example.finallier_project.controllers;

import com.example.finallier_project.models.*;
import com.example.finallier_project.service_implementation.*;
import com.example.finallier_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService = new UserServiceImpl();
    @Autowired
    MiastoService miastoService = new MiastoServiceImpl();
    @Autowired
    LotService lotService = new LotServiceImpl();
    @Autowired
    KlasyService klasyService = new KlasyServiceImpl();
    @Autowired
    BiletService biletService = new BiletServiceImpl();
    @RequestMapping(path = "/validate",method = RequestMethod.POST)
    public ModelAndView validate(
            @ModelAttribute(value = "user")Uzytkownik uzytkownik
            ) throws Exception{
        System.out.println("Sprawdzam dane " + uzytkownik.getUsername());
        userService.validate(uzytkownik);
        return new ModelAndView("index");
    }

    @RequestMapping(path = "/zamow",method = RequestMethod.GET)
    public ModelAndView zamow(){
        var mav = new ModelAndView("users_views/miasta");
        var listaMiasto = miastoService.miasta();
        mav.addObject("zmiasto",new Miasto());
        mav.addObject("domiasto",new Miasto());
        mav.addObject("lot",new Lot());
        mav.addObject("listaMiasto",listaMiasto);
        return mav;
    }

    @RequestMapping(path = "/zamow_lista",method = RequestMethod.GET)
    public ModelAndView zamow_lista(
        int zmiasto,
        int domiasto,
        String data
    ){
        Lot lot = new Lot();
        lot.setData(LocalDate.parse(data));
        var mav = new ModelAndView("users_views/listaLot");
        Miasto zMiasto = miastoService.findMiasto(zmiasto);
        Miasto doMiasto = miastoService.findMiasto(domiasto);
        List<Lot> listaLot = lotService.getLoty(zMiasto,doMiasto,lot.getData());
        mav.addObject("listaLot",listaLot);
        return mav;
    }

    @RequestMapping(path = "/zamow_submit",method = RequestMethod.GET)
    public ModelAndView zamow_submit(
            int id
    ){
       var mav = new ModelAndView("users_views/bilet");
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       var uzytkownik = (User)authentication.getPrincipal();
       Lot lot = lotService.getLotById(id);
       Bilet bilet = new Bilet();
       bilet.setLot(lot);
       bilet.setCena(biletService.setCena(lot));
       bilet.setUzytkownik(userService.getByUsername(uzytkownik.getUsername()));
       mav.addObject("bilet",bilet);
       return mav;
    }

    @RequestMapping(path = "/zakoncz",method = RequestMethod.GET)
    public ModelAndView zakoncz(
            int id,
            float cena,
            String username
    ){
        var mav = new ModelAndView("index");
        Bilet bilet = new Bilet();
        bilet.setLot(lotService.getLotById(id));
        bilet.setCena(cena);
        bilet.setUzytkownik(userService.getByUsername(username));
        biletService.zapisz(bilet);
        return mav;
    }

    @RequestMapping(path = "/twoje_bilety",method = RequestMethod.GET)
    public ModelAndView twoje_bilety(){
        var mav = new ModelAndView("users_views/twoje_bilety");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = (User)authentication.getPrincipal();
        List<Bilet> listaBilet = biletService.getBilety(user.getUsername());
        mav.addObject("listaBilet", listaBilet);
        return mav;
    }

    @RequestMapping(path = "/loty",method = RequestMethod.GET)
    public ModelAndView loty(){
        var mav = new ModelAndView("users_views/loty");
        List<Lot> loty = lotService.getLoty();
        mav.addObject("loty",loty);
        return mav;
    }

}
