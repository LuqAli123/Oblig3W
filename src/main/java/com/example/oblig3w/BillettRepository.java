package com.example.oblig3w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BillettRepository {
    @Autowired
    private JdbcTemplate db;
    public void lagreKunde(Billett film){
        String sql = "INSERT INTO Billett(antall,fornavn,etternavn,film,epost,adresse,mobilnummer)"+
                "VALUES(?,?,?,?,?,?,?);";
        db.update(sql,film.getAntall(),film.getFornavn(),film.getEtternavn(),film.getFilm(),
                film.getEpost(),film.getAdresse(),film.getMobilnummer());
    }
    public List<Billett> hentAlt(){
        String sql = "SELECT*FROM Billett";
        List<Billett>billettRegister = db.query(sql, new BeanPropertyRowMapper(Billett.class));

        return billettRegister;
    }
    public Billett henteBillett(int id){
       String sql= "SELECT*FROM Billett WHERE id=?;";
       Billett billett =  db.queryForObject(sql,BeanPropertyRowMapper.newInstance(Billett.class),id);
       return billett;
    }
    public void endreBillett(Billett billettRegister){
        String sql = "UPDATE Billett SET film=?,fornavn=?,etternavn=?,adresse=?,mobilnummer=?,epost=? where id=?;";
        db.update(sql,billettRegister.getFilm(),billettRegister.getFornavn(),billettRegister.getEtternavn(),
                billettRegister.getAdresse(),billettRegister.getMobilnummer(),billettRegister.getEpost(),billettRegister.getId());
    }

    public void slettAlle(){
        String sql = "DELETE FROM Billett;";
        db.update(sql);
    }
    public void slettEn(long id){
        String sql = "DELETE FROM Billett WHERE id=?;";
        db.update(sql,id);
    }
}
