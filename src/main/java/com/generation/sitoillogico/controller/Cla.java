package com.generation.sitoillogico.controller;

import com.generation.sitoillogico.model.dao.QualcosaDao;
import com.generation.sitoillogico.model.entities.Qualcosa;
import com.generation.sitoillogico.model.entities.enums.Enumeratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class Cla
{
    @Autowired
    QualcosaDao qualcosaDao;

    @GetMapping("/qwe")
    public String abc()
    {
        return "abc";
    }

    @PostMapping("/qwe")
    public String abcForm
            (
                    @RequestParam String b,
                    @RequestParam Enumeratore c,
                    @RequestParam LocalDate d,
                    Model model
                    )
    {
        Qualcosa qualcosa = new Qualcosa();
        qualcosa.setB(b);
        qualcosa.setC(c);
        qualcosa.setD(d);

        qualcosaDao.save(qualcosa);

        return "abc";
    }

    @GetMapping("/rty")
    public String rty(Model model)
    {
        model.addAttribute("oggettiQualcosa", qualcosaDao.findAll());
        return "def";
    }

    @GetMapping("/uio")
    public String uio(Model model)
    {
        List<Qualcosa> tuttiQualcosa = qualcosaDao.findAll();
        List<Qualcosa> qualcosaFiltrati = tuttiQualcosa.stream().
                filter(qualcosa -> qualcosa.getC() == Enumeratore.BBB).
                toList();

        model.addAttribute("oggettiQualcosaFiltrati", qualcosaFiltrati);
        return "ghi";
    }

    @GetMapping("/pas")
    public String pas()
    {
        return "lmn";
    }

    @GetMapping("/DESTRUCTION")
    public String destruction()
    {
        qualcosaDao.deleteAll();

        return "desolazione";
    }
}
