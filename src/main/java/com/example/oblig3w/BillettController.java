package com.example.oblig3w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class BillettController {
    @Autowired
    private BillettRepository rep;
    @PostMapping("/lagreKunde")
    public void lagreKunde(@RequestBody Billett film){
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
    @GetMapping("/slettEn")
    public void slettEn(int id){
        rep.slettEn(id);
    }
    @PostMapping("/endring")
    public void endre(Billett billett){
        rep.endreBillett(billett);
    }
    @GetMapping("/henteEn")
    public Billett hentEn(int id){
        return rep.henteBillett(id);
    }
}
