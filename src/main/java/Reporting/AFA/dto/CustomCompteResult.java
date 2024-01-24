package Reporting.AFA.dto;
import lombok.Data;

@Data
public class CustomCompteResult {
    private String id;
    private String date;
    private String caissier;
    private String nom;
    private String prenom;
    private String numTel;
    private String adresse;
    private String activite;
    private String numCni_Passeport;
    private String nomConjoint_Conjointe;
    private String nom_PrenomMere;
    private Pack pack;
    private Double montantDepotInitial;
    private TypeCompte typeCompte;


    public enum Pack {
        Ganalé, Kilifa, Wurus
    }

    public enum TypeCompte {
        Compte_Courant("Compte Courant"), Compte_Epargne("Compte Epargne");

        private final String label;

        TypeCompte(String label) {
            this.label = label;

        }

        public String getLabel() {
            return label;
        }
    }
}
