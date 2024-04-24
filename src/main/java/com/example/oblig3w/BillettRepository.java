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
    public void lagreKunde(Billett billettRegister){
        String sql = "INSERT INTO Billett(fornavn,etternavn,film,epos,adresse,mobilnummer,id)"+
                "VALUES(?,?,?,?,?,?);";
        db.update(sql,billettRegister.getFornavn(),billettRegister.getEtternavn(),billettRegister.getFilm(),
                billettRegister.getEpost(),billettRegister.getAdresse(),billettRegister.getMobilnummer(),billettRegister.getId());
    }
    public List<Billett> hentAlt(){
        String sql = "SELECT*FROM Billett GROUP BY fornavn";
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
    public void slettEn(long mobillnummer){
        String sql = "DELETE FROM Billett WHERE id=?;";
        db.update(sql,mobillnummer);
    }
}
