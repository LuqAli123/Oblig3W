package com.example.oblig3w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void slettEn(@RequestParam("id") Long id){
        if (id!=null) {
        rep.slettEn(id);
        }
    }
    @PostMapping("/endring")
    public void endre(Billett billett){
        rep.endreBillett(billett);
    }
    @GetMapping("/henteEn")
    public Billett hentEn(Integer id){
        return rep.henteBillett(id);
    }
}
