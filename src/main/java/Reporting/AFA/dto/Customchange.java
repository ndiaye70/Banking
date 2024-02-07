package Reporting.AFA.dto;

import Reporting.AFA.Entity.Change;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data

public class Customchange {

    private String id;

    private String date;
    private String prenom;
    private String nom;
    private String Agent;


    @Enumerated(EnumType.STRING)
    private Change.Service service;

    @Enumerated(EnumType.STRING)
    private Change.Devise deviseRecu;

    @Enumerated(EnumType.STRING)
    private Change.Devise deviseRemis;

    private double montantRecu;

    private double montantRemis;


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

}
