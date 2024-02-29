package Reporting.AFA.Entity;

import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@Data
public class SoldeCaisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String date;

    private double ouvertureCaisseXof;
    private double fermetureCaisseXof;
    private double ouvertureCaisseEur;
    private double fermetureCaisseEur;
    private double ouvertureCaisseUsd;
    private double fermetureCaisseUsd;


    public SoldeCaisse() {
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);


    }






    // Constructeurs, getters et setters à ajouter ici
}
