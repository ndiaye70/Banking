package Reporting.AFA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

// Dans votre package Reporting.AFA.dto
public class CustomOperationResult {
    private String id;
    private String caissier;
    private Service service;
    private NatureOperation natureOperation;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private Double montant;
    private String date;
    private String statut;


    public enum Service {
        Orange_Money("Orange Money"),
        Orange_money("Orange Money"),
        Free_Money("Free Money"),
        Free_money("Free Money"),
        Wave("Wave"),
        Wari("Wari"),
        Wizall("Wizall"),
        Ecobank("Ecobank"),
        Western_union("Western Union"),
        Ria("Ria"),
        Moneygram("Moneygram"),
        Smallworld("Smallworld"),
        BnB("BnB"),
        Askia("Askia"),
        Sunu_Assurance("Sunu Assurance"),
        Creation_Entreprise("Création Entreprise"),
        Cartes("Cartes Com-Imp/Exp"),
        Cartes_Com_Imp_Exp("Cartes Com-Imp/Exp"),
        Natte("Natte");

        private final String label;

        Service(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum NatureOperation {
        Dépôt("Dépôt"),
        retrait("Retrait"),
        RetraitCode("Retrait Code"),
        Seddo("Seddo"),
        Paiement_de_Facture("Paiement de Facture"),
        Achat_Credit_Telephonique("Achat Crédit Téléphonique"),
        IZI("IZI"),
        Envoie("Envoi"),
        Encaissement_Assurance("Encaissement Assurance"),
        Decaissement_Assurance("Décaissement Assurance"),
        Encaissement_Création_Entreprise("Encaissement Création Entreprise"),
        Décaissement_Création_Entreprise("Décaissement Création Entreprise"),
        Encaissement_Carte("Encaissement Carte"),
        Decaissement_Carte("Décaissement Carte");

        private final String label;

        NatureOperation(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }


    // Constructeur, getters et setters
}
