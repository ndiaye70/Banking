package Reporting.AFA.dto;

import Reporting.AFA.Entity.Agent;
import Reporting.AFA.Entity.OuvertureCpteEntreprise;
import lombok.Data;

@Data

public class CustomCpteEntreprise {
    private String id;
    private String agent;

    private String date;
    private String typeCompte;
    private String rccm;
    private FormeJuridique formeJuridique;
    private String ninea;

    private String denominationSocial;

    private Pack pack;

    private double montantDepotInitial;

    private String nom;

    private String prenom;

    private String fonctions;

    private String numCNI_Passeport;

    private String numTel;

    private String adresse;

    public enum FormeJuridique {
        EI, SA, SARL, SAS, GIE, Association
    }

    public enum Pack {
        Terru("Terru"), Doolel("Doolel"), YaatalTPE("Yaatal TPE"), Ndariñ_microentreprises("Ndariñ Microentreprises"),
        AND_Jappo_GIE_TAMBALI("AND Jappo GIE/TAMBALI");

        private final String label;

        Pack(String label) {
            this.label = label;

        }

        public String getLabel() {
            return label;
        }
    }
}
