package Reporting.AFA.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Change {

    @Id
    private String id;

    private String date;
    private String prenom;
    private String nom;

    @Enumerated(EnumType.STRING)
    private Service service;

    @Enumerated(EnumType.STRING)
    private Devise deviseRecu;

    @Enumerated(EnumType.STRING)
    private Devise deviseRemis;

    private double montantRecu;
    private double montantRemis;
    @Transient
    private CourDuJour courDuJour;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public Change() {
        this.id = generateId();
        LocalDateTime now = LocalDateTime.now();

        // Formater la date avec suppression des fractions de seconde
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = now.format(formatter);
    }

    // Enumérations
    public enum Service {
        Achat_de_Devise("Achat De Devise"), Vente_Devise("Vente de Devise");

        private final String label;

        Service(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Devise {
        EUR, XOF, USD
    }


    public double convertirMontants() {
        // Vérification que les devises sont différentes pour effectuer une conversion
        if (!Objects.equals(deviseRecu, deviseRemis)) {
            // Vérification que les taux de change sont disponibles
            if (courDuJour != null) {
                // Conversion des montants en fonction des devises et des taux de change
                switch (deviseRecu) {
                    case EUR:
                        montantRemis = montantRecu * courDuJour.getAchatEuro();
                        break;
                    case USD:
                        montantRemis = montantRecu * courDuJour.getAchatUSD();
                        break;
                    case XOF:
                        if (deviseRemis == Devise.EUR) {
                            montantRemis = montantRecu / courDuJour.getVenteEuro();
                        } else if (deviseRemis == Devise.USD) {
                            montantRemis = montantRecu / courDuJour.getVenteUSD();
                        }
                        break;
                    // Ajoutez d'autres cas pour d'autres devises si nécessaire
                }
                return montantRemis; // Retourner le montant reçu après la conversion
            } else {
                System.out.println("Les taux de change ne sont pas disponibles.");
            }
        } else {
            System.out.println("Les devises sont identiques, aucune conversion n'est nécessaire.");
        }
        return montantRecu; // Retourner le montant reçu sans conversion si les conditions ne sont pas remplies
    }
}


