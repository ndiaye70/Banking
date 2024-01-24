package Reporting.AFA.dto;

import lombok.Data;

// Dans le même package que Entreprise
@Data
public class CustomEntrepriseResult {
    private String dateCreation;
    private String agent;
    private String nom;
    private String prenom;
    private String numTel;
    private String cni;
    private TypeEntreprise typeEntreprise;
    private String nom_Entreprise;
    private Demande demande;
    private Double montant;
    private String id;


    public enum TypeEntreprise {
        GIE("GIE"),
        Entreprise_Individuelle_avec_Nom_Commercial("Entreprise Individuelle avec Nom Commercial"),
        Entreprise_Individuelle_sans_Nom_Commercial("Entreprise Individuelle sans Nom Commercial");

        private final String label;

        TypeEntreprise(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }


    }

    public enum Demande {
        CREATION_ENTREPRISE_INDIVIDUELLE_SANS_NOM_COMMERCIAL("Création d'entreprise individuelle sans nom commercial"),
        CREATION_ENTREPRISE_INDIVIDUELLE_AVEC_NOM_COMMERCIAL("Création d'entreprise individuelle avec nom commercial"),
        CREATION_GIE("Création d'un GIE"),
        CHANGEMENT_NOM_COMMERCIAL("Changement de nom commercial"),
        DEMANDE_NOM_COMMERCIAL_RC_SNC("Demande de nom commercial sur RC SNC"),
        CHANGEMENT_SUPPRESSION_AJOUT_ACTIVITES("Changement/suppression/ Ajout d'activités"),
        CHANGEMENT_SIEGE_SOCIAL("Changement de siège social"),
        CHANGEMENT_ADRESSE("Changement d'adresse"),
        CHANGEMENT_NUMERO_TEL("Changement de numéro de Tél"),
        CHANGEMENT_DIRIGEANT("Changement de dirigeant"),
        CHANGEMENT_SITUATION_MATRIMONIAL("Changement de situation matrimonial"),
        CHANGEMENT_NUM_CNI_PASSEPORT("Changement de N° CNI/ Passeport"),
        DEMANDE_DUPLICATA_RC_NINEA("Demande de duplicata RC ET NINEA"),
        DEMANDE_RADIATION("Demande de radiation"),
        DEMANDE_NINEA("Demande de Ninea");

        private final String label;

        Demande(String label) {
            this.label = label;

        }

        public String getLabel() {
            return label;
        }

    }
}


