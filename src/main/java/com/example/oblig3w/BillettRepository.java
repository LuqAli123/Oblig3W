package com.example.oblig3w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BillettRepository {
    @Autowired
    private JdbcTemplate db;
    public void lagreKunde(Billett billettRegister){
        String sql = "INESRT INTO Billett(film,fornavn,etternavn,adresse,mobilnummer,epost) VALUES(?,?,?,?,?,?);";
        db.update(sql,billettRegister.getFornavn(),billettRegister.getEtternavn(),billettRegister.getFilm(),
                billettRegister.getEpost(),billettRegister.getAdresse(),billettRegister.getMobilnummer(),billettRegister.getId());
    }
    public List<Billett> hentAlt(){
        String sql = "SELECT*FROM Billett";
        List<Billett>billettRegister = db.query(sql, new BeanPropertyRowMapper(Billett.class));
        return billettRegister;
    }

    public void slettAlle(){
        String sql = "DELETE FROM Billett;";
        db.update(sql);
    }


}
