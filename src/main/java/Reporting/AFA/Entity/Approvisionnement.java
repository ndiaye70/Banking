package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Approvisionnement {
    @Id
    private String id;

    private String date;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TYPE type;
    @Enumerated(EnumType.STRING)
    private Origine origine;
    @Enumerated(EnumType.STRING)
    private Compte compteCaisse1;
    private String destination;
    @Enumerated(EnumType.STRING)
    private Compte compteCaisse2;

    @Enumerated(EnumType.STRING)
    private Devise devise;

    private String statut;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Approvisionnement() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    public enum TYPE {
        Approvisionnement_caisse_entrant("Approvisionnement caisse entrant"), Approvisionnement_caisse_sortant("Approvisionnement caisse sortant"), Approvisionnement_compte_virtuel_entrant("Approvisionnement compte virtuel entrant"),
        Approvisionnement_compte_virtuel_sortant("Approvisionnement compte virtuel sortant");

        private final String label;

        TYPE(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;

        }
    }

        public enum Compte {
            Caisse("Caisse"), Orange_Money("Orange Money"), Free_Money("Free Money"), Wave("Wave"), Wari("Wari"), Wizall("Wizall"), Ecobank("Ecobank"), ORABANK_Western_Union("ORABANK_Western_Union"),
            ORABANK_Ria("ORABANK_Ria"),
            ORABANK_Moneygram("ORABANK Moneygram"), ORABANK_SmallWorld("ORABANK SmallWorld"), BnB("BnB"), Askia("Askia"), SUNU_Assurance("SUNU Assurance");


            private final String label;

            Compte(String label) {
                this.label = label;
            }

            public String getLabel() {
                return label;

            }
        }

        public enum Origine{
            Distributeur("Distributeur"),Siége("Siége"),Commissions("Commissions"),Obélisque("Obélisque"),
            Castor("Castor"),Sacré_cœur("Sacré-cœur"),Niary_Tally("Niary Tally"),Agence_Principale("Agence Principale"),
            Parcelles("Parcelles"),Guédiawaye("Guédiawaye");


            private final String label;

            Origine(String label) {
                this.label = label;
            }

            public String getLabel() {
                return label;

            }

        }

        // Enumération
        public enum Devise {
            XOF, EUR, USD
        }
    }

