package Reporting.AFA.dto;

import lombok.Data;

@Data
public class CustomApprovisionnement {

    private String id;

    private String date;
    private double montant;
    private String agent;

    private TYPE type;
    private Origine origine;

    private Compte compteCaisse1;
    private Origine destination;

    private Compte compteCaisse2;


    private Devise devise;

    private String statut;


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
        Caisse("Caisse"),Distributeur("Distributeur"),Siége("Siége"),Commissions("Commissions"),Obélisque("Obélisque"),
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
    public enum Devise {
        XOF, EUR, USD
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

    }
