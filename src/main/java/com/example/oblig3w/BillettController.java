package com.example.oblig3w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class BillettController {
    @Autowired
    BillettRepository rep;
    @PostMapping("/lagreKunde")
    public void lagreKunde(Billett film){
        rep.lagreKunde(film);
    }
    @GetMapping("/hentAlt")
    public List<Billett> hentAlt(){
        return rep.hentAlt();
    }
    @GetMapping("/slettBillett")
    public void slettBillett(){
        rep.slettAlle();
    }
}
