package Reporting.AFA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import Reporting.AFA.Entity.Grossiste;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrossisteDto {

    private String civilite;
    private String nom;
    private String prenom;
    private String numTel;
    private Double montant;
    private Grossiste.MontantPayePar montantPayePar;
    private Double frais;
    private Grossiste.FraisPayePar fraisPayePar;
    private String autres;
    private String statut;

    public Grossiste toEntity() {
        Grossiste grossiste = new Grossiste();
        // Conversion des chaînes en énumérations
        grossiste.setCivilite(Grossiste.Civilite.valueOf(this.civilite));
        grossiste.setNom(this.nom);
        grossiste.setPrenom(this.prenom);
        grossiste.setNumTel(this.numTel);
        grossiste.setMontant(this.montant);
        grossiste.setMontantPayePar(this.montantPayePar);
        grossiste.setFrais(this.frais);
        grossiste.setFraisPayePar(this.fraisPayePar);
        grossiste.setAutres(this.autres);
        grossiste.setStatut(this.statut);
        return grossiste;
    }
}