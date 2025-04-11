package com.generation.sitoillogico.controller;

import com.generation.sitoillogico.model.dao.AltroDao;
import com.generation.sitoillogico.model.dao.QualcosaDao;
import com.generation.sitoillogico.model.dao.UtenteDao;
import com.generation.sitoillogico.model.entities.Altro;
import com.generation.sitoillogico.model.entities.Qualcosa;
import com.generation.sitoillogico.model.entities.Utente;
import com.generation.sitoillogico.model.entities.enums.Enumeratore;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.time.LocalDate;
import java.util.List;

@SessionAttributes({"utente"})
@Controller
public class Cla
{
    @Autowired
    QualcosaDao qualcosaDao;

    @Autowired
    AltroDao altroDao;

    @Autowired
    UtenteDao utenteDao;

    @GetMapping("/qwe")
    public String abc(@SessionAttribute(required = false) Utente utente)
    {
        if (utente == null)
            return "nonSeiLoggato";

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
    public String uio(Model model, @SessionAttribute(required = false) Utente utente)
    {
        if (utente == null)
            return "nonSeiLoggato";

        List<Qualcosa> tuttiQualcosa = qualcosaDao.findAll();
        List<Qualcosa> qualcosaFiltrati = tuttiQualcosa.stream().
                filter(qualcosa -> qualcosa.getC() == Enumeratore.BBB).
                toList();

        model.addAttribute("oggettiQualcosaFiltrati", qualcosaFiltrati);
        return "ghi";
    }

    @GetMapping("/pas")
    public String pas(@SessionAttribute(required = false) Utente utente)
    {
        if (utente == null)
            return "nonSeiLoggato";

        return "lmn";
    }

    @GetMapping("/DESTRUCTION")
    public String destruction(@SessionAttribute(required = false) Utente utente)
    {
        if (utente == null)
            return "nonSeiLoggato";

        qualcosaDao.deleteAll();

        return "desolazione";
    }

    @GetMapping("/dfg")
    public String dfg(Model model, Long a)
    {
        model.addAttribute("idQualcosa", qualcosaDao.findById(a).orElse(null));
        return "opq";
    }

    @PostMapping("/dfg")
    public String PostOfg
            (
                    @RequestParam Long a,
                    @RequestParam String f
            )
    {
        Qualcosa qualcosa = qualcosaDao.findById(a).orElse(null);

        Altro altro = new Altro();
        altro.setF(f);
        altro.setQualcosa(qualcosa);

        altroDao.save(altro);

        return "redirect:/dfg?a=" + a;
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String username, @RequestParam String password)
    {
        String passwordHashata = DigestUtils.md5Hex(password);
        Utente utente = utenteDao.findByUsernameAndPassword(username, passwordHashata);

        if (utente == null)
            return "utenteNotFound";

        model.addAttribute("utente", utente);

        return "loginCorretto";
    }

    @GetMapping("/registrati")
    public String apriPaginaRegistrazione()
    {
        return "registrazione";
    }

    @PostMapping("/registrati")
    public String registrati(@RequestParam String username, @RequestParam String password)
    {
        Utente utente = new Utente();
        utente.setUsername(username);

        String passwordHashata = DigestUtils.md5Hex(password);
        utente.setPassword(passwordHashata);

        utenteDao.save(utente);

        return "redirect:/";
    }

    @GetMapping("/sloggati")
    public String sloggati(SessionStatus sessionStatus, @SessionAttribute(required = false) Utente utente)
    {
        if (utente == null)
            return "nonSeiLoggato";

        sessionStatus.setComplete();

        return "redirect:/";
    }
}
