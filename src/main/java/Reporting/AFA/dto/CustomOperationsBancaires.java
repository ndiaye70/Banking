package Reporting.AFA.dto;

import lombok.Data;

@Data

public class CustomOperationsBancaires {
    private String id;
    private String date;
    private String agent;
    private Banques banques;
    private NatureOperations natureOperations;
    private String numeroCompte;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private double montant;
    private double commissions;

    public enum Banques {
        BNDE, UBA
    }

    public enum NatureOperations {
        Depot("Dépôts"), Retrait("Retrait"),Achat_Carte_Prépayée("Achat Carte Prépayée") ,
        Recharge_Carte_Prépayée("Recharge Carte Prépayée")  ,Décaissement_UBA_CP("Décaissement UBA (CP)"),
        Dépôt_initial("Dépôt initial") ;

        private final String label;

        NatureOperations(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;

        }
    }
}