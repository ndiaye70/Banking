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
public class Commissions {
    @Id
    private String id;
    private String date;
    private double commissionsOM;
    private double commissionsFreeMoney;
    private double commissionsWave;
    private double commissionsWizall;
    private double commissionsWesternUnion;
    private double commissionsRia;
    private double commissionsMoneygram;
    private double commissionsEcobank;
    private double commissionsSmallWorld;
    private double commissionsBnB;
    private double commissionsAskia;
    private double commissionsSunuAssurance;
    private double commissionsCreationEntreprise;
    private double commissionsCartesComImpExp;
    private double commissionsChange;
    private double commissionsGrossisteBnb;
    private double commissionsBndeDepotRetrait;
    private double commissionsOuvertureCompteBnde;
    private double commissionsUbaCartesPrepayees;
    private double commissionsUbaDepotRetrait;

    public Commissions() {

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
