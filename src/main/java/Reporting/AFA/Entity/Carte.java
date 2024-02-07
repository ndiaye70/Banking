package Reporting.AFA.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@Data
public class Carte {
    @Id
    private String id;

    private String date;
    private String nomComplet ;
    private String tel;

    @Enumerated(EnumType.STRING)
    private Service service;

    private double montant;
    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;


    public enum Service {
        CARTE_COMMERCANT("CARTE COMMERCANT", 19200),
        CARTE_IMPORT_EXPORT("CARTE IMPORT EXPORT", 49750),
        Première_demande_PACK("Première demande Pack (C.C et C.I.E)", 62900),
        RENOUVELLEMENT_CARTE_COMMERCANT("Renouvellement CARTE COMMERCANT", 15400),
        RENOUVELLEMENT_CARTE_IMPORT_EXPORT("Renouvellement CARTE IMPORT EXPORT", 49750),
        RENOUVELLEMENT_PACK("Renouvellement Pack (C.C et C.I.E)", 59200);

        private final String label;
        private final double montant;

        Service(String label, double montant) {


            this.label = label;
            this.montant = montant;
        }

        public String getLabel() {
            return label;
        }

        public double getMontant() {
            return montant;
        }
    }


    private String generateId() {
        return UUID.randomUUID().toString();
    }

    // Constructeurs, getters et setters

    public Carte() {

        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);
    }


    // Autres méthodes de la classe Carte
}
