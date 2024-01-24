package Reporting.AFA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import Reporting.AFA.Entity.Entreprise;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDto {
    private String nom;
    private String prenom;
    private String numTel;
    private String cni;
    private String nom_Entreprise;
    private Entreprise.TypeEntreprise typeEntreprise;
    private Entreprise.Demande demande; // Enum pour la demande, pas une chaîne

    public Entreprise toEntity() {
        Entreprise entreprise = new Entreprise();
        entreprise.setNom(this.nom);
        entreprise.setPrenom(this.prenom);
        entreprise.setNumTel(this.numTel);
        entreprise.setCni(this.cni);
        entreprise.setNom_Entreprise(this.nom_Entreprise);
        entreprise.setTypeEntreprise(this.typeEntreprise);
        entreprise.setDemande(this.demande);

        // Ajoutez d'autres conversions si nécessaire

        return entreprise;
    }
}

