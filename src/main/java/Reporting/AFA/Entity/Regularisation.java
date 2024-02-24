package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor

@Entity

public class Regularisation {

    @Id
    private String id;
    private String date;
    private String libelle;
    private double montant;
    @Enumerated(EnumType.STRING)
    private CompteCaisse compteCaisse;
    @Enumerated(EnumType.STRING)
    private Regularisateur regularisateur;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Regularisation(){
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }


    public enum CompteCaisse {

        Espéces_Caisse("Espéces-Caisse"), Orange_Money("Orange Money"), Free_Money("Free Money"),
        Wave("Wave"), RIA("RIA"), Western("Western");


        private final String label;

        CompteCaisse(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;

        }
    }

    public enum Regularisateur{
        Mr_KAMARA("M. KAMARA"),AFA("AFA");
        private final String label;

        Regularisateur(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;

        }

    }

}
