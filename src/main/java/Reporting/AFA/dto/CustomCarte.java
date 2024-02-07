package Reporting.AFA.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CustomCarte {
    private String id;
    private String date;
    private String agent;
    private String nomComplet;
    private String tel;
    private Service service;
    private Double montant;

    public enum Service {
        CARTE_COMMERCANT("CARTE COMMERCANT"),
        CARTE_IMPORT_EXPORT("CARTE IMPORT EXPORT"),
        Première_demande_PACK("Première demande Pack (C.C et C.I.E)"),
        RENOUVELLEMENT_CARTE_COMMERCANT("Renouvellement CARTE COMMERCANT"),
        RENOUVELLEMENT_CARTE_IMPORT_EXPORT("Renouvellement CARTE IMPORT EXPORT"),
        RENOUVELLEMENT_PACK("Renouvellement Pack (C.C et C.I.E)");

        private final String label;

        Service(String label) {


            this.label = label;
        }

        public String getLabel() {
            return label;
        }

    }

}


