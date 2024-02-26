package Reporting.AFA.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@AllArgsConstructor
@Entity
@Data
public class SoldeDepart {
    @Id
    private String id;
    private String date;
    private double orangeMoney;
    private double freeMoney;
    private double wave;
    private double wizall;
    private double westernUnion;
    private double ria;
    private double moneygram;
    private double ecobank;
    private double smallWorld;
    private double bnb;
    private double askia;
    private double sunuAssurance;
    private double creationEntreprise;
    private double cartesComImpExp;
    private double change;
    private double grossisteBnb;
    private double bndeDepotRetrait;
    private double ouvertureCompteBnde;
    private double ubaCartesPrepayees;
    private double ubaDepotRetrait;

    public SoldeDepart() {

        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);

    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }


}
